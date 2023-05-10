package com.example.ciclobnb.BBDD.Connexions;

import android.util.Log;

import com.example.ciclobnb.BBDD.ConnectBBdd;
import com.example.ciclobnb.Objectes.Usser;
import com.example.ciclobnb.Objectes.Xat.Xat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class XatsConexio {
    private Connection cn=null;
    private final ConnectBBdd conexio = new ConnectBBdd();
    public ArrayList<Xat> getXatsPerUser(Usser u) throws InterruptedException {
        ArrayList<Xat>xats=new ArrayList<>();

        Thread fil=new Thread(new Runnable() {
            java.sql.Statement stm = null;
            ResultSet rs = null;
            int idXat,idUser1,idUser2;
            boolean actiu;
            @Override
            public void run() {
                try {

                    String sql= "SELECT * from `xat` WHERE `IdUsuariPropietari` ='"+u.getIdUser()+"' OR 'IdUsuariLlogador'='"+u.getIdUser()+"';";
                    cn=conexio.execute().get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    while(rs.next()){
                        idXat=rs.getInt(0);
                        idUser1=rs.getInt(1);
                        idUser2=rs.getInt(2);
                        xats.add(new Xat(idXat,idUser1,idUser2));
                    }
                    Log.d("userLlegit", ""+xats.size());
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
        return xats;
    }

}
