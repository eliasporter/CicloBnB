package com.example.ciclobnb.Objectes;

import android.content.Context;

import com.example.ciclobnb.BBDD.ConnectBBdd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Direccio {
    private String direccio;
    private int pais;
    private int ciutat;
    private int cp;
    private final ConnectBBdd conexio = new ConnectBBdd();
    private Connection cn =null;
    public int buscarCiutatPerNom (String ciutat) throws InterruptedException {
        final int[] idPais = new int[1];
            Thread fil = new Thread(new Runnable() {
                @Override
                public void run() {
                    java.sql.Statement stm = null;
                    ResultSet rs = null;
                    int idXat,idUser1,idUser2;
                    boolean actiu;
                    try {
                        String sql= "SELECT idCiutat from `ciutat` WHERE nom='"+ciutat+"';";
                        conexio.execute();
                        cn=conexio.get();
                        stm = cn.createStatement();
                        rs=stm.executeQuery(sql);
                        rs.next();
                        idPais[0] =rs.getInt(1);



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
        return idPais[0];
    }
    public String buscarCP (String cp) throws InterruptedException {
        final String[] idPais = new String[1];
        Thread fil = new Thread(new Runnable() {
            @Override
            public void run() {
                java.sql.Statement stm = null;
                ResultSet rs = null;
                int idXat,idUser1,idUser2;
                boolean actiu;
                try {
                    String sql= "SELECT * from `codipostal` WHERE idCodiPostal='"+cp+"';";
                    conexio.execute();
                    cn=conexio.get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    rs.next();
                    idPais[0] =rs.getString(2);



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
        return idPais[0];
    }
    public int getCPPerNom (String cp) throws InterruptedException {
        final int[] idCp = new int[1];
        Thread fil = new Thread(new Runnable() {
            @Override
            public void run() {
                java.sql.Statement stm = null;
                ResultSet rs = null;
                int idXat,idUser1,idUser2;
                boolean actiu;
                try {
                    String sql= "SELECT codipostal from `codipostal` WHERE codiPostal='"+cp+"';";
                    conexio.execute();
                    cn=conexio.get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    rs.next();
                    idCp[0] =rs.getInt(1);



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
        return idCp[0];
    }
    public int getPaisPerNom (String cp) throws InterruptedException {
        final int[] idCp = new int[1];
        Thread fil = new Thread(new Runnable() {
            @Override
            public void run() {
                java.sql.Statement stm = null;
                ResultSet rs = null;
                int idXat,idUser1,idUser2;
                boolean actiu;
                try {
                    String sql= "SELECT pais from `pais` WHERE Nom='"+cp+"';";
                    conexio.execute();
                    cn=conexio.get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    rs.next();
                    idCp[0] =rs.getInt(1);



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
        return idCp[0];
    }
}
