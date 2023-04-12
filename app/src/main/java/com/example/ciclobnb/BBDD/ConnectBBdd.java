package com.example.ciclobnb.BBDD;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectBBdd extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "ConexionMySQL";

    private static final String url = "jdbc:mysql://webapps.insjoanbrudieu.cat:25230/ciclobnbDB";
    private static final String usuario = "ciclobnb";
    private static final String password = "JuElNo--!!18736";

    @Override
    protected Void doInBackground(Void... voids) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Registrar el driver MySQL
            Class.forName("com.mysql.jdbc.Driver");

            // Establecer la conexión
            conn = DriverManager.getConnection(url, usuario, password);

            if (conn != null) {
                Log.d(TAG, "Conexión exitosa a MySQL");

                // Crear una declaración (Statement) para la consulta
                stmt = conn.createStatement();

                // Ejecutar la consulta SELECT
                String query = "SELECT * FROM codipostal";
                rs = stmt.executeQuery(query);

                // Procesar los resultados de la consulta
                while (rs.next()) {
                    // Obtener los valores de cada columna por nombre o por índice
                    int id = rs.getInt("idCodiPostal");
                    String nombre = rs.getString("codiPostal");

                    // Imprimir los valores obtenidos
                    Log.d(TAG, "ID: " + id + ", Codigo postal: " + nombre);
                }
            } else {
                Log.d(TAG, "Fallo en la conexión a MySQL");
            }
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Error al cargar el driver MySQL: " + e.getMessage());
        } catch (SQLException e) {
            Log.e(TAG, "Error de SQL: " + e.getMessage());
        } finally {
            // Cerrar los objetos ResultSet, Statement y Connection
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Log.e(TAG, "Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    Log.e(TAG, "Error al cerrar el Statement: " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    Log.e(TAG, "Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }

        return null;
    }
}