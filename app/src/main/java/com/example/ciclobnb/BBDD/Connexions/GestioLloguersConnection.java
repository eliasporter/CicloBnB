package com.example.ciclobnb.BBDD.Connexions;

import com.example.ciclobnb.BBDD.ConnectBBdd;
import com.example.ciclobnb.Objectes.GestioLloguers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GestioLloguersConnection {
    private Connection connection;
    private final ConnectBBdd connectBBdd;

    public GestioLloguersConnection(){
        connectBBdd = new ConnectBBdd();
    }

    public double GetByUserID(int IDUser) {
        final double[] result = {0};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Statement stm = null;
                ResultSet rs = null;
                try {
                    connectBBdd.execute();
                    connection = connectBBdd.get();
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
}
