package com.eletronicpoint.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {


    private ConnectionFactory(){} //O Padrão Singleton garante que uma classe tenha apenas uma instância de si mesma e que forneça um ponto global de acesso a ela

    public static Connection getConnection(){
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        try {
            return DriverManager.getConnection(dbUrl,dbUser,dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
