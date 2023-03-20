package com.example.ciclobnb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Garatge extends AppCompatActivity {
    Button afegir;
    RecyclerView bicis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garatge);
        afegir=findViewById(R.id.AfegirBiciBoto);
        bicis=findViewById(R.id.ReciclerBicis);
        afegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String missatge="Actualment sols es podrà afegir bicicletes des de un ordenador";
                AlertDialog.Builder builder = new AlertDialog.Builder(Garatge.this);
                builder.setTitle(R.string.atencio);
                builder.setMessage(missatge);
                builder.setPositiveButton("Dacord", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                //Toast toast = Toast.makeText(Garatge.this, missatge , Toast.LENGTH_LONG);
                //toast.show();

            }
        });
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