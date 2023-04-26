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
    public String tipus;
    public String nomCarrer;
    public String numero;
    public String pis;
    public int idCP;
    private ConnexioDireccio connexioDireccio;
    public Direccio(int idDireccion, String tipus, String nomCarrer, String numero, String pis, int idCP){
        this.idDireccion = idDireccion; this.tipus = tipus; this.nomCarrer = nomCarrer; this.numero = numero; this.pis = pis; this.idCP = idCP;
        connexioDireccio = new ConnexioDireccio();
    }

    public Direccio(String tipus, String nomCarrer, String numero, String pis, int idCP){
        this.tipus = tipus; this.nomCarrer = nomCarrer; this.numero = numero; this.pis = pis; this.idCP = idCP;
        connexioDireccio = new ConnexioDireccio();
    }

    public Direccio() { }

    public void InsertarNuevo(Direccio direccio) throws InterruptedException {
        connexioDireccio.SubirDireccion(direccio);
    }

    public void ActualizarDireccion(Direccio direccio) throws InterruptedException {
        connexioDireccio.Actualizar(direccio);
    }

    public int BuscarID() throws InterruptedException {
        return connexioDireccio.BuscarID(idCP);
    }
}
