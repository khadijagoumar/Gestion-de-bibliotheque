package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL = "jdbc:mysql://avnadmin:AVNS_PPMcIA3iHVb8U8fpyp6@mysql-2941b198-khadijagoumarzouazte-43d2.a.aivencloud.com:17966/gestionbib?ssl-mode=REQUIRED"; // Vérifiez le nom de la base de données
    private static final String USER = "avnadmin"; // Utilisateur MySQL
    private static final String PASSWORD = "AVNS_PPMcIA3iHVb8U8fpyp6"; // Mot de passe vide

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Assurez-vous que le driver est inclus dans les dépendances
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
            throw new SQLException("JDBC Driver not found", e);
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            throw new SQLException("Database connection error", e);
        }
    }
}
