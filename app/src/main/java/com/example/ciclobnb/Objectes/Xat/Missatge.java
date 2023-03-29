package com.example.ciclobnb.Objectes.Xat;

import com.example.ciclobnb.Objectes.Usser;

public class Missatge {
    int idMissatge;
    int idXat;
    String missatge;
    Usser emisor;
    String timeStamp;
    public  Missatge(String missatge,Usser emisor,String timeStamp){
        this.missatge=missatge;
        this.emisor=emisor;
        this.timeStamp=timeStamp;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }

    public Usser getEmisor() {
        return emisor;
    }

    public void setEmisor(Usser emisor) {
        this.emisor = emisor;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
