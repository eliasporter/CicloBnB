package com.example.ciclobnb.BBDD.Connexions;

import com.example.ciclobnb.BBDD.ConnectBBdd;
import com.example.ciclobnb.Objectes.Direccio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnexioDireccio {

    java.sql.Statement stm;
    ResultSet rs;
    private final ConnectBBdd conexio = new ConnectBBdd();
    public ConnexioDireccio(){}
    private Connection cn =null;

    public boolean Actualizar(Direccio direccio) throws InterruptedException {
        final boolean[] hecho = {false};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                stm = null;
                rs = null;
                try {
                    String sql= "UPDATE direccio SET TipusVia = '"+direccio.tipusVia+"', " +
                            "NomCarrer = '"+direccio.nomCarrer+"', Numero = '"+direccio.numero+"', " +
                            "Pis = '" + direccio.pis+"', IdCP = '"+direccio.idCP+"' WHERE IdDireccio = " +
                            ""+direccio.idDireccion+";";
                    cn=conexio.execute().get();
                    stm = cn.createStatement();
                    if (stm.executeUpdate(sql) > 0) hecho[0] = true;
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
        thread.run();
        thread.join();
        return hecho[0];
    }
    public boolean SubirDireccion(Direccio direccio) throws InterruptedException {
        final boolean[] hecho = {false};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                stm = null;
                rs = null;
                try {
                    String sql= "INSERT INTO direccio (TipusVia, NomCarrer, Numero, Pis, IdCP) VALUES (" +
                            "'"+direccio.tipusVia+"', '"+direccio.nomCarrer+"', '"+direccio.numero+"', '" +
                            direccio.pis+"', '"+direccio.idCP+"');";
                    cn=conexio.execute().get();
                    stm = cn.createStatement();
                    if (stm.executeUpdate(sql) > 0) hecho[0] = true;
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
        thread.start();
        thread.join();
        return hecho[0];
    }
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

    public int BuscarID(String id) throws InterruptedException {
        final int[] idCp = new int[1];
        Thread fil = new Thread(new Runnable() {
            @Override
            public void run() {
                java.sql.Statement stm = null;
                ResultSet rs = null;
                int idXat,idUser1,idUser2;
                boolean actiu;
                try {
                    String sql= "SELECT * FROM `ciclobnbDB`.`codipostal` WHERE codipostal= '"+id+"';";

                    cn=conexio.execute().get();;
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

    public String agafaUltima() throws InterruptedException {
        //
        final String[] id = new String[1];
        Thread fil = new Thread(new Runnable() {
            @Override
            public void run() {
                java.sql.Statement stm = null;
                ResultSet rs = null;
                try {
                    String sql= "SELECT * FROM `direccio` ORDER BY `IdDireccio` DESC LIMIT 1;";
                    conexio.execute();
                    cn=conexio.get();
                    stm = cn.createStatement();
                    rs=stm.executeQuery(sql);
                    rs.next();
                    id[0] =rs.getString(1);
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
        return id[0];
    }
}
