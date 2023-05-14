package com.example.ciclobnb.Objectes.Xat;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.core.content.LocusIdCompat;

import com.example.ciclobnb.BBDD.Connexions.XatsConexio;
import com.example.ciclobnb.BBDD.Connexions.missatgeConexio;
import com.example.ciclobnb.Objectes.Usser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Xat implements Parcelable {
    private int idXat;
    private int user1;
    private int user2;


    public Xat(int idXat, int user, int llogater) {
        this.idXat = idXat;
        this.user1 = user;
        this.user2 = llogater;
    }
    public Xat(){}

    protected Xat(Parcel in) {
        idXat = in.readInt();
        user1 = in.readInt();
        user2 = in.readInt();
    }

    public static final Creator<Xat> CREATOR = new Creator<Xat>() {
        @Override
        public Xat createFromParcel(Parcel in) {
            return new Xat(in);
        }

        @Override
        public Xat[] newArray(int size) {
            return new Xat[size];
        }
    };

    public int getIdXat() {
        return idXat;
    }

    public void setIdXat(int idXat) {
        this.idXat = idXat;
    }

    public int getUser1() {
        return user1;
    }

    public void setUser1(int user1) {
        this.user1 = user1;
    }

    public int getUser2() {return user2;}

    public void setUser2(int user2) {
        this.user2 = user2;
    }

    public String getUltimMissatge() throws InterruptedException {
        return new XatsConexio().getUltimMissatge(this);
    }
    public Xat getXAtPerId(int idXat) throws InterruptedException {
       return new XatsConexio().BuscaXatPerId(idXat);
    }
    public int comprovaCreaXat(Usser cli,Usser prop) throws InterruptedException {
        int idXat=0;
        idXat= new XatsConexio().BuscaXat(cli,prop);
        if(idXat==0)
            idXat= creaXat(cli,prop);
        return idXat;

    }
    public int creaXat(Usser cli,Usser prop) throws InterruptedException {
        int idXat=0;
        new XatsConexio().crearXat(cli,prop);
        idXat= new XatsConexio().getUltim();
        return idXat;

    }
    public ArrayList<Missatge> getMissatges ()  {

        try {
            return new missatgeConexio().getAllMisatges(this);
        }catch ( InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(idXat);
        dest.writeInt(user1);
        dest.writeInt(user2);
    }
}
