package com.example.ciclobnb.Objectes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.example.ciclobnb.BBDD.ConnectBBdd;
import com.example.ciclobnb.BBDD.Connexions.ConnexioDireccio;
import com.example.ciclobnb.BBDD.Connexions.UserConnection;
import com.example.ciclobnb.BBDD.Connexions.XatsConexio;
import com.example.ciclobnb.BBDD.UsserBBDD;
import com.example.ciclobnb.CrearCompte;
import com.example.ciclobnb.Objectes.Xat.Xat;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Usser implements Parcelable {

    private int IdUser;
    private String nom;
    private String cognom1;
    private String cognom2;
    private String login;
    private String contrasenya;
    private Date dataNaixement;
    private String correuElectronic;
    private Boolean actiu;
    private Direccio direccio;
    private final ConnectBBdd conexio = new ConnectBBdd();
    private Connection cn =null;
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public Usser(){}
    //Per a crear un nou usu no caldrà ficar cap id
    public Usser(String nom, String cognom1, String cognom2, String login, String contrasenya, Date dataNaixement, String correuElectronic, boolean actiu) {
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.login = login;
        this.contrasenya = contrasenya;
        this.dataNaixement = dataNaixement;
        this.correuElectronic = correuElectronic;
        this.actiu = actiu;
    }
    public Usser(int idUser,String nom, String cognom1, String cognom2, String login, String contrasenya, Date dataNaixement, String correuElectronic, Boolean actiu) {
        this.IdUser=idUser;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.login = login;
        this.contrasenya = contrasenya;
        this.dataNaixement = dataNaixement;
        this.correuElectronic = correuElectronic;
        this.actiu = actiu;
        try {
            conectar();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected Usser(Parcel in) {
        IdUser = in.readInt();
        nom = in.readString();
        cognom1 = in.readString();
        cognom2 = in.readString();
        login = in.readString();
        contrasenya = in.readString();
        correuElectronic = in.readString();
        byte tmpActiu = in.readByte();
        actiu = tmpActiu == 0 ? null : tmpActiu == 1;
        direccio = in.readParcelable(Direccio.class.getClassLoader());
        try{
            dataNaixement = dateFormat.parse(in.readString());
        } catch (ParseException e) {e.printStackTrace();}
    }

    public static final Creator<Usser> CREATOR = new Creator<Usser>() {
        @Override
        public Usser createFromParcel(Parcel in) {
            return new Usser(in);
        }

        @Override
        public Usser[] newArray(int size) {
            return new Usser[size];
        }
    };

    public void conectar() throws SQLException, ClassNotFoundException, ExecutionException, InterruptedException {
        cn=  conexio.execute().get();
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom1() {
        return cognom1;
    }

    public void setCognom1(String cognom1) {
        this.cognom1 = cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cognom2) {
        this.cognom2 = cognom2;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getDataNaixement() {
        dateFormat.applyPattern("yyyy-MM-dd");
        return dateFormat.format(dataNaixement);
    }

    public void setDataNaixement(Date dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public String getCorreuElectronic() {
        return correuElectronic;
    }

    public void setCorreuElectronic(String correuElectronic) {
        this.correuElectronic = correuElectronic;
    }

    public Boolean getActiu() {
        return actiu;
    }

    public void setActiu(Boolean actiu) {
        this.actiu = actiu;
    }

    public Direccio getDireccio(){return direccio;}
    public void setDireccio(Direccio direccio){this.direccio = direccio;}

    public Usser getUserPerId(int id) throws SQLException, InterruptedException {
        return new UserConnection().getUserPerId(id);
    }

    public String Hash(String contrasenya){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(contrasenya.getBytes(StandardCharsets.UTF_8));
            return String.format("%064x", new BigInteger(1, hash));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    public Usser Login(String user, String password) throws SQLException, InterruptedException {
        return new UserConnection().Login(user,password);
    }

    public boolean insertUser() throws InterruptedException {
        return new UserConnection().insertUser(this);
    }
    public boolean updateUser() throws InterruptedException {
        return new UserConnection().updateUser(this);
    }

    public ArrayList<Xat> getXats() throws SQLException, InterruptedException {
        return new XatsConexio().getXatsPerUser(this);
    }

    public ArrayList<String> Buscador(String query, Integer columnName) throws InterruptedException {
        return new UserConnection().Buscador(query,columnName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(IdUser);
        dest.writeString(nom);
        dest.writeString(cognom1);
        dest.writeString(cognom2);
        dest.writeString(login);
        dest.writeString(contrasenya);
        dest.writeString(correuElectronic);
        dest.writeByte((byte) (actiu == null ? 0 : actiu ? 1 : 2));
        dest.writeParcelable(direccio, flags);
        dest.writeString(dateFormat.format(dataNaixement));
    }
}
