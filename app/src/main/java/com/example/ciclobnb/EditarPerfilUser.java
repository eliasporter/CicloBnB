package com.example.ciclobnb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.ciclobnb.Objectes.Usser;

public class EditarPerfilUser extends AppCompatActivity implements View.OnClickListener {
    Button guarda,cancel;
    Usser usuari;
    EditText loginEdit,nomEdit,cognom1,cognom2,correu,naixement,iban,contrasenya;
    RatingBar cuali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil_user);
        guarda=(Button) findViewById(R.id.guardaUserEditat);
        guarda.setOnClickListener(this);
        Bundle b=getIntent().getExtras();
        usuari=new Usser().getUserPerId(b.getInt("id"));
        cancel=findViewById(R.id.cancelaEditUser);
        cancel.setOnClickListener(this);
        iniciar();
//5
    }

    private void iniciar() {
        loginEdit=findViewById(R.id.editNomLogin);
        loginEdit.setText(usuari.getLogin());
        nomEdit=findViewById(R.id.editNom);
        nomEdit.setText(usuari.getNom());
        cognom1=findViewById(R.id.editCognom);
        cognom1.setText(usuari.getCognom1());
        cognom2=findViewById(R.id.editCognom2);
        correu=findViewById(R.id.editMail);
        correu.setText(usuari.getCorreuElectronic());
        naixement=findViewById(R.id.dataNaixement);
        naixement.setText(usuari.getDataNaixement());
        iban=findViewById(R.id.ibanEdita);
        iban.setText("H555543432");
        cuali=findViewById(R.id.ratingBarEdit);
        cuali.setIsIndicator(true);
        cuali.setRating(3.5f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home :
                Intent i=new Intent(this,PrimeraPantalla.class);
                i.putExtra("id",usuari.getIdUser());
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onClick(View v) {
        if(v.equals(guarda)){
            Intent i = new Intent(this,PerfilUsuari.class);
            if(desar()){
                Toast.makeText(getApplicationContext(), "Desat correctament", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "No s'han pogut desar les noves dades", Toast.LENGTH_SHORT).show();
            }
            startActivity(i);
        }else if(v.equals(cancel)){
            Intent i = new Intent(this,PerfilUsuari.class);
            startActivity(i);
        }
    }

    public boolean desar() {//aquí guardadem com ho desem, i si sh'a desat o no
        return true;
    }
}