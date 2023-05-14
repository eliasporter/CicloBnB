package com.example.ciclobnb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ciclobnb.Objectes.Adapter.AdapterCiclo;
import com.example.ciclobnb.Objectes.Adapter.AdapterXat;
import com.example.ciclobnb.Objectes.Usser;
import com.example.ciclobnb.Objectes.Xat.Xat;

import java.sql.SQLException;
import java.util.ArrayList;

public class XatsAmbPersones extends AppCompatActivity {
    ArrayList<Xat>xats=new ArrayList<>();
    Usser usuari;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xats_amb_persones);
        Bundle b =getIntent().getExtras();
        try {
            usuari=b.getParcelable("User");
            xats=usuari.getXats();
        }catch (SQLException | InterruptedException e){
            e.printStackTrace();
        }

        RecyclerView vista=(RecyclerView) findViewById(R.id.XatsRecicler);
        vista.setAdapter(new AdapterXat(xats,this,usuari));
        vista.setLayoutManager(new LinearLayoutManager(this));
    }
}