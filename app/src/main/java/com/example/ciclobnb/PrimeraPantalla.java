package com.example.ciclobnb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ciclobnb.Objectes.Adapter.AdapterCiclo;
import com.example.ciclobnb.Objectes.Bici;
import com.example.ciclobnb.Objectes.Usser;

import java.sql.SQLException;
import java.util.ArrayList;

public class PrimeraPantalla extends AppCompatActivity implements  View.OnClickListener{
    ArrayList <Bici> bicis=new ArrayList<>();
    ArrayList <Usser> ussers=new ArrayList<>();
    TextView loginText,nomCognomsText,guanysText;
    Button filtreDia,filtrePreu,filtreDireccio;
    Usser usuari;
    Button perfil;
    Context c=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_pantalla);
        try {
            busca();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        filtreDia=findViewById(R.id.filtreDia);
        filtreDireccio=findViewById(R.id.filtreDireccio);
        filtrePreu=findViewById(R.id.filtrePreu);
        filtreDireccio.setOnClickListener(this);
        filtrePreu.setOnClickListener(this);
        filtreDia.setOnClickListener(this);
        Bundle b =getIntent().getExtras();

        usuari=b.getParcelable("User");
        iniciarTextView();
        RecyclerView vista=(RecyclerView) findViewById(R.id.cercaBicis);
        vista.setAdapter(new AdapterCiclo(ussers,bicis,PrimeraPantalla.this));
        vista.setLayoutManager(new LinearLayoutManager(this));
        perfil=findViewById(R.id.editPerfil);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, PerfilUsuari.class);
                int id= usuari.getIdUser();
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

    }

    private void iniciarTextView() {
        loginText=findViewById(R.id.textPerfil);
        loginText.setText(usuari.getLogin());

        nomCognomsText=findViewById(R.id.nomCognomsPrimera);
        nomCognomsText.setText(usuari.getNom()+" "+usuari.getCognom1()+" "+usuari.getCognom2());

        guanysText=findViewById(R.id.guanysPerfilUsuari);
        guanysText.setText("55.05"+" €");
    }
    private void busca() throws InterruptedException {
        //recollim els filtres
        String filtre="null,null,null";
        this.bicis=new Bici().bicisPrimeraPag(filtre);
        /*this.bicis.add(new Bici("Orbea",1, 1, "Bicicleta de montaña", "Montaña", 1));
        this.bicis.add(new Bici("Giant",2, 1, "Bicicleta de carretera", "Carretera", 1));
        this.bicis.add(new Bici("Giant",3, 2, "Bicicleta híbrida", "Híbrida", 1));
        this.bicis.add(new Bici("Orbea",4, 3, "Bicicleta eléctrica", "Eléctrica", 1));*/

    }

    @Override
    public void onClick(View v) {
        if(v==filtreDia){

        }else if(v==filtreDireccio){

        }else if(v==filtrePreu){

        }
    }
}