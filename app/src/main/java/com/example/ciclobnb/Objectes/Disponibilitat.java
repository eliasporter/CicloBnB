package com.example.ciclobnb.Objectes;

import java.util.Date;

public class Disponibilitat {
    private Date dataInici;
    private Date dataFi;
    private double preu;


    public Disponibilitat(Date dataInici,Date dataFi,double preu){
        this.dataInici=dataInici;
        this.dataFi=dataFi;
        this.preu=preu;
    }

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

    @Override
    public String toString() {
        return "Disponibilitat{" +
                "dataInici=" + dataInici +
                ", dataFi=" + dataFi +
                ", preu=" + preu +
                '}';
    }
}
