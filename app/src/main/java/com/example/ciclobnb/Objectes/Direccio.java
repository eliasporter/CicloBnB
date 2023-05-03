package com.example.ciclobnb.Objectes;

import android.content.Context;

import com.example.ciclobnb.BBDD.ConnectBBdd;
import com.example.ciclobnb.BBDD.Connexions.ConnexioDireccio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Direccio {
    public int idDireccion;
    public String tipusVia;
    public String nomCarrer;
    public String numero;
    public String pis;
    public int idCP;
    private ConnexioDireccio connexioDireccio;
    public Direccio(int idDireccion, String tipus, String nomCarrer, String numero, String pis, int idCP){
        this.idDireccion = idDireccion; this.tipusVia = tipus; this.nomCarrer = nomCarrer; this.numero = numero; this.pis = pis; this.idCP = idCP;
        connexioDireccio = new ConnexioDireccio();
    }
    public Direccio(String tipus, String nomCarrer, String numero, String pis, int idCP){
        this.tipusVia = tipus; this.nomCarrer = nomCarrer; this.numero = numero; this.pis = pis; this.idCP = idCP;
        connexioDireccio = new ConnexioDireccio();
    }
    public Direccio() { }
    public int getIdDireccion() {
        return idDireccion;
    }
    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }
    public String getTipusVia() {
        return tipusVia;
    }
    public void setTipusVia(String tipusVia) {
        this.tipusVia = tipusVia;
    }
    public String getNomCarrer() {
        return nomCarrer;
    }
    public void setNomCarrer(String nomCarrer) {
        this.nomCarrer = nomCarrer;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getPis() {
        return pis;
    }
    public void setPis(String pis) {
        this.pis = pis;
    }
    public int getIdCP() {
        return idCP;
    }
    public void setIdCP(int idCP) {
        this.idCP = idCP;
    }
}
