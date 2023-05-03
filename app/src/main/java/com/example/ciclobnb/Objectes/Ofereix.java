package com.example.ciclobnb.Objectes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Ofereix implements Parcelable {
    private Integer idOfereix;
    private Integer idUsuari;
    private Integer idBici;
    private Integer idDispo;
    public ArrayList<Disponibilitat> disponibilitats;
    public ArrayList<Bici> bicis;
    public ArrayList<Usser> ussers;

    protected Ofereix(Parcel in) {
        if (in.readByte() == 0) {
            idOfereix = null;
        } else {
            idOfereix = in.readInt();
        }
        if (in.readByte() == 0) {
            idUsuari = null;
        } else {
            idUsuari = in.readInt();
        }
        if (in.readByte() == 0) {
            idBici = null;
        } else {
            idBici = in.readInt();
        }
        if (in.readByte() == 0) {
            idDispo = null;
        } else {
            idDispo = in.readInt();
        }
        bicis = in.createTypedArrayList(Bici.CREATOR);
        ussers = in.createTypedArrayList(Usser.CREATOR);
    }

    public static final Creator<Ofereix> CREATOR = new Creator<Ofereix>() {
        @Override
        public Ofereix createFromParcel(Parcel in) {
            return new Ofereix(in);
        }

        @Override
        public Ofereix[] newArray(int size) {
            return new Ofereix[size];
        }
    };

    private void OpenUp(){
        disponibilitats = new ArrayList<>();
        bicis = new ArrayList<>();
        ussers = new ArrayList<>();
    }
    public Ofereix(Integer idOfereix, Integer idUsuari, Integer idBici, Integer idDispo) {
        this.idOfereix = idOfereix;
        this.idUsuari = idUsuari;
        this.idBici = idBici;
        this.idDispo = idDispo;
        OpenUp();
    }
    public Ofereix(Integer idUsuari, Integer idBici, Integer idDispo) {
        this.idUsuari = idUsuari;
        this.idBici = idBici;
        this.idDispo = idDispo;
        OpenUp();
    }
    public Ofereix(){OpenUp();}
    public Integer getIdOfereix() {
        return idOfereix;
    }
    public void setIdOfereix(Integer idOfereix) {
        this.idOfereix = idOfereix;
    }
    public Integer getIdUsuari() {
        return idUsuari;
    }
    public void setIdUsuari(Integer idUsuari) {
        this.idUsuari = idUsuari;
    }
    public Integer getIdBici() {
        return idBici;
    }
    public void setIdBici(Integer idBici) {
        this.idBici = idBici;
    }
    public Integer getIdDispo() {
        return idDispo;
    }
    public void setIdDispo(Integer idDispo) {
        this.idDispo = idDispo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (idOfereix == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idOfereix);
        }
        if (idUsuari == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idUsuari);
        }
        if (idBici == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idBici);
        }
        if (idDispo == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idDispo);
        }
        dest.writeTypedList(bicis);
        dest.writeTypedList(ussers);
    }
}
