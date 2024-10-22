package com.github.mrayek;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class DatabaseConnection {
    public static Properties loadProperties() throws IOException{
        InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties");

        Properties properties = new Properties();
        properties.load(input);

        return properties;
    }

    public static Connection getConnection() throws SQLException {
        Properties properties;
        try {
            properties = loadProperties();
        } catch (IOException e) {
            throw new RuntimeException("Unable to load db.properties");
        }

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }
}