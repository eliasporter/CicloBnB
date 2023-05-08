package com.example.ciclobnb.Objectes;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.example.ciclobnb.BBDD.ConnectBBdd;
import com.example.ciclobnb.BBDD.Connexions.ConnexioDireccio;
import com.example.ciclobnb.CrearCompte;
import com.example.ciclobnb.Objectes.Xat.Xat;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Usser implements Parcelable {

    private int IdUser;
    private String nom;
    private String cognom1;
    private String cognom2;
    private String login;
    private String contrasenya;
    private Date dataNaixement;
    private String correuElectronic;
    private Boolean actiu;
    public Direccio direccio;
    private final ConnectBBdd conexio = new ConnectBBdd();
    private Connection cn =null;

    public Usser(){}
    //Per a crear un nou usu no caldrà ficar cap id
    public Usser(String nom, String cognom1, String cognom2, String login, String contrasenya, Date dataNaixement, String correuElectronic, boolean actiu) {
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.login = login;
        this.contrasenya = contrasenya;
        this.dataNaixement = dataNaixement;
        this.correuElectronic = correuElectronic;
        this.actiu = actiu;
    }
    public Usser(int idUser,String nom, String cognom1, String cognom2, String login, String contrasenya, Date dataNaixement, String correuElectronic, Boolean actiu) {
        this.IdUser=idUser;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.login = login;
        this.contrasenya = contrasenya;
        this.dataNaixement = dataNaixement;
        this.correuElectronic = correuElectronic;
        this.actiu = actiu;
        try {
            conectar();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    protected Usser(Parcel in) {
        IdUser = in.readInt();
        nom = in.readString();
        cognom1 = in.readString();
        cognom2 = in.readString();
        login = in.readString();
        contrasenya = in.readString();
        correuElectronic = in.readString();
        byte tmpActiu = in.readByte();
        actiu = tmpActiu == 0 ? null : tmpActiu == 1;
    }

    public static final Creator<Usser> CREATOR = new Creator<Usser>() {
        @Override
        public Usser createFromParcel(Parcel in) {
            return new Usser(in);
        }

        @Override
        public Usser[] newArray(int size) {
            return new Usser[size];
        }
    };

    public void conectar() throws SQLException, ClassNotFoundException, ExecutionException, InterruptedException {
        cn=  conexio.execute().get();
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

    public Date getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(Date dataNaixement) {
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

    public Usser getUserPerId(int id) throws SQLException, InterruptedException {
        final Usser[] u = {null};
        Thread fil = new Thread(new Runnable() {
            @Override
            public void run() {
                java.sql.Statement stm = null;
                ResultSet rs = null;
                String nom, cognom1, cognom2,correuElectronic;
                Date dataNaixement;
                boolean actiu;

                try {
                    String sql= "SELECT * from `usuaris` WHERE idUsuari='"+id+"';";
                    cn=conexio.execute().get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    rs.next();
                    login=rs.getString(2);
                    nom=rs.getString(4);
                    cognom1=rs.getString(5);
                    cognom2=rs.getString(6);
                    dataNaixement=rs.getDate(7);
                    correuElectronic=rs.getString(8);
                    actiu=rs.getBoolean(9);
                    u[0] = new Usser(id,nom, cognom1,  cognom2, login, contrasenya, dataNaixement, correuElectronic, actiu);
                    Log.d("userLlegit", u[0].getNom());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        cn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        fil.start();
        fil.join();
        return u[0];
    }

    public String Hash(String contrasenya){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(contrasenya.getBytes(StandardCharsets.UTF_8));
            return String.format("%064x", new BigInteger(1, hash));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    public Usser Login(String user, String password) throws SQLException, InterruptedException {
        final Usser[] u = {null};
        Thread fil = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    java.sql.Statement stm = null;
                    ResultSet rs = null;
                    cn= new ConnectBBdd().execute().get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery("SELECT * FROM usuaris u" +
                            "INNER JOIN direccio d ON u.IdDireccio = d.IdDireccio " +
                            "WHERE Login='"+user+"' AND Contrasenya='"+Hash(password)+"';");
                    rs.next();
                    u[0] = new Usser(rs.getInt("IdUsuari"),rs.getString("Nom"), rs.getString("Cognom1"),  rs.getString("Cognom2"), user, Hash(password), rs.getDate("DataNaixement"), rs.getString("CorreuElectronic"), rs.getBoolean("CompteActiu"));
                    u[0].direccio = new Direccio(rs.getInt("IdDireccio"),rs.getString("TipusVia"),rs.getString("NomCarrer"),rs.getString("Numero"),rs.getString("Pis"),rs.getInt("IdCP"));
                    Log.d("userLlegit", u[0].getNom());
                }catch (Exception e){ e.printStackTrace();}
            }
        });
        fil.start();
        fil.join();
        return u[0];
    }

    public boolean insertUser() throws InterruptedException {
        Thread fil = new Thread(new Runnable() {
            @Override
            public void run() {
                java.sql.Statement stm = null;
                ResultSet rs = null;
                try {
                    String sql = "INSERT INTO `usuaris` (`Login`, `Contrasenya`, `Nom`, `Cognom1`, " +
                            "`Cognom2`, `DataNaixement`, `CorreuElectronic`, `CompteActiu`, `IdDireccio`) " +
                            "VALUES ('"+login+"', '"+Hash(contrasenya)+"', '"+nom+"', '"+cognom1+"', '"+cognom2+"', '"+"2023-04-17"+"', '"+correuElectronic+"', 1, "+new Direccio().AgafaUltima()+");";

                    cn= conexio.execute().get();
                    stm = cn.createStatement();
                    int i = stm.executeUpdate(sql);
                    if (i > 0) {
                        Log.d("SsQL", "insertat ");

                    } else {

                        Log.d("SsQL", "No insertat ");
                        rs.close();
                        stm.close();
                        cn.close();
                    }

                } catch (SQLException | ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        fil.start();
        fil.join();

        return true;
    }
    public boolean comprovarExisteixXat(){

        return true;
    }
    public void crearXat(){

    }
    public int getUserPerBici(int idBici){
        Thread fil=new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        return 0;
    }

    public ArrayList<Xat> getXats() throws SQLException, InterruptedException {

        ArrayList<Xat>xats=new ArrayList<>();

        Thread fil=new Thread(new Runnable() {
            java.sql.Statement stm = null;
            ResultSet rs = null;
            int idXat,idUser1,idUser2;
            boolean actiu;
            Usser u;
            @Override
            public void run() {
                try {

                    String sql= "SELECT * from `xat` WHERE `IdUsuariPropietari` ='"+getIdUser()+"' OR 'IdUsuariLlogador'='"+getIdUser()+"';";
                    cn=conexio.execute().get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    while(rs.next()){
                        idXat=rs.getInt(0);
                        idUser1=rs.getInt(1);
                        idUser2=rs.getInt(2);
                        xats.add(new Xat(idXat,idUser1,idUser2));
                    }
                    Log.d("userLlegit", ""+xats.size());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        cn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Log.d("XatError", e.getMessage());
                    }
                }
            }
        });
        fil.start();
        fil.join();
        return xats;
    }

    public ArrayList<String> Buscador(String query, Integer columnName) throws InterruptedException {
        ArrayList<String> result = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                java.sql.Statement stm = null;
                ResultSet rs = null;
                try {
                    conexio.execute();
                    cn = conexio.get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(query);
                    while(rs.next()){
                        String name=rs.getString(columnName);
                        result.add(name);
                    }
                    cn.close();
                    rs.close();
                    stm.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        return result;
    }
    public ArrayList<String> getCiutat(int ciutat){
        ArrayList<String>codisPostals = new ArrayList<>();
        Thread fil = new Thread(new Runnable() {
            @Override
            public void run() {
                java.sql.Statement stm = null;
                ResultSet rs = null;
                int idXat, idUser1, idUser2;
                boolean actiu;
                try {
                    String sql = "SELECT * from `pcciutat` WHERE IdCiutat='" + ciutat + "';";
                    conexio.execute();
                    cn = conexio.get();
                    stm = cn.createStatement();
                    rs = stm.executeQuery(sql);
                    while (rs.next()) {
                        String cp = new ConnexioDireccio().buscarCP(rs.getString(2));
                        codisPostals.add(cp);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        cn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Log.d("XatError", e.getMessage());
                    }
                }
            }
        });
        return codisPostals;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(IdUser);
        dest.writeString(nom);
        dest.writeString(cognom1);
        dest.writeString(cognom2);
        dest.writeString(login);
        dest.writeString(contrasenya);
        dest.writeString(correuElectronic);
        dest.writeByte((byte) (actiu == null ? 0 : actiu ? 1 : 2));
    }
}
