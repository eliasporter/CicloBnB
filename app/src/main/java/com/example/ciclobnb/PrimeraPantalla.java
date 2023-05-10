package com.example.ciclobnb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ciclobnb.BBDD.Connexions.BicicletesConnection;
import com.example.ciclobnb.BBDD.Connexions.GestioLloguersConnection;
import com.example.ciclobnb.BBDD.Connexions.OfereixConnection;
import com.example.ciclobnb.Objectes.Adapter.AdapterCiclo;
import com.example.ciclobnb.Objectes.Bici;
import com.example.ciclobnb.Objectes.Ofereix;
import com.example.ciclobnb.Objectes.Usser;

import java.util.ArrayList;

public class PrimeraPantalla extends AppCompatActivity implements  View.OnClickListener{
    ArrayList<Ofereix> ofereixes =  new ArrayList<>();
    TextView loginText,nomCognomsText,guanysText;
    Button filtreDia,filtrePreu,filtreDireccio;
    Usser user;
    Button perfil;
    Context c=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_pantalla);

        //Recibir usuario
        Bundle b = getIntent().getExtras();
        user = b.getParcelable("User");

        OpenTextView();
        OpenButton();
        LoadBikes();

        RecyclerView vista = findViewById(R.id.cercaBicis);
        vista.setAdapter(new AdapterCiclo(ofereixes,c));
        vista.setLayoutManager(new LinearLayoutManager(this));
    }

    private void OpenButton(){
        filtreDia=findViewById(R.id.filtreDia);
        filtreDireccio=findViewById(R.id.filtreDireccio);
        filtrePreu=findViewById(R.id.filtrePreu);
        perfil=findViewById(R.id.editPerfil);
        filtreDireccio.setOnClickListener(this);
        filtrePreu.setOnClickListener(this);
        filtreDia.setOnClickListener(this);
        perfil.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    private void OpenTextView() {
        loginText=findViewById(R.id.textPerfil);
        loginText.setText(user.getLogin());

        nomCognomsText=findViewById(R.id.nomCognomsPrimera);
        nomCognomsText.setText(user.getNom()+" "+ user.getCognom1()+" "+ user.getCognom2());

        guanysText=findViewById(R.id.guanysPerfilUsuari);
        GestioLloguersConnection gestioLloguersConnection = new GestioLloguersConnection();
        guanysText.setText(gestioLloguersConnection.GetByUserID(user.getIdUser())+" €");
    }
    private void LoadBikes() {
        OfereixConnection ofereixConnection = new OfereixConnection();
        ofereixes = ofereixConnection.SearchFor(user.getIdUser());
    }

    @Override
    public void onClick(View v) {
        if(v==filtreDia){

        }else if(v==filtreDireccio){

        }else if(v==filtrePreu){

        }else if(v==perfil){
            Intent intent = new Intent(c, PerfilUsuari.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }
    }
}