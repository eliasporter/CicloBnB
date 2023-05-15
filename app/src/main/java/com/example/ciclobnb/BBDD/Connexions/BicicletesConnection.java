package com.example.ciclobnb.BBDD.Connexions;

import com.example.ciclobnb.BBDD.ConnectBBdd;
import com.example.ciclobnb.Objectes.Bici;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class BicicletesConnection {
    private final ConnectBBdd connectBBdd;
    private Connection connection;
    public BicicletesConnection(){

        connectBBdd = new ConnectBBdd();
        try {
            this.connection=connectBBdd.execute().get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Bici> SearchForBike(Integer Id) {
        ArrayList<Bici> bikes = new ArrayList<>();
        Thread thread =  new Thread(new Runnable() {
            @Override
            public void run() {
                Statement stm = null;
                ResultSet rs = null;
                try{
                    connectBBdd.execute();
                    Connection connection = connectBBdd.get();
                    stm = connection.createStatement();
                    rs=stm.executeQuery("SELECT b.IdBicicleta, b.marca, b.Descripcio, b.Tipus, d.Preu, b.IdDireccio, b.modelo, b.suspension " +
                            "FROM bicicletes b " +
                            "INNER JOIN ofereix o ON b.IdBicicleta = o.IdBici " +
                            "INNER JOIN disponibilitat d ON o.IdDispo = d.IdDisponibilitat " +
                            "INNER JOIN usuaris u ON o.IdUsuari = u.IdUsuari " +
                            "WHERE u.IdUsuari !="+Id+";");
                    while(rs.next()){
                        bikes.add(new Bici(rs.getInt("IdBicicleta"),rs.getString("Descripcio"), rs.getString("Tipus"),rs.getInt("IdDireccio"), rs.getString("marca"), rs.getString("modelo"), rs.getString("suspension")));
                    }
                    connection.close();
                    rs.close();
                    stm.close();
                } catch (Exception e){e.printStackTrace();}
            }
        });
        thread.start();
        try{
            thread.join();
        } catch (InterruptedException e){e.printStackTrace();}
        return bikes;
    }
}
