package com.example.ciclobnb.Objectes.Xat;

import com.example.ciclobnb.Objectes.Usser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Xat {
    int idXat;
    int user1;
    int user2;


    public Xat(int idXat, int user, int llogater) {
        this.idXat = idXat;
        this.user1 = user;
        this.user2 = llogater;
    }
    public Xat(){}

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
    public Xat getXAtPerId(int idXat){
        Xat xat=null;
        if(idXat==1){
            xat =new Xat(1,1,2);
        }else if(idXat==2){
            xat=(new Xat(2,1,3));}
        else if(idXat==3){
            xat=(new Xat(3,2,1));
        }else if(idXat==4){
            xat=(new Xat(4,2,3));
        }else {

        }

        return xat;
    }
    public ArrayList<Missatge> getMissatges (){
        ArrayList<Missatge> missatges=new ArrayList<>();
        Date date = new Date();
        Timestamp temps = new Timestamp(date.getTime());
        missatges.add(new Missatge("Hola",getUser1(),temps.toString()));
        missatges.add(new Missatge("Hola!",getUser2(),temps.toString()));

        return missatges;
    }
}
