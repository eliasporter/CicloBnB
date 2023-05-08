package com.example.ciclobnb.Objectes;

import android.provider.ContactsContract;

import java.util.Date;

public class GestioLloguers {
    private int IdLloguer;
    private double Total;
    private int IdUsuari;
    private Date DataPagament;
    public GestioLloguers(){}
    public GestioLloguers(int IdLloguer, double Total, int IdUsuari, Date DataPagament){
        this.IdLloguer = IdLloguer;
        this.Total = Total;
        this.IdUsuari = IdUsuari;
        this.DataPagament = DataPagament;
    }
    public int getIdLloguer() {
        return IdLloguer;
    }
    public void setIdLloguer(int idLloguer) {
        IdLloguer = idLloguer;
    }
    public double getTotal() {
        return Total;
    }
    public void setTotal(double total) {
        Total = total;
    }
    public int getIdUsuari() {
        return IdUsuari;
    }
    public void setIdUsuari(int idUsuari) {
        IdUsuari = idUsuari;
    }
    public Date getDataPagament() {
        return DataPagament;
    }
    public void setDataPagament(Date dataPagament) {
        DataPagament = dataPagament;
    }
}
