package com.example.ciclobnb.Objectes;

import java.util.Date;

public class BicisLlogater {
    private int idBL;
    private int idBici;
    private int idLloguer;
    private Date dataInici;
    private Date dataFi;
    private double preu;
    public BicisLlogater(){}
    public BicisLlogater(int idBl, int idBici, int idLloguer, Date dataInici, Date dataFi, double preu){
        this.idBL = idBl;
        this.idBici = idBici;
        this.idLloguer = idLloguer;
        this.dataInici = dataInici;
        this.dataFi = dataFi;
        this.preu = preu;
    }
    public BicisLlogater(int idBici, int idLloguer, Date dataInici, Date dataFi, double preu){
        this.idBici = idBici;
        this.idLloguer = idLloguer;
        this.dataInici = dataInici;
        this.dataFi = dataFi;
        this.preu = preu;
    }
    public int getIdBL() {
        return idBL;
    }
    public void setIdBL(int idBL) {
        this.idBL = idBL;
    }
    public int getIdBici() {
        return idBici;
    }
    public void setIdBici(int idBici) {
        this.idBici = idBici;
    }
    public int getIdLloguer() {
        return idLloguer;
    }
    public void setIdLloguer(int idLloguer) {
        this.idLloguer = idLloguer;
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
}
