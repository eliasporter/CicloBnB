package com.example.ciclobnb.Objectes.Xat;

import com.example.ciclobnb.BBDD.Connexions.missatgeConexio;
import com.example.ciclobnb.Objectes.Usser;

public class Missatge {
    public int idMissatge;
    public int idXat;
     private String missatge;
    private int emisor;
    private String timeStamp;
    public  Missatge( String missatge,int emisor,String timeStamp){

        this.missatge=missatge;
        this.emisor=emisor;
        this.timeStamp=timeStamp;
    }
    public  Missatge( String missatge,int emisor,String timeStamp, int idXat){
        this.idXat=idXat;
        this.missatge=missatge;
        this.emisor=emisor;
        this.timeStamp=timeStamp;
    }
    public  Missatge(int idMissatge, String missatge,int emisor,String timeStamp){
        this.idMissatge=idMissatge;
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

    public int getEmisor() {
        return emisor;
    }

    public void setEmisor(int emisor) {
        this.emisor = emisor;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean insertMissatge(){
        try {
            return new missatgeConexio().insertMissatge(this);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return false;
    }
}
