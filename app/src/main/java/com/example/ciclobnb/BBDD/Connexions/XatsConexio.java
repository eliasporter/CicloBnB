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

                    String sql= "SELECT * from `xat` WHERE `IdUsuariPropietari` ='"+u.getIdUser()+"' OR `IdUsuariLlogador`='"+u.getIdUser()+"';";
                    cn=conexio.execute().get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    while(rs.next()){
                        idXat=rs.getInt(1);
                        idUser1=rs.getInt(2);
                        idUser2=rs.getInt(3);
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
    public int BuscaXat(Usser clie,Usser prop) throws InterruptedException {
        final int[] existeis = new int[1];

        Thread fil=new Thread(new Runnable() {
            java.sql.Statement stm = null;
            ResultSet rs = null;
            //int idXat,idUser1,idUser2;
            @Override
            public void run() {
                try {

                    String sql= "SELECT * from `xat` WHERE " +
                            "(`IdUsuariPropietari` ='"+clie.getIdUser()+"' OR 'IdUsuariLlogador'='"+clie.getIdUser()+"') " +
                            "OR (`IdUsuariPropietari` ='"+clie.getIdUser()+"' OR 'IdUsuariLlogador'='"+clie.getIdUser()+"');";
                    cn=conexio.execute().get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    if(rs.getRow()==0)
                        existeis[0] =0;
                    else
                        rs.next();
                        existeis[0] =rs.getInt(1);
                   // Log.d("userLlegit", ""+xats.size());
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
        return existeis[0];
    }
    public int agafarUltim(){
        return 0;//todo
    }
    public Xat BuscaXatPerId(int idXat) throws InterruptedException {
        final Xat[] xat = new Xat[1];

        Thread fil=new Thread(new Runnable() {
            java.sql.Statement stm = null;
            ResultSet rs = null;
            int idXat,idUser1,idUser2;
            @Override
            public void run() {
                try {

                    String sql= "SELECT * from `xat` WHERE IdXat = "+idXat+";";
                    cn=conexio.execute().get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    rs.next();
                    idXat=rs.getInt(1);
                    idUser1=rs.getInt(2);
                    idUser2=rs.getInt(3);
                    xat[1]= new Xat(idXat,idUser1,idUser2);

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
        return xat[0];
    }
    public int crearXat(Usser clie,Usser prop) throws InterruptedException {//todo
        final boolean[] creat = {false};
        Thread fil=new Thread(new Runnable() {
            java.sql.Statement stm = null;
            int rs = 0;
            //int idXat,idUser1,idUser2;
            @Override
            public void run() {
                try {

                    String sql= "INSERT INTO `ciclobnbDB`.`xat` (`IdUsuariPropietari`, `IdUsuariLlogador`) VALUES ("+clie.getIdUser()+" , "+prop.getIdUser()+") ;";
                    cn=conexio.execute().get();
                    stm = cn.createStatement();
                    rs=stm.executeUpdate(sql);
                    if (rs > 0) {
                        creat[0] =true;
                        Log.d("SsQL", "insertat ");

                    }else{
                        creat[0]=false;
                        Log.d("SsQL", "No insertat ");
                    }

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
        if(creat[0]=true)
            return agafarUltim();
        else
            return 0;
    }
    public int getUltim() throws InterruptedException {
        //SELECT * FROM `ciclobnbDB`.`xat` ORDER BY `IdXat` DESC LIMIT 1000;
        final int[] xat = new int[1];

        Thread fil=new Thread(new Runnable() {
            java.sql.Statement stm = null;
            ResultSet rs = null;
            @Override
            public void run() {
                try {

                    String sql= "SELECT * FROM `ciclobnbDB`.`xat` ORDER BY `IdXat` DESC LIMIT 1;";
                    cn=conexio.execute().get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    rs.next();
                    xat[0]=rs.getInt(1);

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
        return xat[0];

    }
    public String getUltimMissatge(Xat xat) throws InterruptedException {
        final String[] missatge = {""};
        Thread fil=new Thread(new Runnable() {
            java.sql.Statement stm = null;
            ResultSet rs = null;
            int idXat,idUser1,idUser2;
            boolean actiu;
            @Override
            public void run() {
                try {
                    String sql= "SELECT  Missatge,  `Hora` FROM `ciclobnbDB`.`missatges` " +
                            "where IdXat = "+ xat.getIdXat() +
                            " ORDER BY `IdMissatge` DESC LIMIT 1;";
                    cn=conexio.execute().get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    rs.next();
                    missatge[0] =rs.getString(1);
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
        return missatge[0];
    }


}
