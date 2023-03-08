package com.example.ciclobnb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    Button inicia,pass,creaUser;
    EditText nom,contra;
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
                    Intent intent = new Intent(Login.this, PrimeraPantalla.class);
                    startActivity(intent);
                }
            }
        });
    }
    public boolean cercaUser(){
        if(nom.getText().toString().equals("Admin")&&contra.getText().toString().equals("123"))
        return true;
        return false;
    }
}