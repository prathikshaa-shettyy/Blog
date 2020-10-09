package com.upgrad.ublog.db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO 6.2: Implement the DatabaseConnection class using the Singleton Pattern (Hint. Should have the
 *  private no-arg constructor, a private static connection attribute of type Connection and a public
 *  static getConnection() method which return the connection attribute).
 * TODO 6.3: The getInstance() method should check if the connection attribute is null. If yes, then
 *  it should create a connection object which is connected with the local database and assign this
 *  connection object to the connection attribute. In the end, return this connection attribute.
 * TODO 6.4: You should handle the ClassNotFoundException and SQLException individually,
 *  and not using the Exception class.
 */

public class DatabaseConnection {
    private static Connection connection = null;

    private DatabaseConnection() {}

    public static Connection getConnection(){
        return connection;
    }

    public static Connection getInstance() {
        if (connection == null) {
            String path = System.getProperty("catalina.base")+"\\..\\..\\src\\main\\java\\com\\upgrad\\ublog\\db\\database.config";
            Map<String, String> credentials = null;
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                credentials = new HashMap<>();
                String line;
                while ((line=br.readLine()) != null) {
                    String[] tokens = line.split("=");
                    credentials.put(tokens[0], tokens[1]);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Config file not found.");
            } catch (IOException e) {
                System.out.println("Error while reading config file.");
            }
            String url = credentials.get("url");
            String username = credentials.get("username");
            String password = credentials.get("password");

            try {
                Class.forName(credentials.get("driverName"));
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                System.out.println(credentials.get("errorMessage"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }



    public static void main(String[] args) throws SQLException{
        try {
        	DatabaseConnection.getConnection();
        	System.out.println("Connected");
        } catch (Exception e) {
        	System.out.println("Not Connected");
        }
    }
}
