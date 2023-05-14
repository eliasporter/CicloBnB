package com.example.ciclobnb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.ciclobnb.Objectes.Adapter.AdapterMissatges;
import com.example.ciclobnb.Objectes.Usser;
import com.example.ciclobnb.Objectes.Xat.Missatge;
import com.example.ciclobnb.Objectes.Xat.Xat;

import java.sql.SQLException;
import java.util.ArrayList;

public class Missatges extends AppCompatActivity {
    RecyclerView llista;
    ArrayList<Missatge> missatges = new ArrayList<>();
    Button enviar;
    Usser user;
    Xat xat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missatges);
        Bundle b = getIntent().getExtras();
            user=b.getParcelable("User");


            xat=b.getParcelable("Xat");

        missatges=xat.getMissatges();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    missatges=xat.getMissatges();
                    //recareguem cada 0,5 s
                    try {
                        Thread.currentThread().sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        llista =findViewById(R.id.recyclerMissatges);
        llista.setAdapter(new AdapterMissatges(missatges,this,user));
        llista.setLayoutManager(new LinearLayoutManager(this));
    }
}