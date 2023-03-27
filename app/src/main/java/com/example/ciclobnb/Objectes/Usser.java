package com.example.ciclobnb.Objectes;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.example.ciclobnb.BBDD.ConnectBBdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    private Connection cn =conexio.conectar();

    public Usser(String nom, String cognom1, String cognom2, String login, String contrasenya, String dataNaixement, String correuElectronic, Boolean actiu) {
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.login = login;
        this.contrasenya = contrasenya;
        this.dataNaixement = dataNaixement;
        this.correuElectronic = correuElectronic;
        this.actiu = actiu;
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
        return login;
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

    public boolean insertUser() {
        java.sql.Statement stm = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO `usuaris` (`IdUsuari`, `Login`, `Contrasenya`, `Nom`, `Cognom1`, `Cognom2`, `DataNaixement`," +
                    " `CorreuElectronic`, `CompteActiu`, `IdDireccio`) VALUES " +
                    "(NULL, '" + login + "', '" + contrasenya + "', '" + nom + "', '" + cognom1 + "', '" + cognom2 + "', '" + dataNaixement + "', '" + correuElectronic + "', b'0', '1');";
            stm = cn.createStatement();
            int i = stm.executeUpdate(sql);
            if (i > 0) {
                System.out.println("ROW INSERTED");
            } else {
                System.out.println("ROW NOT INSERTED");
                Log.d("SsQL", "No insertat ");
            }

        } catch (SQLException e) {
            // TODO: handle exception
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
                // TODO: handle exception
            }
            return true;
        }
    }
}
