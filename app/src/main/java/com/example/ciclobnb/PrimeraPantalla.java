package com.example.ciclobnb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.ciclobnb.Objectes.Adapter.AdapterCiclo;
import com.example.ciclobnb.Objectes.Bici;
import com.example.ciclobnb.Objectes.Usser;

import java.util.ArrayList;

public class PrimeraPantalla extends AppCompatActivity {
    ArrayList <Bici> bicis=new ArrayList<>();
    ArrayList <Usser> ussers=new ArrayList<>();
    Button perfil;
    Context c=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_pantalla);
        busca();
        RecyclerView vista=(RecyclerView) findViewById(R.id.cercaBicis);
        vista.setAdapter(new AdapterCiclo(ussers,bicis,PrimeraPantalla.this));
        vista.setLayoutManager(new LinearLayoutManager(this));
        perfil=findViewById(R.id.editPerfil);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(c,PerfilUsuari.class);
                startActivity(i);
            }
        });

    }


    private void busca(){

        this.ussers.add(new Usser(1, "Juan", "Pérez", "García", "juanperez", "1234", "01/01/1990", "juanperez@gmail.com", true));
        this.ussers.add(new Usser(2, "María", "Gómez", "García", "mariagomez", "5678", "02/02/1995", "mariagomez@gmail.com", true));
        this.ussers.add(new Usser(3, "Pedro", "García", "Fernández", "pedrogarcia", "abcd", "03/03/1985", "pedrogarcia@gmail.com", true));
        this.ussers.add(new Usser(4, "Ana", "Fernández", "Ruiz", "anafernandez", "efgh", "04/04/2000", "anafernandez@gmail.com", true));
        this.ussers.add(new Usser(5, "Javier", "López", "González", "javierlopez", "ijkl", "05/05/1992", "javierlopez@gmail.com", true));

        this.bicis.add(new Bici("Orbea",1, 1, "Bicicleta de montaña", "Montaña", 1));
        this.bicis.add(new Bici("Giant",2, 1, "Bicicleta de carretera", "Carretera", 1));
        this.bicis.add(new Bici("Giant",3, 2, "Bicicleta híbrida", "Híbrida", 1));
        this.bicis.add(new Bici("Orbea",4, 3, "Bicicleta eléctrica", "Eléctrica", 1));

    }
}