package com.example.ciclobnb.Objectes.Xat;

import com.example.ciclobnb.Objectes.Usser;

import java.util.ArrayList;

public class Xat {
    int idXat;
    int user1;
    int user2;
    ArrayList<Missatge> missatges=new ArrayList<>();

    public Xat(int idXat, int user, int llogater) {
        this.idXat = idXat;
        this.user1 = user;
        this.user2 = llogater;
    }

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

    public String getUltimMissatge(){
        return "Adeu";
    }
}
