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
import java.util.concurrent.ExecutionException;

public class missatgeConexio {
    java.sql.Statement stm;
    ResultSet rs;
    private Connection cn=null;
    private final ConnectBBdd conexio = new ConnectBBdd();
    public missatgeConexio()  {
        try {

            if(cn!=null)
                cn=null;
            this.cn=conexio.execute().get();

        }catch (ExecutionException | InterruptedException e) {

        }
    }
    public ArrayList<Missatge> getAllMisatges(Xat xat) throws InterruptedException  {
        ArrayList<Missatge> missatges = new ArrayList<>();

        Thread fil=new Thread(new Runnable() {
            java.sql.Statement stm = null;
            ResultSet rs = null;
            String missatge,timeStamp;
            int idEmisor,idMissatge;
            @Override
            public void run() {
                try {

                    String sql= "SELECT  Missatge, IdUsuari ,Hora, idMissatge FROM `ciclobnbDB`.`missatges` where idXat='"+xat.getIdXat()+"' "+
                            "ORDER BY `idMissatge`;";
                    //cn=conexio.execute().get();
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
                        if(cn!=null)
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
    public boolean insertMissatge(Missatge m) throws InterruptedException {
        final boolean[] hecho = {false};
        if(cn==null){
            try {
                cn=conexio.execute().get();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                stm = null;
                rs = null;
                try {
                    String sql= "INSERT INTO `ciclobnbDB`.`missatges` (`IdXat`, `IdUsuari`, `Missatge`, `Hora`) VALUES ("+m.idXat+", "+m.getEmisor()+", '"+m.getMissatge()+"', '"+m.getTimeStamp()+"');";
                    //cn=conexio.execute().get();
                    stm = cn.createStatement();
                    if (stm.executeUpdate(sql) > 0) hecho[0] = true;
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (stm != null) {
                        try {
                            stm.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (cn != null) {
                        try {
                            cn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.start();
        thread.join();
        return hecho[0];

    }


}
