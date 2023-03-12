package com.example.ciclobnb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button inicia,pass,creaUser;
    EditText nom,contra;
    Context c=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        inicia= findViewById(R.id.inicia);
        inicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom=(EditText) findViewById(R.id.editTextTextPersonName);
                contra=(EditText) findViewById(R.id.editTextTextPassword);
                if(cercaUser()) {
                    Intent intent = new Intent(c, PrimeraPantalla.class);
                    startActivity(intent);
                }else{
                    ColorStateList color = ColorStateList.valueOf(getResources().getColor(R.color.bermell));
                    contra.setBackgroundTintList(color);
                    contra.setText("");
                    Toast.makeText(getApplicationContext(), "Usuari o contrasenya incorrectes", Toast.LENGTH_SHORT).show();
                }
            }
        });
        creaUser=(Button)  findViewById(R.id.creaCompte);
        creaUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c,CrearCompte.class);
                startActivity(intent);
            }
        });
    }
    public boolean cercaUser(){
        if(nom.getText().toString().equals("Admin")&&contra.getText().toString().equals("123"))
        return true;
        return false;
    }
}