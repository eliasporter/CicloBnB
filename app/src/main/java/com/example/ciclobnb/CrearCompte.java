package com.example.ciclobnb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ciclobnb.BBDD.Connexions.ConnexioDireccio;
import com.example.ciclobnb.BBDD.Connexions.FillSpinners;
import com.example.ciclobnb.Objectes.Direccio;
import com.example.ciclobnb.Objectes.HashMapAdapter;
import com.example.ciclobnb.Objectes.Usser;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

public class CrearCompte extends AppCompatActivity implements View.OnClickListener {
    EditText textLogin,textPass,textNom,textCognom1,textCognom2, textEmail,textIban;
    DatePicker textEdat;
    Button textDireccio;
    String login,password,nom,cognom1,cognom2,edat,email,iban,direccio;
    Spinner paisos,ciutats,codiPostal;
    String pais, ciutat,cp;
    String tipusVia,nomCarrer,numero, pis;
    Button cancela,crea;
    Context c=this;
    EditText textCarrer;
    EditText textTipusVia;
    EditText textnumero;
    EditText textPis;
    AlertDialog dialeg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_compte);
        assigna();
        cancela=(Button)findViewById(R.id.cancel);
        cancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(c,Login.class);
                startActivity(i);
            }
        });
//iniciem Spinners
        paisos = findViewById(R.id.spinner_paises);
        ciutats = findViewById(R.id.spinner_ciudades);
        codiPostal = findViewById(R.id.spinner_codigos_postales);

        try {
            emplenarLinears();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
        crea=(Button)findViewById(R.id.crea);
        crea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c,PrimeraPantalla.class);
                if(comprovar()) {
                    try {
                        guardarDir(textCarrer, textTipusVia,textPis,textnumero);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //String tipus, String nomCarrer, String numero, String pis, int idCP
                    Usser temp = new Usser(textNom.getText().toString(),textCognom1.getText().toString(),textCognom2.getText().toString(),
                            textLogin.getText().toString(),textPass.getText().toString(),new Date(textEdat.getYear(), textEdat.getMonth(), textEdat.getDayOfMonth()),
                            textEmail.getText().toString(),true);

                    try {
                        if(temp.insertUser()){//si retorna true anem a la seguent sinó ens quedem
                            try {
                                temp=temp.Login(textLogin.getText().toString(),textPass.getText().toString());
                            } catch (SQLException | InterruptedException e) {
                                e.printStackTrace();
                            }
                            //iniciem sessió
                            i.putExtra("id",temp.getIdUser());
                            startActivity(i);
                        }else
                            Toast.makeText(getApplicationContext(), "Ha agut un error, torna-ho a provar", Toast.LENGTH_SHORT).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else
                    Toast.makeText(getApplicationContext(), "S'ha emplenat malament algún dels camps, reemplena", Toast.LENGTH_SHORT).show();

            }
        });
        /*textLogin.setOnClickListener(this);
        textPass.setOnClickListener(this);
        textNom.setOnClickListener(this);
        textCognom1.setOnClickListener(this);*/
    }

    private void emplenarLinears() throws SQLException, InterruptedException {
        FillSpinners fillSpinners = new FillSpinners();
        fillSpinners.FillCountries();
        Log.d("CitiesLoading", "Countries");
        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fillSpinners.countries);
        paisos.setAdapter(countriesAdapter);
        paisos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Log.d("CitiesLoading", "Cities");
                    fillSpinners.FillCities(fillSpinners.countries.get(position));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(c, android.R.layout.simple_spinner_item, fillSpinners.cities);
                ciutats.setAdapter(citiesAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        this.ciutats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Log.d("CitiesLoading", "Postal Code");
                    fillSpinners.FillCP(fillSpinners.cities.get(position));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                ArrayAdapter<String> postalAdapter = new ArrayAdapter<>(c, android.R.layout.simple_spinner_item, fillSpinners.cp);
                CrearCompte.this.codiPostal.setAdapter(postalAdapter);
            }
              @Override

            public void onNothingSelected(AdapterView<?> parent) {
                //todo
                // Nada fue seleccionado. Por cierto, no he visto que este método se desencadene
            }
    });
    }

    @Override
    public void onClick(View v) {
        if(v instanceof EditText){
            EditText e = (EditText) v;
            if(e.getText().toString().charAt(0)=='.')
                e.setText("");
        }
    }
    private void assigna(){
        textLogin=(EditText) findViewById(R.id.loginBox);
        textPass=(EditText) findViewById(R.id.password);
        textNom=(EditText) findViewById(R.id.nom);
        textCognom1=(EditText) findViewById(R.id.cognom1);
        textCognom2=(EditText) findViewById(R.id.cognom2);
        textEdat=(DatePicker) findViewById(R.id.Edat);
        textEmail=(EditText) findViewById(R.id.mail);
        textIban = (EditText) findViewById(R.id.iban);
        textDireccio = findViewById(R.id.Direccio);

        LayoutInflater inflater = LayoutInflater.from(this);
        final View ompleDir = inflater.inflate(R.layout.emplena_direccio, null);
        textDireccio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean[] be = {false};
                if (ompleDir.getParent() != null) {
                    ((ViewGroup) ompleDir.getParent()).removeView(ompleDir);
                }
                AlertDialog.Builder dir = new AlertDialog.Builder(c);
                dir.setTitle("Emplena els camps");
                dir.setView(ompleDir);
                emplenarDialeg();
                dir.setPositiveButton("Desar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //guardem la direcció a la base de dades
                        String text = textCarrer.getText().toString();
                        be[0] = comprovaDireccio(textCarrer, textTipusVia, textPis, textnumero);
                        //dir.setCancelable(be[0]);
                        tipusVia=textTipusVia.getText().toString();
                        nomCarrer=textCarrer.getText().toString();
                        pis=textPis.getText().toString();
                        numero=textnumero.getText().toString();

                    }
                });
                dir.setCancelable(be[0]);
                dir.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialeg = dir.create();
                dialeg.show();
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                float dp = 10f;
                float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
                lp.horizontalMargin = px;
                lp.verticalMargin = px;
                dialeg.getWindow().setAttributes(lp);

                textCarrer = dialeg.findViewById(R.id.editTextTextNomCarrer);
                textTipusVia = dialeg.findViewById(R.id.editTextTextTipusVia);
                textnumero = dialeg.findViewById(R.id.editTextTextNumeroPis);
                textPis = dialeg.findViewById(R.id.editTextTextPis);
            }
        });
    }
    private void emplenarDialeg(){
        if(nomCarrer!=null)//&&!nomCarrer.equals(""))
            textCarrer.setText(nomCarrer);
        if(pis!=null)//&&!pis.equals(""))
            textPis.setText(pis);
        if(numero!=null)//&&!numero.equals(""))
            textnumero.setText(numero);
        if(tipusVia!=null)//&&!tipusVia.equals(""))
            textTipusVia.setText(tipusVia);
    }
    private void guardarDir(EditText textCarrer, EditText textTipus,EditText textPis,EditText textNumero) throws InterruptedException {
        //agafel l'id del CP
        int cp= new Direccio().BuscarID(codiPostal.getSelectedItem().toString());
        Direccio temp=new Direccio(textTipus.getText().toString(),textCarrer.getText().toString(),textNumero.getText().toString(),textPis.getText().toString(),cp);
        new Direccio().InsertarNuevo(temp);

    }
    private Boolean comprovar(){//comprovem que els camps del formulari estan ben escrits
        boolean be=true;
        if(!comprovaNom()) {
            be= false;
        }
        if(!comprovaLogin()) {

            be =false;
        }
        if (!comprovaCognoms()){
            be = false;
        }
        if (!comprovaPass()){
            be = false;
        }
        if(!comprovaEdat()){
            be=false;
        }
        if(!comprovaMail()){
            be =false;
        }
        if(!comprovaIban())
            be=false;

        return be;
    }
    private Boolean comprovaNom() {
        if(textNom.getText().toString().equals("")){
            ColorStateList color = ColorStateList.valueOf(getResources().getColor(R.color.bermell));
            textNom.setBackgroundTintList(color);
            return false;
        }
        return true;
    }
    private Boolean comprovaLogin(){
        if(textLogin.getText().toString().equals("")){
            ColorStateList color = ColorStateList.valueOf(getResources().getColor(R.color.bermell));
            textLogin.setBackgroundTintList(color);
            return false;
        }
        return true;
    }
    private Boolean comprovaCognoms (){
        if(textCognom1.getText().toString().equals("")){
            ColorStateList color = ColorStateList.valueOf(getResources().getColor(R.color.bermell));
            textCognom1.setBackgroundTintList(color);
            return false;
        }
        return true;
    }
    private Boolean comprovaPass(){
        if(textPass.getText().toString().equals("")){
            ColorStateList color = ColorStateList.valueOf(getResources().getColor(R.color.bermell));
            textPass.setBackgroundTintList(color);
            return false;
        }
        return true ;
    }
    private Boolean comprovaEdat(){

        LocalDate fechaNacimiento = null;
        try {
            fechaNacimiento = LocalDate.parse(""+textEdat.getYear()+"-"+textEdat.getMonth()+"-"+textEdat.getDayOfMonth(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {

            e.printStackTrace();
            ColorStateList color = ColorStateList.valueOf(getResources().getColor(R.color.bermell));
            textEdat.setBackgroundTintList(color);
            return false;
        }

        LocalDate fechaActual = LocalDate.now();
        int edad = Period.between(fechaNacimiento, fechaActual).getYears();

        if (edad >= 18) {
            return true;
        } else {
            ColorStateList color = ColorStateList.valueOf(getResources().getColor(R.color.bermell));
            textEdat.setBackgroundTintList(color);

            return false;
        }


    }
    private Boolean comprovaMail(){
        if(textEmail.getText().toString().equals("")||!textEmail.getText().toString().contains("@")){//todo
            ColorStateList color = ColorStateList.valueOf(getResources().getColor(R.color.bermell));
            textEmail.setBackgroundTintList(color);
            return false;
        }
        return true;
    }
    private Boolean comprovaIban(){
        if(textIban.getText().toString().equals("")){
            ColorStateList color = ColorStateList.valueOf(getResources().getColor(R.color.bermell));
            textIban.setBackgroundTintList(color);
            return false;
        }
        return true;
    }
    private Boolean comprovaDireccio(EditText textCarrer, EditText textTipus,EditText textPis,EditText textNumero){
        boolean be = true;
        if(textCarrer.getText().toString().equals("")){
            ColorStateList color = ColorStateList.valueOf(getResources().getColor(R.color.bermell));
            textCarrer.setBackgroundTintList(color);
            be= false;
        }
        if(textTipus.getText().toString().equals("")){
            ColorStateList color = ColorStateList.valueOf(getResources().getColor(R.color.bermell));
            textTipus.setBackgroundTintList(color);
            be= false;
        }
        if(textPis.getText().toString().equals("")){
            ColorStateList color = ColorStateList.valueOf(getResources().getColor(R.color.bermell));
            textPis.setBackgroundTintList(color);
            be= false;
        }
        if(textNumero.getText().toString().equals("")){
            ColorStateList color = ColorStateList.valueOf(getResources().getColor(R.color.bermell));
            textNumero.setBackgroundTintList(color);
            be= false;
        }
        return be;
    }
}