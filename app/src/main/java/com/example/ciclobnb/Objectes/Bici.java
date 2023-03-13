package com.example.ciclobnb.Objectes;

public class Bici {

    private int IdBicicleta;
    private String Descripcio;
    private String Tipus;
    private int idDireccio;

    public Bici(int idBicicleta, String descripcio, String tipus, int idDireccio) {
        IdBicicleta = idBicicleta;
        Descripcio = descripcio;
        Tipus = tipus;
        this.idDireccio = idDireccio;
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
}
