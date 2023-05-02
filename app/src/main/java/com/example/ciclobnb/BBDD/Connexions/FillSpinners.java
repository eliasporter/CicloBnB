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
    public HashMap<Integer, String>countries;
    public HashMap<Integer, String>cities;
    public HashMap<Integer, String>cp;
    private Usser command;

    public void FillCountries(){
        countries = command.Buscador("SELECT * FROM pais", 1, 2);
    }

    public void FillCities(String name) {
        cities = command.Buscador("SELECT * from ciutat WHERE Nom='"+name+"';", 3, 1);
    }

    public FillSpinners(){
        countries = new HashMap<>();
        cities = new HashMap<>();
        cp = new HashMap<>();
        command = new Usser();
    }
}
