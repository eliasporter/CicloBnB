package com.example.ciclobnb.BBDD;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "Usuari")
public class Usser {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "IdUser")
    private int IdUser;

    @NonNull
    @ColumnInfo(name = "nom")
    private String nom;
    @NonNull
    @ColumnInfo(name = "cognom1")
    private String cognom1;
    @NonNull
    @ColumnInfo(name = "cognom2")
    private String cognom2;
    @NonNull
    @ColumnInfo(name = "login")
    private String login;
    @NonNull
    @ColumnInfo(name = "contrasenya")
    private String contrasenya;
    @NonNull
    @ColumnInfo(name = "dataNaixement")
    private String dataNaixement;
    @NonNull
    @ColumnInfo(name = "correuElectronic")
    private String correuElectronic;
    @NonNull
    @ColumnInfo(name = "actiu")
    private Boolean actiu;
    @NonNull
    @ColumnInfo(name = "idDireccio")
    private int idDireccio;

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }

    @NonNull
    public String getNom() {
        return nom;
    }

    public void setNom(@NonNull String nom) {
        this.nom = nom;
    }

    @NonNull
    public String getCognom1() {
        return cognom1;
    }

    public void setCognom1(@NonNull String cognom1) {
        this.cognom1 = cognom1;
    }

    @NonNull
    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(@NonNull String cognom2) {
        this.cognom2 = cognom2;
    }

    @NonNull
    public String getLogin() {
        return login;
    }

    public void setLogin(@NonNull String login) {
        this.login = login;
    }

    @NonNull
    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(@NonNull String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @NonNull
    public String getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(@NonNull String dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    @NonNull
    public String getCorreuElectronic() {
        return correuElectronic;
    }

    public void setCorreuElectronic(@NonNull String correuElectronic) {
        this.correuElectronic = correuElectronic;
    }

    @NonNull
    public Boolean getActiu() {
        return actiu;
    }

    public void setActiu(@NonNull Boolean actiu) {
        this.actiu = actiu;
    }

    public int getIdDireccio() {
        return idDireccio;
    }

    public void setIdDireccio(int idDireccio) {
        this.idDireccio = idDireccio;
    }

    public Usser( @NonNull String nom, @NonNull String cognom1, @NonNull String cognom2, @NonNull String login, @NonNull String contrasenya, @NonNull String dataNaixement, @NonNull String correuElectronic, @NonNull Boolean actiu, int idDireccio) {
        //IdUser = idUser;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.login = login;
        this.contrasenya = contrasenya;
        this.dataNaixement = dataNaixement;
        this.correuElectronic = correuElectronic;
        this.actiu = actiu;
        this.idDireccio = idDireccio;
    }
}
