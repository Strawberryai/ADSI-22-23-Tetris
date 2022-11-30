package com.zetcode;

import java.net.ConnectException;
import java.sql.*;

public class GestorBD {
    private static GestorBD miGestor;
    private String URL = "jdbc:h2:mem:test";
    private Connection connection = null;

    private GestorBD() {
        this.createConnection();
        this.initializeDatabase();
    }

    public static GestorBD getInstance(){
        if(GestorBD.miGestor == null) GestorBD.miGestor = new GestorBD();
        return GestorBD.miGestor;
    }

    public void createConnection(){
        if(this.connection == null){
            try {
                this.connection = DriverManager.getConnection(this.URL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnection(){
        if(this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void executeStatement(String sql){
        try {
            Statement statement = this.connection.createStatement();
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql){
        ResultSet res = null;
        try {
            Statement statement = this.connection.createStatement();
            res = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    private void initializeDatabase(){
        this.executeStatement("CREATE TABLE Test (ID int PRIMARY KEY, name VARCHAR(50))");
        this.executeStatement("INSERT INTO Test (ID, name) VALUES (1, 'Manuel Garcia')");
        this.executeStatement("INSERT INTO Test (ID, name) VALUES (2, 'Gustavo Alonso')");
    }
}
