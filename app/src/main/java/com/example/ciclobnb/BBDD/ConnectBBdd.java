package com.example.ciclobnb.BBDD;
import android.os.AsyncTask;
import android.util.Log;

import com.example.ciclobnb.Objectes.Usser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectBBdd extends AsyncTask<Void, Void, Connection> {

    private static final String TAG = "ConexionMySQL";

    private static final String url = "jdbc:mysql://webapps.insjoanbrudieu.cat:25230/ciclobnbDB";
    private static final String usuari = "ciclobnb";
    private static final String password = "JuElNo--!!18736";

    public ConnectBBdd() {
    }

    @Override
    protected Connection doInBackground(Void... voids) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Properties props = new Properties();
        props.setProperty("user", usuari);
        props.setProperty("password", password);
        props.setProperty("connectTimeout", "5000");
        try {
            // Registrar el driver MySQL
            Class.forName("com.mysql.jdbc.Driver");

            // Establecer la conexión
            conn = DriverManager.getConnection(url, props);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }
}