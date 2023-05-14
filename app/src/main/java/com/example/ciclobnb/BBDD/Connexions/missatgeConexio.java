package com.example.ciclobnb.BBDD.Connexions;

import android.util.Log;

import com.example.ciclobnb.BBDD.ConnectBBdd;
import com.example.ciclobnb.Missatges;
import com.example.ciclobnb.Objectes.Xat.Missatge;
import com.example.ciclobnb.Objectes.Xat.Xat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class missatgeConexio {
    private Connection cn=null;
    private final ConnectBBdd conexio = new ConnectBBdd();
    public ArrayList<Missatge> getAllMisatges(Xat xat) throws InterruptedException {
        ArrayList<Missatge> missatges = new ArrayList<>();
        Thread fil=new Thread(new Runnable() {
            java.sql.Statement stm = null;
            ResultSet rs = null;
            String missatge,timeStamp;
            int idEmisor,idMissatge;
            @Override
            public void run() {
                try {

                    String sql= "SELECT  Missatge, IdUsuari ,Hora, idMissatge FROM `ciclobnbDB`.`missatges` " +
                            "INNER JOIN xat ON xat.IdXat = '"+xat.getIdXat()+"'" +
                            "ORDER BY `idMissatge`;";
                    cn=conexio.execute().get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    while(rs.next()){
                        missatge=rs.getString(1);
                        idEmisor=rs.getInt(2);
                        timeStamp=rs.getString(3);
                        idMissatge=rs.getInt(4);
                        missatges.add(new Missatge(idMissatge,missatge,idEmisor,timeStamp));
                    }
                    Log.d("userLlegit", ""+missatges.size());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        cn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Log.d("XatError", e.getMessage());
                    }
                }
            }
        });
        fil.start();
        fil.join();
        return missatges;
    }
}
