package com.example.ciclobnb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import java.sql.SQLException;

public class PerfilUsuari extends AppCompatActivity implements View.OnClickListener {
    Button edita, garatge,xatButton;
    TextView loginText,nomCognomsText,direccioLlogerText;
    RatingBar qualiRatingBar;
    Usser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuari);
        Bundle b =getIntent().getExtras();
        user = b.getParcelable("User");

        iniciarTextView();
       /* LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, // ancho
                80 // altura en píxeles
        );
        edita.setLayoutParams(params);*/
    }

    private void findComponents(){
        loginText = findViewById(R.id.nomLogin);
        nomCognomsText = findViewById(R.id.nomCognoms);
        direccioLlogerText = findViewById(R.id.direccioLloger);
        qualiRatingBar = findViewById(R.id.cualificacio);
        xatButton=findViewById(R.id.xatsButton);
        edita=findViewById(R.id.edit);
        garatge=findViewById(R.id.botoGaratge);
    }

    @SuppressLint("SetTextI18n")
    private void iniciarTextView() {
        loginText.setText(user.getLogin());
        nomCognomsText.setText(user.getNom()+" "+user.getCognom1()+" "+user.getCognom2());
        direccioLlogerText.setText(user.direccio.tipusVia + " " + user.direccio.nomCarrer + " " + user.direccio.numero + "-" + user.direccio.pis);

        qualiRatingBar.setIsIndicator(true);
        qualiRatingBar.setRating(3.7f);
        xatButton.setOnClickListener(this);
        edita.setOnClickListener(this);
        garatge.setOnClickListener(this);
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
                i.putExtra("id",user.getIdUser());
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(edita)){
            Intent i= new Intent(PerfilUsuari.this,CrearCompte.class);
            i.putExtra("User",user);
            startActivity(i);
        }else if(v.equals(garatge)){
            Intent i= new Intent(PerfilUsuari.this,Garatge.class);
            i.putExtra("id",user.getIdUser());
            startActivity(i);
        }else if(v==xatButton){
            Intent intent = new Intent(PerfilUsuari.this, XatsAmbPersones.class);
            intent.putExtra("User",user);
            startActivity(intent);
        }

    }
}