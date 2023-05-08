package com.example.ciclobnb.BBDD.Connexions;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.ciclobnb.BBDD.ConnectBBdd;
import com.example.ciclobnb.Objectes.BicisLlogater;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BicisLlogaterConnection {
    private final ConnectBBdd connectBBdd;
    @SuppressLint("SimpleDateFormat") private final SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    public BicisLlogaterConnection(){connectBBdd = new ConnectBBdd();}
    public ArrayList<BicisLlogater> SearchFor(int idBici){
        ArrayList<BicisLlogater> bicisLlogaters = new ArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Statement stm = null;
                ResultSet rs = null;
                try{
                    Log.d("LOGOfereix", "Comenzando busqueda bicisllogater");
                    connectBBdd.execute();
                    Connection connection = connectBBdd.get();
                    stm = connection.createStatement();
                    rs=stm.executeQuery("SELECT * FROM bicisllogater " +
                            "WHERE IdBici = " + idBici + ";");
                    while(rs.next()){
                        bicisLlogaters.add(new BicisLlogater(rs.getInt("IdBL"),rs.getInt("IdBici"),rs.getInt("IdLloguer"),rs.getDate("DataInici"),rs.getDate("DataFi"),rs.getDouble("Preu")));
                    }
                    Log.d("LOGOfereix", "Busqueda bicisllogater terminada");
                    connection.close();
                    rs.close();
                    stm.close();
                } catch (Exception e){e.printStackTrace();}
            }
        });
        thread.start();
        try{
            thread.join();
        }catch (InterruptedException e){e.printStackTrace();}
        return bicisLlogaters;
    }

    public boolean InsertNew(int idUser, BicisLlogater bicisLlogater){
        final boolean[] hecho = {false};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Statement stm = null;
                ResultSet rs = null;
                try{
                    connectBBdd.execute();
                    Connection connection = connectBBdd.get();
                    stm = connection.createStatement();
                    rs=stm.executeQuery("SELECT MAX(IdLloguer) FROM gestiolloguers " +
                            "WHERE IdUsuari = " + idUser + ";");
                    rs.next();
                    int idBL = rs.getInt(1);
                    if (stm.executeUpdate("INSERT INTO bicisllogater (IdBici, IdLloguer, DataInici, DataFi, Preu) VALUES("+bicisLlogater.getIdBici()+","+idBL+",'"+date.format(bicisLlogater.getDataInici())+"','"+date.format(bicisLlogater.getDataFi())+"',"+bicisLlogater.getPreu()+");")>0) hecho[0]=true;
                    connection.close();
                    rs.close();
                    stm.close();
                } catch (Exception e){e.printStackTrace();}
            }
        });
        thread.start();
        try{
            thread.join();
        }catch (InterruptedException e){e.printStackTrace();}
        return hecho[0];
    }
}
