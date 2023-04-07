package com.example.ciclobnb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.ciclobnb.Objectes.Usser;

public class PerfilUsuari extends AppCompatActivity implements View.OnClickListener {
    Button edita, garatge,xatButton;
    TextView loginText,nomCognomsText,direccioLlogerText;
    RatingBar qualiRatingBar;
    Usser usuari;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuari);
        Bundle b =getIntent().getExtras();
        usuari=new Usser().getUserPerId(b.getInt("id"));
        iniciarTextView();
        xatButton=findViewById(R.id.xatsButton);
        xatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilUsuari.this, XatsAmbPersones.class);
                intent.putExtra("id",usuari.getIdUser());
                startActivity(intent);
            }
        });
        edita=(Button) findViewById(R.id.edit);
        edita.setOnClickListener(this);
        garatge=findViewById(R.id.botoGaratge);
        garatge.setOnClickListener(this);
       /* LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, // ancho
                80 // altura en píxeles
        );
        edita.setLayoutParams(params);*/
    }

    private void iniciarTextView() {
        loginText=findViewById(R.id.nomLogin);
        loginText.setText(usuari.getLogin());

        nomCognomsText=findViewById(R.id.nomCognoms);
        nomCognomsText.setText(usuari.getNom()+" "+usuari.getCognom1()+" "+usuari.getCognom2());

        direccioLlogerText=findViewById(R.id.direccioLloger);
        direccioLlogerText.setText("C/unic Num23");

        qualiRatingBar=findViewById(R.id.cualificacio);
        qualiRatingBar.setIsIndicator(true);
        qualiRatingBar.setRating(3.7f);
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
        if(v.equals(edita)){
            Intent i= new Intent(PerfilUsuari.this,EditarPerfilUser.class);
            i.putExtra("id",usuari.getIdUser());
            startActivity(i);
        }else if(v.equals(garatge)){
            Intent i= new Intent(PerfilUsuari.this,Garatge.class);
            i.putExtra("id",usuari.getIdUser());

            startActivity(i);
        }

    }
}