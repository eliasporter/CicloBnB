package com.example.ciclobnb.Objectes;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.ciclobnb.BBDD.ConnectBBdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Bici implements Parcelable {
    private int IdBicicleta;
    private String Descripcio;
    private String Tipus;
    private int idDireccio;
    private String marca;
    private String modelo;
    private String suspension;
    public Direccio direccio;
    public Bici(){ NewDireccio(); }
    public Bici(int IdBicicleta, String Descripcio, String Tipus, int IdDireccio, String marca, String modelo, String suspension) {
        this.IdBicicleta = IdBicicleta;
        this.Descripcio = Descripcio;
        this.Tipus = Tipus;
        this.idDireccio = IdDireccio;
        this.marca=marca;
        this.modelo = modelo;
        this.suspension = suspension;
        NewDireccio();
    }
    public Bici(String Descripcio, String Tipus, int IdDireccio, String marca, String modelo, String suspension) {
        this.Descripcio = Descripcio;
        this.Tipus = Tipus;
        this.idDireccio = IdDireccio;
        this.marca=marca;
        this.modelo = modelo;
        this.suspension = suspension;
        NewDireccio();
    }
    private void NewDireccio(){this.direccio = new Direccio();}
    protected Bici(Parcel in) {
        IdBicicleta = in.readInt();
        Descripcio = in.readString();
        Tipus = in.readString();
        idDireccio = in.readInt();
        marca = in.readString();
        modelo = in.readString();
        suspension = in.readString();
    }
    public static final Creator<Bici> CREATOR = new Creator<Bici>() {
        @Override
        public Bici createFromParcel(Parcel in) {
            return new Bici(in);
        }

        @Override
        public Bici[] newArray(int size) {
            return new Bici[size];
        }
    };
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
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getSuspension() {
        return suspension;
    }
    public void setSuspension(String suspension) {
        this.suspension = suspension;
    }
    public Direccio getDireccio(){return direccio;}
    public void setDireccio(Direccio direccio){this.direccio = direccio;}
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(IdBicicleta);
        dest.writeString(Descripcio);
        dest.writeString(Tipus);
        dest.writeInt(idDireccio);
        dest.writeString(marca);
        dest.writeString(modelo);
        dest.writeString(suspension);
    }
}
