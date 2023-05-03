package com.example.ciclobnb.Objectes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Disponibilitat implements Parcelable {
    private Integer idDisponibilitat;
    private Date dataInici;
    private Date dataFi;
    private double preu;
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

    public Date getDataInici() {
        return dataInici;
    }
    public void setDataInici(Date dataInici) {
        this.dataInici = dataInici;
    }
    public Date getDataFi() {
        return dataFi;
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
        dest.writeDouble(preu);
    }
}
