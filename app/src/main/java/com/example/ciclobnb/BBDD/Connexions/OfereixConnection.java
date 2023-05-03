package com.example.ciclobnb.BBDD.Connexions;

import android.util.Log;

import com.example.ciclobnb.BBDD.ConnectBBdd;
import com.example.ciclobnb.Objectes.Bici;
import com.example.ciclobnb.Objectes.Direccio;
import com.example.ciclobnb.Objectes.Disponibilitat;
import com.example.ciclobnb.Objectes.Ofereix;
import com.example.ciclobnb.Objectes.Usser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OfereixConnection {
    private final ConnectBBdd connectBBdd;
    public OfereixConnection(){connectBBdd = new ConnectBBdd();}

    public ArrayList<Ofereix> SearchFor(Integer id){
        ArrayList<Ofereix> ofereixes = new ArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Statement stm = null;
                ResultSet rs = null;
                try{
                    Log.d("LOGOfereix", "Siu");
                    connectBBdd.execute();
                    Connection connection = connectBBdd.get();
                    stm = connection.createStatement();
                    rs=stm.executeQuery("SELECT * " +
                            "FROM bicicletes b " +
                            "INNER JOIN ofereix o ON b.IdBicicleta = o.IdBici " +
                            "INNER JOIN disponibilitat d ON o.IdDispo = d.IdDisponibilitat " +
                            "INNER JOIN usuaris u ON o.IdUsuari = u.IdUsuari " +
                            "INNER JOIN direccio di ON di.IdDireccio = b.IdDireccio " +
                            "WHERE u.IdUsuari !="+id+";");
                    while(rs.next()){
                        ofereixes.add(new Ofereix(rs.getInt("IdOfereix"),rs.getInt("IdUsuari"),rs.getInt("IdBici"),rs.getInt("IdDispo")));
                        ofereixes.get(ofereixes.size()-1).disponibilitats.add(new Disponibilitat(rs.getInt("IdDisponibilitat"),rs.getDate("DataInici"),rs.getDate("DataFi"),rs.getDouble("Preu")));
                        ofereixes.get(ofereixes.size()-1).bicis.add(new Bici(rs.getInt("IdBicicleta"),rs.getString("Descripcio"), rs.getString("Tipus"),rs.getInt("IdDireccio"), rs.getString("marca"), rs.getString("modelo"), rs.getString("suspension")));
                        ofereixes.get(ofereixes.size()-1).bicis.get(ofereixes.get(ofereixes.size()-1).bicis.size()-1).direccio = new Direccio(rs.getInt("IdDireccio"),rs.getString("TipusVia"),rs.getString("NomCarrer"),rs.getString("Numero"),rs.getString("Pis"),rs.getInt("IdCP"));
                        ofereixes.get(ofereixes.size()-1).ussers.add(new Usser(rs.getInt("IdUsuari"),rs.getString("Nom"), rs.getString("Cognom1"),  rs.getString("Cognom2"), rs.getString("Login"), rs.getString("Contrasenya"), rs.getDate("DataNaixement"), rs.getString("CorreuElectronic"), rs.getBoolean("CompteActiu")));
                    }
                    Log.d("LOGOfereix", "Salio");
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
        return ofereixes;
    }
}
