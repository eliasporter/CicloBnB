package com.example.ciclobnb;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.example.ciclobnb.BBDD.Connexions.UserConnection;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrearCompte extends AppCompatActivity implements View.OnClickListener {
    EditText textLogin,textPass,textNom,textCognom1,textCognom2,textEmail,textCarrer,textTipusVia,textnumero,textPis;
    DatePicker textEdat;
    Spinner paisos,ciutats,codiPostal;
    String pais, ciutat,cp;
    String tipusVia,nomCarrer,numero, pis;
    Button cancela,crea,textDireccio;
    Context c=this;
    Usser usser;
    LayoutInflater inflater;
    View ompleDir;
    boolean nueva;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_compte);
        OpenElements();
        assigna();
        SetOnClick();
        Bundle b = getIntent().getExtras();
        nueva = b.getBoolean("Nueva");

        if (!nueva){
            usser = b.getParcelable("User");
            crea.setText("Guardar");
            FillUser();
            textLogin.setEnabled(false);
            textPass.setEnabled(false);
        } else usser = new Usser();

        //iniciem Spinners
        try {
            emplenarLinears();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void FillUser(){
        textLogin.setText(usser.getLogin());
        textPass.setText(usser.getContrasenya());
        textNom.setText(usser.getNom());
        textCognom1.setText(usser.getCognom1());
        textCognom2.setText(usser.getCognom2());
        textEmail.setText(usser.getCorreuElectronic());
        textEdat.updateDate(Integer.parseInt(usser.getDataNaixement().split("-")[0]), Integer.parseInt(usser.getDataNaixement().split("-")[1]) - 1, Integer.parseInt(usser.getDataNaixement().split("-")[2]));
    }

    private void TurnBack(){
        Intent i = null;
        if (nueva) i = new Intent(c,Login.class);
        else {
            i = new Intent(c, PerfilUsuari.class);
            i.putExtra("User", usser);
        }
        startActivity(i);
    }

    private void TestAndFill(){
        usser.setNom(String.valueOf(textNom.getText()));
        usser.setCognom1(textCognom1.getText().toString());
        usser.setCognom2(textCognom2.getText().toString());
        usser.setLogin(textLogin.getText().toString());
        usser.setContrasenya(textPass.getText().toString());
        usser.setDataNaixement(textEdat.getYear() + "-" + (textEdat.getMonth() + 1) + "-" + textEdat.getDayOfMonth());
        usser.setCorreuElectronic(textEmail.getText().toString());
        usser.setActiu(true);
    }

    private void UsuarioNuevo(){
        Intent i = new Intent(c,PrimeraPantalla.class);
        try {
            guardarDir(textCarrer, textTipusVia,textPis,textnumero);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TestAndFill();
        try {
            if(usser.insertUser()){//si retorna true anem a la seguent sinó ens quedem
                i.putExtra("User", usser);
                startActivity(i);
            }else
                Toast.makeText(getApplicationContext(), "Ha agut un error, torna-ho a provar", Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void ActualizarUsuario() {
        if (textCarrer != null) {
            usser.getDireccio().setNomCarrer(textCarrer.getText().toString());
            usser.getDireccio().setNumero(textnumero.getText().toString());
            usser.getDireccio().setPis(textPis.getText().toString());
            usser.getDireccio().setTipusVia(textTipusVia.getText().toString());
        }
        try {
            usser.getDireccio().setIdCP(new Direccio().BuscarID(codiPostal.getSelectedItem().toString()));
            TestAndFill();
            Toast.makeText(getApplicationContext(), "Guardant les teves dades...", Toast.LENGTH_SHORT).show();
            ConnexioDireccio connexioDireccio = new ConnexioDireccio();
            connexioDireccio.Actualizar(usser.getDireccio());
            UserConnection userConnection = new UserConnection();
            userConnection.UpdateUser(usser);
        } catch (InterruptedException e) {e.printStackTrace();}
        Intent i =  new Intent(c, PerfilUsuari.class);
        i.putExtra("User", usser);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        if(v==cancela){
            TurnBack();
        }
        else if (v==crea){

            if(comprovar()) {
                if (nueva) UsuarioNuevo();
                else ActualizarUsuario();
            }else Toast.makeText(getApplicationContext(), "S'ha emplenat malament algún dels camps, reemplena", Toast.LENGTH_SHORT).show();
        }
        else if(v==textDireccio){
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
            if (!nueva) FillStreet();
        }
    }

    private void FillStreet(){
        textCarrer.setText(usser.getDireccio().getNomCarrer());
        textTipusVia.setText(usser.getDireccio().getTipusVia());
        textnumero.setText(usser.getDireccio().getNumero());
        textPis.setText(usser.getDireccio().getPis());
    }

    private void SetOnClick(){
        cancela.setOnClickListener(this);
        crea.setOnClickListener(this);
        textDireccio.setOnClickListener(this);
    }

    private void OpenElements(){
        cancela=findViewById(R.id.cancel);
        paisos = findViewById(R.id.spinner_paises);
        ciutats = findViewById(R.id.spinner_ciudades);
        codiPostal = findViewById(R.id.spinner_codigos_postales);
        textLogin=findViewById(R.id.loginBox);
        textPass=findViewById(R.id.password);
        textNom=findViewById(R.id.nom);
        textCognom1=findViewById(R.id.cognom1);
        textCognom2=findViewById(R.id.cognom2);
        textEdat=findViewById(R.id.Edat);
        textEmail=findViewById(R.id.mail);
        textDireccio = findViewById(R.id.Direccio);
        crea = findViewById(R.id.crea);
    }

    private void emplenarLinears() throws SQLException, InterruptedException {
        FillSpinners fillSpinners = new FillSpinners();
        fillSpinners.FillCountries();
        Log.d("CitiesLoading", "Countries");
        paisos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fillSpinners.countries));
        paisos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Log.d("CitiesLoading", "Cities");
                    fillSpinners.FillCities(fillSpinners.countries.get(position));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                ciutats.setAdapter(new ArrayAdapter<>(c, android.R.layout.simple_spinner_item, fillSpinners.cities));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
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
                CrearCompte.this.codiPostal.setAdapter(new ArrayAdapter<>(c, android.R.layout.simple_spinner_item, fillSpinners.cp));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
    });
    }
    private void assigna(){
        inflater = LayoutInflater.from(this);
        ompleDir = inflater.inflate(R.layout.emplena_direccio, null);
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
        if(!comprovaNom()) return false;
        if(!comprovaLogin()) return false;
        if (!comprovaCognoms()) return false;
        if (!comprovaPass()) return false;
        if(!comprovaEdat()) return false;
        if(!comprovaMail()) return false;
        return true;
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
        LocalDate localDate = LocalDate.now();
        LocalDate birth = LocalDate.of(textEdat.getYear(), textEdat.getMonth(), textEdat.getDayOfMonth());
        int edad = Period.between(birth, localDate).getYears();
        if (edad >= 18) {
            return true;
        } else {
            ColorStateList color = ColorStateList.valueOf(getResources().getColor(R.color.bermell));
            textEdat.setBackgroundTintList(color);
            return false;
        }
    }

    private Boolean comprovaMail(){
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(textEmail.getText().toString());
        if(!matcher.matches()){//todo
            ColorStateList color = ColorStateList.valueOf(getResources().getColor(R.color.bermell));
            textEmail.setBackgroundTintList(color);
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