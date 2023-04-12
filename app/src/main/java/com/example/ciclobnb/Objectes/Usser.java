package com.example.ciclobnb.Objectes;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.example.ciclobnb.BBDD.ConnectBBdd;
import com.example.ciclobnb.Objectes.Xat.Xat;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

public class Usser {

    private int IdUser;
    private String nom;
    private String cognom1;
    private String cognom2;
    private String login;
    private String contrasenya;
    private String dataNaixement;
    private String correuElectronic;
    private Boolean actiu;
    private ConnectBBdd conexio = new ConnectBBdd();
    private Connection cn =null;

    public Usser(){}
    //Per a crear un nou usu no caldrà ficar cap id
    public Usser(String nom, String cognom1, String cognom2, String login, String contrasenya, String dataNaixement, String correuElectronic, boolean actiu) {
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.login = login;
        this.contrasenya = contrasenya;
        this.dataNaixement = dataNaixement;
        this.correuElectronic = correuElectronic;
        this.actiu = actiu;
    }
    public Usser(int idUser,String nom, String cognom1, String cognom2, String login, String contrasenya, String dataNaixement, String correuElectronic, Boolean actiu) {
        this.IdUser=idUser;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.login = login;
        this.contrasenya = contrasenya;
        this.dataNaixement = dataNaixement;
        this.correuElectronic = correuElectronic;
        this.actiu = actiu;
        /*try {
            conectar();
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }



    public void conectar() throws SQLException {
        cn=conexio.conectar();
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom1() {
        return cognom1;
    }

    public void setCognom1(String cognom1) {
        this.cognom1 = cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cognom2) {
        this.cognom2 = cognom2;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(String dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public String getCorreuElectronic() {
        return correuElectronic;
    }

    public void setCorreuElectronic(String correuElectronic) {
        this.correuElectronic = correuElectronic;
    }

    public Boolean getActiu() {
        return actiu;
    }

    public void setActiu(Boolean actiu) {
        this.actiu = actiu;
    }

    public Usser getUserPerId(int id){
        java.sql.Statement stm = null;
        ResultSet rs = null;
        String nom, cognom1, cognom2,dataNaixement,correuElectronic;
        boolean actiu;
        Usser u=null;
        /*try {
            String sql= "SELECT * from `usuaris` WHERE contrasenya='"+id+"';";
            cn=conexio.conectar();
            stm = cn.createStatement();
            rs=stm.executeQuery(sql);
            login=rs.getString(2);
            nom=rs.getString(4);
            cognom1=rs.getString(5);
            cognom2=rs.getString(6);
            dataNaixement=rs.getString(7);
            correuElectronic=rs.getString(8);
            actiu=rs.getBoolean(9);
            u = new Usser(nom, cognom1,  cognom2, login, contrasenya, dataNaixement, correuElectronic, actiu);
            Log.d("userLlegit", u.getNom());
        }catch (Exception e){

        }*/
        Usser j=new Usser(1,"Juan","Avila","Vega","juanse","123","2002-01-01","juan@gmail.com",true);
        Usser n=new Usser(2,"Norbert","Aguilera","CApdevila","nor","123","2003-02-25","naca605@gmail.com",true);
        //u=(id==1)?j:n;
        if(id==1)u=j;
        else if(id==2)u=n;
        else if(id==3)u=new Usser(1,"Admin","Avila","Vega","Admin","123","2002-01-01","juan@gmail.com",true);
        return u;
    }

    public String creaHash(String contrasenya){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(contrasenya.getBytes());
            String codificat = Base64.getEncoder().encodeToString(hashBytes);
            return codificat;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    public Usser propietariBici(int id){
        Usser u= null;
        java.sql.Statement stm = null;
        ResultSet rs = null;
        try {
            String sql= "SELECT * from `usuaris` WHERE login='"+login+"' AND Contrasenya='"+contrasenya+"';";
            cn=conexio.conectar();
            stm = cn.createStatement();
            rs=stm.executeQuery(sql);
            id=rs.getInt(1);
            login=rs.getString(2);
            nom=rs.getString(4);
            cognom1=rs.getString(5);
            cognom2=rs.getString(6);
            dataNaixement=rs.getString(7);
            correuElectronic=rs.getString(8);
            actiu=rs.getBoolean(9);
            u = new Usser(id,nom, cognom1,  cognom2, login, contrasenya, dataNaixement, correuElectronic, actiu);
            Log.d("userLlegit", u.getNom());
        }catch (Exception e){

        }
        return u;
    }
    public Usser Login(String login, String contrasenya){
        java.sql.Statement stm = null;
        ResultSet rs = null;
        String nom, cognom1, cognom2,dataNaixement,correuElectronic;
        int id;
        boolean actiu;
        Usser u=null;
        /*
        try {
            String sql= "SELECT * from `usuaris` WHERE login='"+login+"' AND Contrasenya='"+contrasenya+"';";
            cn=conexio.conectar();
            stm = cn.createStatement();
            rs=stm.executeQuery(sql);
            id=rs.getInt(1);
            login=rs.getString(2);
            nom=rs.getString(4);
            cognom1=rs.getString(5);
            cognom2=rs.getString(6);
            dataNaixement=rs.getString(7);
            correuElectronic=rs.getString(8);
            actiu=rs.getBoolean(9);
            u = new Usser(id,nom, cognom1,  cognom2, login, contrasenya, dataNaixement, correuElectronic, actiu);
            Log.d("userLlegit", u.getNom());
        }catch (Exception e){

        }*/
        Usser j=new Usser(1,"Juan","Avila","Vega","juanse","123","2002-01-01","juan@gmail.com",true);
        Usser n=new Usser(2,"Norbert","Aguilera","CApdevila","nor","123","2003-02-25","naca605@gmail.com",true);

        if(login.equals("juanse")&&contrasenya.equals("123"))
            u=j;
        else if(login.equals("nor")&&contrasenya.equals("123"))
            u=n;
        return u;
    }

    public boolean insertUser() {
        java.sql.Statement stm = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO `usuaris` (`IdUsuari`, `Login`, `Contrasenya`, `Nom`, `Cognom1`, `Cognom2`, `DataNaixement`," +
                    " `CorreuElectronic`, `CompteActiu`, `IdDireccio`) VALUES " +
                    "(NULL, '" + login + "', '" + contrasenya + "', '" + nom + "', '" + cognom1 + "', '" + cognom2 + "', '" + dataNaixement + "', '" + correuElectronic + "', b'0', '1');";
            cn=conexio.conectar();
            stm = cn.createStatement();
            int i = stm.executeUpdate(sql);
            if (i > 0) {
                Log.d("SsQL", "insertat ");

            } else {

                Log.d("SsQL", "No insertat ");
                rs.close();
                stm.close();
                cn.close();
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return true;
    }
    public boolean comprovarExisteixXat(){

        return true;
    }
    public void crearXat(){

    }
    public int getUserPerBici(int idBici){
        return 0;
    }
    public ArrayList<Xat> getXats() {
        java.sql.Statement stm = null;
        ResultSet rs = null;
        String nom, cognom1, cognom2,dataNaixement,correuElectronic;
        int id;
        boolean actiu;
        ArrayList<Xat>xats=new ArrayList<>();
        /*
        try {
            String sql= "SELECT * from `usuaris` WHERE login='"+login+"' AND Contrasenya='"+contrasenya+"';";
            cn=conexio.conectar();
            stm = cn.createStatement();
            rs=stm.executeQuery(sql);
            id=rs.getInt(1);
            login=rs.getString(2);
            nom=rs.getString(4);
            cognom1=rs.getString(5);
            cognom2=rs.getString(6);
            dataNaixement=rs.getString(7);
            correuElectronic=rs.getString(8);
            actiu=rs.getBoolean(9);
            u = new Usser(id,nom, cognom1,  cognom2, login, contrasenya, dataNaixement, correuElectronic, actiu);
            Log.d("userLlegit", u.getNom());
        }catch (Exception e){

        }*/
        if(this.IdUser==1){
            xats.add(new Xat(1,1,2));
            xats.add(new Xat(2,1,3));
        }else if(this.IdUser==2){
            xats.add(new Xat(1,2,1));
            xats.add(new Xat(2,2,3));
        }else {
            xats.add(new Xat(1,2,1));
            xats.add(new Xat(2,2,3));
            xats.add(new Xat(1,1,2));
            xats.add(new Xat(2,1,3));
        }
        return xats;
    }
}
