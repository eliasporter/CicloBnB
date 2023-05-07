package com.example.ciclobnb.Objectes;

import android.content.Context;
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
import java.util.concurrent.ExecutionException;

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
    private final ConnectBBdd conexio = new ConnectBBdd();
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
        try {
            conectar();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



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

    public Usser getUserPerId(int id) throws SQLException, InterruptedException {
        final Usser[] u = {null};
        Thread fil = new Thread(new Runnable() {
            @Override
            public void run() {
                java.sql.Statement stm = null;
                ResultSet rs = null;
                String nom, cognom1, cognom2,dataNaixement,correuElectronic;
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
                    dataNaixement=rs.getString(7);
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

        /*Usser j=new Usser(1,"Juan","Avila","Vega","juanse","123","2002-01-01","juan@gmail.com",true);
        Usser n=new Usser(2,"Norbert","Aguilera","CApdevila","nor","123","2003-02-25","naca605@gmail.com",true);
        //u=(id==1)?j:n;
        if(id==1)u=j;
        else if(id==2)u=n;
        else if(id==3)u=new Usser(1,"Admin","Avila","Vega","Admin","123","2002-01-01","juan@gmail.com",true);
        */
        return u[0];
    }

    public String creaHash(String contrasenya){

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(contrasenya.getBytes(StandardCharsets.UTF_8));
            String hex = String.format("%064x", new BigInteger(1, hash));
            return hex;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }
    public Usser propietariBici(int id) throws SQLException {
        ConnectBBdd conexio = new ConnectBBdd();
        Connection cn =null;
        Usser u= null;
        java.sql.Statement stm = null;
        ResultSet rs = null;
        try {
            String sql= "SELECT * from `usuaris` WHERE login='"+login+"' AND Contrasenya='"+creaHash(contrasenya)+"';";
            cn= conexio.execute().get();
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

        }finally {
            cn.close();
        }
        return u;
    }
    public Usser Login(String nomSesio, String contrasenya) throws SQLException, InterruptedException {
        final Usser[] u = {null};
        Thread fil= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    java.sql.Statement stm = null;
                    ResultSet rs = null;
                    String nom,login, cognom1, cognom2,dataNaixement,correuElectronic;
                    int id;
                    boolean actiu;
                    String sql= "SELECT * from `usuaris` WHERE login='"+nomSesio+"' AND Contrasenya='"+creaHash(contrasenya)+"';";
                    cn= new ConnectBBdd().execute().get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    rs.next();
                    id=rs.getInt(1);
                    login=rs.getString(2);
                    nom=rs.getString(4);
                    cognom1=rs.getString(5);
                    cognom2=rs.getString(6);
                    dataNaixement=rs.getString(7);
                    correuElectronic=rs.getString(8);
                    actiu=rs.getBoolean(9);
                    u[0] = new Usser(id,nom, cognom1,  cognom2, login, contrasenya, dataNaixement, correuElectronic, actiu);
                    Log.d("userLlegit", u[0].getNom());
                }catch (Exception e){

                }
                finally {
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

        /*Usser j=new Usser(1,"Juan","Avila","Vega","juanse","123","2002-01-01","juan@gmail.com",true);
        Usser n=new Usser(2,"Norbert","Aguilera","CApdevila","nor","123","2003-02-25","naca605@gmail.com",true);

        if(login.equals("juanse")&&contrasenya.equals("123"))
            u=j;
        else if(login.equals("nor")&&contrasenya.equals("123"))
            u=n;*/
        return u[0];
    }

    public boolean insertUser() throws InterruptedException {
        Thread fil = new Thread(new Runnable() {
            @Override
            public void run() {
                java.sql.Statement stm = null;
                ResultSet rs = null;
                try {
                    String sql = "INSERT INTO `ciclobnbDB`.`usuaris` (`Login`, `Contrasenya`, `Nom`, `Cognom1`, " +
                            "`Cognom2`, `DataNaixement`, `CorreuElectronic`, `CompteActiu`, `IdDireccio`) " +
                            "VALUES ('"+login+"', '"+creaHash(contrasenya)+"', '"+nom+"', '"+cognom1+"', '"+cognom2+"', '"+"2023-04-17"+"', '"+correuElectronic+"', 1, "+new Direccio().AgafaUltima();
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

                } catch (SQLException e) {
                    System.out.println(e);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
    public ArrayList<String> BuscarPaisos(Context context) throws SQLException, InterruptedException {
        ArrayList<String>paisos=new ArrayList<>();

        Thread fil = new Thread(new Runnable() {
            @Override
            public void run() {
                java.sql.Statement stm = null;
                ResultSet rs = null;
                int idXat,idUser1,idUser2;
                boolean actiu;
                try {
                    String sql= "SELECT * from `pais` ;";
                    conexio.execute();
                    cn=conexio.get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    while(rs.next()){
                        String pais=rs.getString(2);
                        paisos.add(pais);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (stm != null) {
                        try {
                            stm.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (cn != null) {
                        try {
                            cn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        fil.start();
        fil.join();
        return paisos;
    }
    public ArrayList<String> BuscarCiutats(Context context,int pais) throws SQLException, InterruptedException {
        ArrayList<String>paisos=new ArrayList<>();

        Thread fil = new Thread(new Runnable() {
            @Override
            public void run() {
                java.sql.Statement stm = null;
                ResultSet rs = null;
                int idXat,idUser1,idUser2;
                boolean actiu;
                try {
                    String sql= "SELECT * from `ciutat` WHERE IdPais='"+pais+"';";
                    conexio.execute();
                    cn=conexio.get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    while(rs.next()){
                        String pais=rs.getString(1);
                        paisos.add(pais);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (stm != null) {
                        try {
                            stm.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (cn != null) {
                        try {
                            cn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        fil.start();
        fil.join();
        return paisos;
    }
    public ArrayList<String> BuscarCP(Context context,int ciutat) throws SQLException, InterruptedException {
        ArrayList<String>codisPostals=new ArrayList<>();

        Thread fil = new Thread(new Runnable() {
            @Override
            public void run() {
                java.sql.Statement stm = null;
                ResultSet rs = null;
                int idXat,idUser1,idUser2;
                boolean actiu;
                try {
                    String sql= "SELECT * from `pcciutat` WHERE IdCiutat='"+ciutat+"';";
                    conexio.execute();
                    cn=conexio.get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    while(rs.next()){
                        String cp=new ConnexioDireccio().buscarCP(rs.getString(2));
                        codisPostals.add(cp);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (stm != null) {
                        try {
                            stm.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (cn != null) {
                        try {
                            cn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        fil.start();
        fil.join();
        return codisPostals;
    }
}
