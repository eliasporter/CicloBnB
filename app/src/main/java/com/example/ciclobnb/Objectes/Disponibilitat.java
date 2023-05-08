package com.example.ciclobnb.Objectes;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Disponibilitat implements Parcelable {
    private Integer idDisponibilitat;
    private Date dataInici;
    private Date dataFi;
    private double preu;
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat  dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public Disponibilitat(){}
    public Disponibilitat(Date dataInici,Date dataFi,double preu){
        this.dataInici=dataInici;
        this.dataFi=dataFi;
        this.preu=preu;
    }
    public Disponibilitat(Integer idDisponibilitat, Date dataInici, Date dataFi, double preu){
        this.idDisponibilitat = idDisponibilitat;
        this.dataInici = dataInici;
        this.dataFi = dataFi;
        this.preu = preu;
    }

    protected Disponibilitat(Parcel in) {
        if (in.readByte() == 0) {
            idDisponibilitat = null;
        } else {
            idDisponibilitat = in.readInt();
        }
        try {
            dataInici = dateFormat.parse(in.readString());
            dataFi = dateFormat.parse(in.readString());
        } catch (ParseException e) {e.printStackTrace();}
        preu = in.readDouble();
    }

    public static final Creator<Disponibilitat> CREATOR = new Creator<Disponibilitat>() {
        @Override
        public Disponibilitat createFromParcel(Parcel in) {
            return new Disponibilitat(in);
        }

        @Override
        public Disponibilitat[] newArray(int size) {
            return new Disponibilitat[size];
        }
    };

    public String getDataInici() {
        dateFormat.applyPattern("yyyy-MM-dd");
        return dateFormat.format(dataInici);
    }
    public void setDataInici(Date dataInici) {
        this.dataInici = dataInici;
    }
    public String getDataFi() {
        dateFormat.applyPattern("yyyy-MM-dd");
        return dateFormat.format(dataFi);
    }
    public void setDataFi(Date dataFi) {
        this.dataFi = dataFi;
    }
    public double getPreu() {
        return preu;
    }
    public void setPreu(double preu) {
        this.preu = preu;
    }
    public Integer getIdDisponibilitat() {
        return idDisponibilitat;
    }
    public void setIdDisponibilitat(Integer idDisponibilitat) {
        this.idDisponibilitat = idDisponibilitat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (idDisponibilitat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idDisponibilitat);
        }
        dest.writeString(dateFormat.format(dataInici));
        String f = dateFormat.format(dataInici);
        dest.writeString(dateFormat.format(dataFi));
        dest.writeDouble(preu);
    }
}
