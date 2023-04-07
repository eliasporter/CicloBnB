package com.example.ciclobnb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ciclobnb.Objectes.Adapter.AdapterMissatges;
import com.example.ciclobnb.Objectes.Usser;
import com.example.ciclobnb.Objectes.Xat.Missatge;

import java.util.ArrayList;

public class Missatges extends AppCompatActivity {
    RecyclerView llista;
    ArrayList<Missatge> missatges = new ArrayList<>();
    Usser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missatges);
        llista =findViewById(R.id.recyclerMissatges);
        llista.setAdapter(new AdapterMissatges(missatges,this,user));
    }
}