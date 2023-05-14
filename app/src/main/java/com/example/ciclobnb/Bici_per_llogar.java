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

import com.example.ciclobnb.BBDD.Connexions.BicisLlogaterConnection;
import com.example.ciclobnb.BBDD.Connexions.GestioLloguersConnection;
import com.example.ciclobnb.Objectes.BicisLlogater;
import com.example.ciclobnb.Objectes.GestioLloguers;
import com.example.ciclobnb.Objectes.Ofereix;
import com.example.ciclobnb.Objectes.Usser;
import com.example.ciclobnb.Objectes.Xat.Xat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.security.auth.x500.X500Principal;

public class Bici_per_llogar extends AppCompatActivity implements View.OnClickListener{
    Button llogar, xat, btnDesde, btnFins;
    TextView login, tipusBici, preu, descripcio, marca, modelo, suspension, lblDesde, lblFins;
    CalendarView calendarView;
    Ofereix ofereix;
    Usser user;
    ArrayList<BicisLlogater> bicisLlogaterArrayList;
    Context context = this;
    @SuppressLint("SimpleDateFormat") private final SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bici_per_llogar);

        Bundle b = getIntent().getExtras();
        ofereix = b.getParcelable("Bike");
        user=b.getParcelable("User");

        OpenObjects();
        FillText();
        OnDateChange();
        llogar.setOnClickListener(this);
        btnDesde.setOnClickListener(this);
        btnFins.setOnClickListener(this);
    }

    private void OnDateChange(){
        VerificarFechaDisponible verificarFechaDisponible = new VerificarFechaDisponible(ofereix.getIdBici());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                try {
                    Date f = date.parse(year + "-" + (month+1) + "-" + dayOfMonth);
                    if (verificarFechaDisponible.ImportantDays(f)){
                        Toast.makeText(getApplicationContext(), "Data ocupada!", Toast.LENGTH_SHORT).show();
                        llogar.setEnabled(false);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Disponible!", Toast.LENGTH_SHORT).show();
                        llogar.setEnabled(true);
                        if (btnDesde.isEnabled()) lblFins.setText(year + "-" + (month+1) + "-" + dayOfMonth);
                        else lblDesde.setText(year + "-" + (month+1) + "-" + dayOfMonth);
                        Date fInicio = date.parse(lblDesde.getText()+""); Date fFin = date.parse(lblFins.getText()+"");
                        llogar.setEnabled(verificarFechaDisponible.VerificarFecha(fInicio, fFin) && !verificarFechaDisponible.VerificarDosFechas(fInicio, fFin));
                        if (llogar.isEnabled()) preu.setText((ofereix.disponibilitats.get(0).getPreu() * ((((fFin.getTime() - fInicio.getTime())/(1000 * 60 * 60 * 24))%365)+1) + " €"));
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

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
        btnDesde = findViewById(R.id.btnDesde);
        btnFins = findViewById(R.id.btnFins);
        lblDesde = findViewById(R.id.lblDesde);
        lblFins = findViewById(R.id.lblFins);
        xat = findViewById(R.id.iniciarXatButton);
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
        btnDesde.setEnabled(false);
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

    private void BiciAlquilada(){
        AlertDialog.Builder a = new AlertDialog.Builder(context);
        a.setMessage("El teu lloguer ha sigut tramitat amb exit!");
        a.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(context, PrimeraPantalla.class);
                //i.putExtra("User", );
                startActivity(i);
            }
        });a.show();
    }

    private void AlquilarBici(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(R.string.DialegIniciLloger);
        builder.setPositiveButton(R.string.continuar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                GestioLloguersConnection gestioLloguersConnection = new GestioLloguersConnection();
                if (gestioLloguersConnection.InsertNew(new GestioLloguers(ofereix.disponibilitats.get(0).getPreu(), ofereix.getIdUsuari(), new Date(System.currentTimeMillis())))){
                    BicisLlogaterConnection bicisLlogaterConnection = new BicisLlogaterConnection();
                    try {
                        if (bicisLlogaterConnection.InsertNew(ofereix.getIdUsuari(), new BicisLlogater(ofereix.getIdBici(), 0, date.parse(lblDesde.getText()+""), date.parse(lblFins.getText()+""), Double.parseDouble(preu.getText().toString().split(" ")[0])))){
                            BiciAlquilada();
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) { }
        });
        builder.show();
    }

    @Override
    public void onClick(View v) {
        if(v==llogar){//dialeg a mostrar per a veurer si l'usuari està segur de si vol iniciar el lloguer
            AlquilarBici();
        }else if(v==xat){
            Intent i = new Intent(this,Missatges.class);
            try {
                int idXat=new Xat().comprovaCreaXat(user,ofereix.ussers.get(0));
                if (idXat!=0)
                    i.putExtra("idXat",idXat);
                else {
                    Toast toast = Toast.makeText(this, "No s'ha pogut crear el Xat tornaho a provar", Toast.LENGTH_SHORT);
                    toast.show();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else if(v==btnDesde){
            btnFins.setEnabled(true);
            btnDesde.setEnabled(false);
        }else if(v==btnFins){
            btnDesde.setEnabled(true);
            btnFins.setEnabled(false);
        }
    }
}