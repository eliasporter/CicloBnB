package com.example.ciclobnb.Objectes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.ciclobnb.BBDD.Connexions.ConnexioDireccio;

public class Direccio implements Parcelable {
    private int idDireccion;
    private String tipusVia;
    private String nomCarrer;
    private String numero;
    private String pis;
    private int idCP;
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

    protected Direccio(Parcel in) {
        idDireccion = in.readInt();
        tipusVia = in.readString();
        nomCarrer = in.readString();
        numero = in.readString();
        pis = in.readString();
        idCP = in.readInt();
    }

    public static final Creator<Direccio> CREATOR = new Creator<Direccio>() {
        @Override
        public Direccio createFromParcel(Parcel in) {
            return new Direccio(in);
        }

        @Override
        public Direccio[] newArray(int size) {
            return new Direccio[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(idDireccion);
        dest.writeString(tipusVia);
        dest.writeString(nomCarrer);
        dest.writeString(numero);
        dest.writeString(pis);
        dest.writeInt(idCP);
    }
}
