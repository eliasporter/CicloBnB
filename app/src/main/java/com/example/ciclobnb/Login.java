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

import com.example.ciclobnb.Objectes.Usser;

import java.sql.SQLException;

public class Login extends AppCompatActivity {
    Button login, passForgotten, newUser;
    EditText nom,contra;
    Context c=this;
    Usser usuari=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        passForgotten=findViewById(R.id.NoPass);
        login= findViewById(R.id.inicia);
        newUser=(Button)  findViewById(R.id.creaCompte);

        login.setOnClickListener(onClickListener());
        newUser.setOnClickListener(onClickListener());
    }

    public View.OnClickListener onClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (v == login) {
                   nom=(EditText) findViewById(R.id.editTextTextPersonName);
                   contra=(EditText) findViewById(R.id.editTextTextPassword);
                   if(cercaUser()) {
                       Intent intent = new Intent(c, PrimeraPantalla.class);
                       intent.putExtra("User", usuari);
                       startActivity(intent);
                   }else{
                       ColorStateList color = ColorStateList.valueOf(getResources().getColor(R.color.bermell));
                       contra.setBackgroundTintList(color);
                       contra.setText("");
                       Toast.makeText(getApplicationContext(), "Usuari o contrasenya incorrectes", Toast.LENGTH_SHORT).show();
                   }
               } else if (v == newUser){
                   Intent intent =new Intent(c,CrearCompte.class);
                   startActivity(intent);
               }
            }
        };
    }
    public boolean cercaUser(){
        //Cridem la funcio que te Usser per a buscar l'usuari
        try {
            usuari=new Usser().Login(nom.getText().toString(),contra.getText().toString());
        }catch (SQLException | InterruptedException e){
            e.printStackTrace();
        }

        return usuari != null;
    }
}