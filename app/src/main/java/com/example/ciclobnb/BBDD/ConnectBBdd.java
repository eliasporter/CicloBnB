package com.example.ciclobnb.BBDD;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectBBdd {
    private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
   // private static final String URL = "jdbc:mysql://192.168.8.101:3306/ciclobnbnou";
   //private static final String URL = "jdbc:mysql://webapps.insjoanbrudieu.cat/ciclobnbDB:25230/";

    static {
        try {
            Class.forName(CONTROLADOR);
        } catch (ClassNotFoundException e) {
            Log.e("ConnectBBdd", "Error loading JDBC driver", e);
            throw new RuntimeException("Error loading JDBC driver", e);
        }
    }

    public Connection conectar() throws SQLException {
        Connection connection = null;
        try {
            StrictMode.ThreadPolicy fil = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(fil);
            //jdbc:mysql://192.168.1.150:25230/ciclobnbDB
            //connection = DriverManager.getConnection("jdbc:mysql://webapps.insjoanbrudieu.cat:25230/ciclobnbDB"
            connection = DriverManager.getConnection("jdbc:mysql://webapps.insjoanbrudieu.cat:25230/ciclobnbDB", "ciclobnb", "JuElNo--!!18736");
        } catch (SQLException e) {
            Log.e("ConnectBBdd", "Error connecting to database", e);
            throw e;
        }
        return connection;
    }

    public void desconectar(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            Log.e("ConnectBBdd", "Error closing database connection", e);
        }
    }
}
