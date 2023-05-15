package com.example.ciclobnb.BBDD.Connexions;

import android.annotation.SuppressLint;

import com.example.ciclobnb.BBDD.ConnectBBdd;
import com.example.ciclobnb.Objectes.BicisLlogater;
import com.example.ciclobnb.Objectes.GestioLloguers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class GestioLloguersConnection {
    private Connection connection;
    private final ConnectBBdd connectBBdd;
    @SuppressLint("SimpleDateFormat") private final SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

    public GestioLloguersConnection(){
        connectBBdd = new ConnectBBdd();
        try {
            this.connection=connectBBdd.execute().get();
        }catch (ExecutionException | InterruptedException e){

        }
    }

    public double GetByUserID(int IDUser) {
        final double[] result = {0};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Statement stm = null;
                ResultSet rs = null;
                try {
                    /*connectBBdd.execute();
                    connection = connectBBdd.get();*/
                    stm = connection.createStatement();
                    rs=stm.executeQuery("SELECT Total FROM gestiolloguers WHERE IdLloguer = " + IDUser + ";");
                    while(rs.next()){
                        result[0] += rs.getInt("Total");
                    }
                    connection.close();
                    rs.close();
                    stm.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {e.printStackTrace();}

        return result[0];
    }

    public boolean InsertNew(GestioLloguers gestioLloguers){
        final boolean[] hecho = {false};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Statement stm = null;
                try {
                    /*connectBBdd.execute();
                    connection = connectBBdd.get();*/
                    stm = connection.createStatement();
                    if (stm.executeUpdate("INSERT INTO gestiolloguers(Total, IdUsuari, DataPagament) VALUES ("+gestioLloguers.getTotal()+","+gestioLloguers.getIdUsuari()+", '"+date.format(System.currentTimeMillis())+"');") > 0) hecho[0]=true;
                    connection.close();
                    stm.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {e.printStackTrace();}
        return hecho[0];
    }
}
