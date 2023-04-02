package com.example.ciclobnb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EditarPerfilUser extends AppCompatActivity implements View.OnClickListener {
    Button guarda,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil_user);
        guarda=(Button) findViewById(R.id.guardaUserEditat);
        guarda.setOnClickListener(this);
        cancel=findViewById(R.id.cancelaEditUser);
        cancel.setOnClickListener(this);
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
                Toast.makeText(getApplicationContext(), "Dades desades correctament.", Toast.LENGTH_SHORT).show();
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