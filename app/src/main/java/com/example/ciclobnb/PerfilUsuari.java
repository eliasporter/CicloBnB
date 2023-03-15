package com.example.ciclobnb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout.LayoutParams;


import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

public class PerfilUsuari extends AppCompatActivity {
    Button edita, garatge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuari);
        edita=(Button) findViewById(R.id.edit);
       /* LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, // ancho
                80 // altura en píxeles
        );
        edita.setLayoutParams(params);*/
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
}