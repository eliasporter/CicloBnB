package com.example.ciclobnb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ciclobnb.Objectes.Direccio;
import com.example.ciclobnb.Objectes.Usser;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.ArrayList;

public class CrearCompte extends AppCompatActivity implements View.OnClickListener {
    EditText textLogin,textPass,textNom,textCognom1,textCognom2,textEdat, textEmail,textIban,textDireccio;
    String login,password,nom,cognom1,cognom2,edat,email,iban,direccio;
    Spinner paisos,ciutats,codiPostal;
    Button cancela,crea;
    Context c=this;
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
                    Usser temp = new Usser(textNom.getText().toString(),textCognom1.getText().toString(),textCognom2.getText().toString(),
                            textLogin.getText().toString(),textPass.getText().toString(),textEdat.getText().toString(),
                            textEmail.getText().toString(),true);

                    try {
                        if(temp.insertUser()){//si retorna true anem a la seguent sinó ens quedem
                            try {
                                temp=temp.Login(textLogin.getText().toString(),textPass.getText().toString());
                            } catch (SQLException | InterruptedException e) {
                                e.printStackTrace();
                            }
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
        ArrayList<String>paisos=new Usser().BuscarPaisos(this);
        ArrayAdapter<String> adapterPais = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, paisos);
        this.paisos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String>ciutats= null;
                try {
                    ciutats = new Usser().BuscarCiutats(CrearCompte.this,position+1);//agafarà el paìs amb l'id
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ArrayAdapter<String>  adapterCiutats = new ArrayAdapter<String>(CrearCompte.this, android.R.layout.simple_spinner_item, ciutats);
                CrearCompte.this.ciutats.setAdapter(adapterCiutats);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nada fue seleccionado. Por cierto, no he visto que este método se desencadene
            }
        });
        this.ciutats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String>cp= null;
                try {
                    cp = new Usser().BuscarCP(CrearCompte.this,new Direccio().buscarCiutatPerNom(CrearCompte.this.ciutats.getSelectedItem().toString()));//agafarà el paìs amb l'id
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ArrayAdapter<String>  adapterCP = new ArrayAdapter<String>(CrearCompte.this, android.R.layout.simple_spinner_item, cp);
                CrearCompte.this.codiPostal.setAdapter(adapterCP);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nada fue seleccionado. Por cierto, no he visto que este método se desencadene
            }
        });
        //Emplenem Spinners

        this.paisos.setAdapter(adapterPais);
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
        textEdat=(EditText) findViewById(R.id.Edat);
        textEmail=(EditText) findViewById(R.id.mail);
        textIban = (EditText) findViewById(R.id.iban);
        textDireccio = (EditText) findViewById(R.id.Direccio);
    }

    private Boolean comprovar(){//comprovem que els camps del formulari estan ben escrits

        return true;
    }

}