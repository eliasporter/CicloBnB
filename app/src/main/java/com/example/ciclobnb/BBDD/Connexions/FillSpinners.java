package com.example.ciclobnb.BBDD.Connexions;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ciclobnb.CrearCompte;
import com.example.ciclobnb.Objectes.Usser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FillSpinners {
    public ArrayList<String>countries;
    public ArrayList<String>cities;
    public ArrayList<String>cp;
    private final Usser command;

    public void FillCountries() throws InterruptedException {
        countries = command.Buscador("SELECT Nom FROM pais;",  1);
    }

    public void FillCities(String pais) throws InterruptedException {
        cities = command.Buscador("SELECT ciutat.Nom FROM ciutat " +
                "INNER JOIN pais ON pais.IdPais = ciutat.IdPais " +
                "WHERE pais.Nom = '"+pais+"';",  1);
    }

    public void FillCP(String ciutat) throws InterruptedException {
        cp = command.Buscador("SELECT codipostal.idCodiPostal, codipostal.codiPostal FROM codipostal " +
                "INNER JOIN pcciutat ON pcciutat.IdCodiPostal = codipostal.idCodiPostal " +
                "INNER JOIN ciutat ON ciutat.IdCiutat = pcciutat.IdCiutat " +
                "WHERE ciutat.Nom = '"+ciutat+"';",  1);
    }


    public FillSpinners(){
        countries = new ArrayList<>();
        cities = new ArrayList<>();
        cp = new ArrayList<>();
        command = new Usser();
    }

    public ArrayList<String> getCountries() {

        return countries;
    }

    public void setCountries(ArrayList<String> countries) {
        this.countries = countries;
    }

    public ArrayList<String> getCities() {
        return cities;
    }

    public void setCities(ArrayList<String> cities) {
        this.cities = cities;
    }

    public ArrayList<String> getCp() {
        return cp;
    }

    public void setCp(ArrayList<String> cp) {
        this.cp = cp;
    }
}
