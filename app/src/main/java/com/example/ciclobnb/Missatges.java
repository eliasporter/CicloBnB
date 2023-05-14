package com.example.ciclobnb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ciclobnb.Objectes.Adapter.AdapterMissatges;
import com.example.ciclobnb.Objectes.Usser;
import com.example.ciclobnb.Objectes.Xat.Missatge;
import com.example.ciclobnb.Objectes.Xat.Xat;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Missatges extends AppCompatActivity implements View.OnClickListener{
    RecyclerView llista;
    ArrayList<Missatge> missatges = new ArrayList<>();
    Button enviar;
    Usser user;
    EditText textMiss;
    Xat xat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missatges);
        enviar= findViewById(R.id.EnviarMissatge);
        enviar.setOnClickListener(this);
        textMiss=findViewById(R.id.message_edit_text);
        Bundle b = getIntent().getExtras();
            user=b.getParcelable("User");

            xat=b.getParcelable("Xat");

        missatges=xat.getMissatges();
        llista =findViewById(R.id.recyclerMissatges);
        llista.setAdapter(new AdapterMissatges(missatges,this,user));
        llista.setLayoutManager(new LinearLayoutManager(this));
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                    ficarAdapter();
                    //recareguem cada 0,5 s
                    try {
                        Thread.currentThread().sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    private void ficarAdapter() {
        missatges=xat.getMissatges();
        //tot i que estiguem en un altre fil ho executarem en el principal
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AdapterMissatges adapter = new AdapterMissatges(missatges, Missatges.this, user);
                llista.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

    }
    private void enviarMissatge(){
        Date d=new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String data = formatoFecha.format(d);
        Missatge temp = new Missatge(textMiss.getText().toString(),user.getIdUser(),data,xat.getIdXat());
        temp.insertMissatge();
        textMiss.setText("");
    }
    @Override
    public void onClick(View v) {
        if(v==enviar){
            enviarMissatge();
        }
    }
}