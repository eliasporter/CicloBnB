package com.example.ciclobnb.BBDD.Connexions;

import android.util.Log;

import com.example.ciclobnb.BBDD.ConnectBBdd;
import com.example.ciclobnb.Objectes.BicisLlogater;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BicisLlogaterConnection {
    private final ConnectBBdd connectBBdd;
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
}
