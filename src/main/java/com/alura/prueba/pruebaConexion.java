package com.alura.prueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class pruebaConexion {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3309/control_de_stock?useTimezone=true&serverTimezone=UTC",
                        "root",
                        "admin");

        System.out.println("Cerrando la conexion");

        con.close();
    }
}