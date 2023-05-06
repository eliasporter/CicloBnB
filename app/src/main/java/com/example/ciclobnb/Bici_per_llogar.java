package com.example.ciclobnb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ciclobnb.Objectes.BicisLlogater;
import com.example.ciclobnb.Objectes.Ofereix;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Bici_per_llogar extends AppCompatActivity implements View.OnClickListener{
    Button llogar,xat;
    TextView login, tipusBici, preu, descripcio, marca, modelo, suspension;
    CalendarView calendarView;
    Ofereix ofereix;
    ArrayList<BicisLlogater> bicisLlogaterArrayList;
    Context context = this;
    @SuppressLint("SimpleDateFormat") private final SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bici_per_llogar);

        Bundle b = getIntent().getExtras();
        ofereix = b.getParcelable("Bike");

        OpenObjects();
        FillText();
        OnDateChange();
        llogar.setOnClickListener(this);
    }

    private void OnDateChange(){
        VerificarFechaDisponible verificarFechaDisponible = new VerificarFechaDisponible(ofereix.getIdBici());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                try {
                    Date f = date.parse(year + "-" + (month+1) + "-" + dayOfMonth);
                    if (verificarFechaDisponible.ImportantDays(f))
                        Toast.makeText(getApplicationContext(), "Data ocupada!", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(getApplicationContext(), "Disponible!", Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /*private void OpenRecycler(){
        Log.d("LOGOfereix","IniciandoRecycler");
        RecyclerView recyclerView = findViewById(R.id.dispoRecycler);
        recyclerView.setAdapter(new AdapterDisponibilitats(ofereix.disponibilitats, context));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("LOGOfereix","TerminandoRecycler");
    }*/

    private void OpenObjects(){
        Log.d("LOGOfereix", "IniciandoObjetos");
        llogar = findViewById(R.id.LlogarBici);
        login = findViewById(R.id.NomLoginBiciPerLlogar);
        tipusBici = findViewById(R.id.tipusBici);
        preu = findViewById(R.id.preuBici);
        descripcio = findViewById(R.id.descripcioText);
        marca = findViewById(R.id.TextMarca);
        modelo = findViewById(R.id.TextModelo);
        suspension = findViewById(R.id.TextSuspension);
        calendarView = findViewById(R.id.calendarView);
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        calendarView.setClickable(true);
        calendarView.setMinDate(System.currentTimeMillis());
        Log.d("LOGOfereix", "ObjetosIniciados");
    }

    @SuppressLint("SetTextI18n")
    private void FillText(){
        Log.d("LOGOfereix", "LlenandoInformacion");
        login.setText(ofereix.ussers.get(0).getLogin());
        tipusBici.setText(ofereix.bicis.get(0).getTipus());
        preu.setText(ofereix.disponibilitats.get(0).getPreu() + " €");
        descripcio.setText(ofereix.bicis.get(0).getDescripcio());
        marca.setText(ofereix.bicis.get(0).getMarca());
        modelo.setText(ofereix.bicis.get(0).getModelo());
        suspension.setText(ofereix.bicis.get(0).getSuspension());
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