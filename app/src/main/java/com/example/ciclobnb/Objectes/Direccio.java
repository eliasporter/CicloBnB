package com.example.ciclobnb.Objectes;

import com.example.ciclobnb.BBDD.Connexions.ConnexioDireccio;

public class Direccio {
    public int idDireccion;
    public String tipusVia;
    public String nomCarrer;
    public String numero;
    public String pis;
    public int idCP;
    private ConnexioDireccio connexioDireccio=new ConnexioDireccio();
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

    public int BuscarID(String cp) throws InterruptedException {
        return connexioDireccio.BuscarID(cp);
    }

    public String AgafaUltima() throws InterruptedException {
        return connexioDireccio.agafaUltima();
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


    public void InsertarNuevo(Direccio temp) {
        try {
            new ConnexioDireccio().SubirDireccion(temp);

        }catch (InterruptedException e){

        }
    }
}
