package com.example.ciclobnb.Objectes;

import android.graphics.Bitmap;
import android.util.Log;

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

    public Bici(String marca,int idBicicleta, int idUser, String descripcio, String tipus, int idDireccio) {
        this.IdBicicleta = idBicicleta;
        this.Descripcio = descripcio;
        this.Tipus = tipus;
        this.idDireccio = idDireccio;
        this.idUser= idUser;
        this.foto="bici_foto.png";
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
            String sDate1="31-12-1998";
            String sDate2="11-12-1999";
            Date inici=new SimpleDateFormat("dd-MM-yyyy").parse(sDate1);
            Date fi=new SimpleDateFormat("dd-MM-yyyy").parse(sDate2);
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
}
