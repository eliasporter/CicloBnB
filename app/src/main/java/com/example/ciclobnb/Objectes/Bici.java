package com.example.ciclobnb.Objectes;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Collection;

public class Bici {

    private int IdBicicleta;
    private int idUser;
    private String Descripcio;
    private String Tipus;
    private int idDireccio;
    private String foto;

    public Bici(int idBicicleta, int idUser, String descripcio, String tipus, int idDireccio) {
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
}
