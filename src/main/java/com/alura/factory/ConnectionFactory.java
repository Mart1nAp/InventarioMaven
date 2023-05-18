package com.alura.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection recuperaConexion() throws SQLException {
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3309/control_de_stock?useTimezone=true&serverTimezone=UTC",
                                "root",
                                "admin");
    }
}
