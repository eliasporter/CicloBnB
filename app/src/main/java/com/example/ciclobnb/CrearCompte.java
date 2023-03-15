package com.example.ciclobnb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CrearCompte extends AppCompatActivity implements View.OnClickListener {
    EditText textLogin,textPass,textNom,textCognom1,textCognom2,textEdat, textEmail,textIban,textDireccio;
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
        crea=(Button)findViewById(R.id.crea);
        crea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c,PrimeraPantalla.class);
                if(comprovar())
                    startActivity(i);
                else
                    Toast.makeText(getApplicationContext(), "S'ha emplenat malament algún dels camps, reemplena", Toast.LENGTH_SHORT).show();

            }
        });
        textLogin.setOnClickListener(this);
        textPass.setOnClickListener(this);
        textNom.setOnClickListener(this);
        textCognom1.setOnClickListener(this);
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