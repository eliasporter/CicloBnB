package com.example.ciclobnb.Objectes.Xat;

import com.example.ciclobnb.Objectes.Usser;

public class Xat {
    int idXat;
    Usser user;
    Usser llogater;

    public Xat(int idXat, Usser user, Usser llogater) {
        this.idXat = idXat;
        this.user = user;
        this.llogater = llogater;
    }

    public int getIdXat() {
        return idXat;
    }

    public void setIdXat(int idXat) {
        this.idXat = idXat;
    }

    public Usser getUser() {
        return user;
    }

    public void setUser(Usser user) {
        this.user = user;
    }

    public Usser getLlogater() {
        return llogater;
    }

    public void setLlogater(Usser llogater) {
        this.llogater = llogater;
    }
}
