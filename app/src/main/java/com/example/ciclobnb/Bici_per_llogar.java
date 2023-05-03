package com.example.ciclobnb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ciclobnb.Objectes.Ofereix;
import com.example.ciclobnb.Objectes.Usser;

public class Bici_per_llogar extends AppCompatActivity implements View.OnClickListener{
    Button llogar,xat;
    TextView login, tipusBici, preu, descripcio;
    Ofereix ofereix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bici_per_llogar);

        Bundle b = getIntent().getExtras();
        ofereix = b.getParcelable("Bike");

        OpenObjects();
        FillText();
        llogar.setOnClickListener(this);
    }

    private void OpenObjects(){
        Log.d("LOGOfereix", "IniciandoObjetos");
        llogar = findViewById(R.id.LlogarBici);
        login = findViewById(R.id.NomLoginBiciPerLlogar);
        tipusBici = findViewById(R.id.tipusBici);
        preu = findViewById(R.id.preuBici);
        descripcio = findViewById(R.id.descripcioText);
        Log.d("LOGOfereix", "ObjetosIniciados");
    }

    @SuppressLint("SetTextI18n")
    private void FillText(){
        Log.d("LOGOfereix", "LlenandoInformacion");
        login.setText(ofereix.ussers.get(0).getLogin());
        tipusBici.setText(ofereix.bicis.get(0).getTipus());
        preu.setText(ofereix.disponibilitats.get(0).getPreu() + "");
        descripcio.setText(ofereix.bicis.get(0).getDescripcio());
        Log.d("LOGOfereix", "InformacionLlenada");
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
                //i.putExtra("id",usuari.getIdUser());
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        if(v==llogar){//dialeg a mostrar per a veurer si l'usuari està segur de si vol iniciar el lloguer
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage(R.string.DialegIniciLloger);
            builder.setPositiveButton(R.string.continuar, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });

        }else if(v==xat){

        }
    }
}