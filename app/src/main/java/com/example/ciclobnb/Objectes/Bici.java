package com.example.ciclobnb.Objectes;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.ciclobnb.BBDD.ConnectBBdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Bici {

    private int IdBicicleta;
    private int idUser;
    private String marca;
    private String Descripcio;
    private String Tipus;
    private int idDireccio;
    private double preu;
    private String foto;
    private ArrayList<Disponibilitat> disponibilitats = new ArrayList<>();
    private ConnectBBdd conexio = new ConnectBBdd();
    private Connection cn =null;

    public Bici(String marca,int idBicicleta, int idUser, String descripcio, String tipus, int idDireccio) {
        this.IdBicicleta = idBicicleta;
        this.Descripcio = descripcio;
        this.Tipus = tipus;
        this.idDireccio = idDireccio;
        this.idUser= idUser;
        this.foto="bici_foto.png";
        this.marca=marca;
        /*try {
            conectarBD();
        }catch (Exception e){

        }*/
    }
    private void conectarBD() throws SQLException {
        cn =conexio.conectar();
    }

    public int getIdBicicleta() {
        return IdBicicleta;
    }

    public void setIdBicicleta(int idBicicleta) {
        IdBicicleta = idBicicleta;
    }

    public String getDescripcio() {
        return Descripcio;
    }

    public void setDescripcio(String descripcio) {
        Descripcio = descripcio;
    }

    public String getTipus() {
        return Tipus;
    }

    public void setTipus(String tipus) {
        Tipus = tipus;
    }

    public int getIdDireccio() {
        return idDireccio;
    }

    public void setIdDireccio(int idDireccio) {
        this.idDireccio = idDireccio;
    }
    public String getUserNom(Collection<Usser> users){
        if(users instanceof ArrayList) {
            for (Usser u : users) {
                if(u.getIdUser()==idUser){
                    return u.getLogin();
                }
            }

        }
        return null;
    }
    public String getFoto(){
        return this.foto;
    }

    public ArrayList<Disponibilitat> getDisponibilitats() {
        try {
            String sDate1="1998-12-31";
            String sDate2="1999-11-12-";
            Date inici=new SimpleDateFormat("dd-MM-yyyy").parse(sDate1);
            Date fi=new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
            Disponibilitat temp = new Disponibilitat(inici,fi,12.32);
            Log.d("dispoooo", temp.toString());
            disponibilitats.add(temp);
            //disponibilitats.add(new Disponibilitat(inici,fi,12.32));

        }catch (ParseException e){
            e.printStackTrace();
        }

        return disponibilitats;
    }

    public String getMarca() {
        return this.marca;
    }

    public ArrayList<Bici> bicisPrimeraPag(){
        ArrayList<Bici>bicis=new ArrayList<>();
        java.sql.Statement stm = null;
        ResultSet rs = null;
        int idBicicleta;
        String marca;
        int idBici;
        int idUser;
        String descripcio;
        String tipus;
        int idDireccio;

        try {
            String sql= "SELECT * from `bicicletes` ";
            cn=conexio.conectar();
            stm = cn.createStatement();
            rs=stm.executeQuery(sql);
            while (rs.next()){
                marca=rs.getString(5);
                idBici=rs.getInt(1);
                idUser=new Usser().getUserPerBici(idBici);//Todo
                descripcio=rs.getString(2);
                tipus=rs.getString(3);
                idDireccio=rs.getInt(4);
                bicis.add(new Bici(marca,idBici, idUser,  descripcio,  tipus, idDireccio));
            }

            Log.d("TotalBicis", ""+bicis.size());
        }catch (Exception e){

        }
        return bicis;
    }

}
