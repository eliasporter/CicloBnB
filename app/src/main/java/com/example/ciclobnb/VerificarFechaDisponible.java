package com.example.ciclobnb;

import com.example.ciclobnb.BBDD.Connexions.BicisLlogaterConnection;
import com.example.ciclobnb.Objectes.BicisLlogater;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class VerificarFechaDisponible {
    private int idBici;
    private BicisLlogaterConnection bicisLlogaterConnection = new BicisLlogaterConnection();
    private final ArrayList<BicisLlogater> bicisLlogaters;
    public VerificarFechaDisponible(int idBici){this.idBici = idBici; bicisLlogaters = bicisLlogaterConnection.SearchFor(idBici);}
    public boolean ImportantDays(Date dateSelected){
        for (BicisLlogater bicisLlogater : bicisLlogaters){
            long dataInici = bicisLlogater.getDataInici().getTime();
            long dataFi = bicisLlogater.getDataFi().getTime();
            long f = dateSelected.getTime();
            if (f >= dataInici && f <= dataFi) return true;
        }
        return false;
    }
}
