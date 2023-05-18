package com.alura.prueba;

import com.alura.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class pruebaConexion {
    public static void main(String[] args) throws SQLException {
        Connection con = new ConnectionFactory().recuperaConexion();
        System.out.println("Cerrando la conexion");

        con.close();
    }
}
