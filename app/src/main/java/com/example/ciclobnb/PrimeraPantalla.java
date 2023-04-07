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

import java.util.ArrayList;

public class PrimeraPantalla extends AppCompatActivity {
    ArrayList <Bici> bicis=new ArrayList<>();
    ArrayList <Usser> ussers=new ArrayList<>();
    TextView loginText,nomCognomsText,guanysText;
    Usser usuari;
    Button perfil;
    Context c=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_pantalla);
        busca();
        Bundle b =getIntent().getExtras();
        usuari=new Usser().getUserPerId(b.getInt("id"));
        iniciarTextView();
        RecyclerView vista=(RecyclerView) findViewById(R.id.cercaBicis);
        vista.setAdapter(new AdapterCiclo(ussers,bicis,PrimeraPantalla.this));
        vista.setLayoutManager(new LinearLayoutManager(this));
        perfil=findViewById(R.id.editPerfil);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, PerfilUsuari.class);
                intent.putExtra("id",usuari.getIdUser());
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
    private void busca(){

        this.ussers.add(new Usser("Juan", "Pérez", "García", "juanperez", "1234", "01/01/1990", "juanperez@gmail.com", true));
        this.ussers.add(new Usser( "María", "Gómez", "García", "mariagomez", "5678", "02/02/1995", "mariagomez@gmail.com", true));
        this.ussers.add(new Usser( "Pedro", "García", "Fernández", "pedrogarcia", "abcd", "03/03/1985", "pedrogarcia@gmail.com", true));
        this.ussers.add(new Usser( "Ana", "Fernández", "Ruiz", "anafernandez", "efgh", "04/04/2000", "anafernandez@gmail.com", true));
        this.ussers.add(new Usser( "Javier", "López", "González", "javierlopez", "ijkl", "05/05/1992", "javierlopez@gmail.com", true));

        this.bicis.add(new Bici("Orbea",1, 1, "Bicicleta de montaña", "Montaña", 1));
        this.bicis.add(new Bici("Giant",2, 1, "Bicicleta de carretera", "Carretera", 1));
        this.bicis.add(new Bici("Giant",3, 2, "Bicicleta híbrida", "Híbrida", 1));
        this.bicis.add(new Bici("Orbea",4, 3, "Bicicleta eléctrica", "Eléctrica", 1));

    }
}