package com.zetcode;

import java.net.ConnectException;
import java.nio.file.Paths;
import java.sql.*;

public class GestorBD {
    // Configuracion (CodC🔑, colorPieza, musica, ladrillo)
    // Jugador (ID🔑, usuario, pass, puntosMax, esAdmin, CodC🗝️)
    // Partida (ID_Jugador🔑, fechaHora🔑, puntuacion, nivel)
    // Premio (CodPremio🔑, recompensa, descripcion)
    // Gana (ID_Jugador🔑, fechaHora🔑, codPremio🔑)

    private static GestorBD miGestor;
    private String URL;
    private Connection connection = null;

    private GestorBD() {
        String path = Paths.get("").toAbsolutePath().toString();
        URL = "jdbc:h2:" + path + "/assets/database/datafile";

        this.createConnection();

        // Inicializar base de datos en caso de que no lo esté
        try {
            ResultSet res = this.executeQuery("SELECT * FROM Jugador WHERE usuario='Manuel'");
            if(!res.next()){
                // Existen las tablas pero no existe Manuel
                fillDatabaseWithExampleData();
            }

        } catch (Exception e) {
            this.initializeDatabase();
            // Existen las tablas pero no existe Manuel
            fillDatabaseWithExampleData();
        }
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

    public void imprimirTabla(String tabla){
        ResultSet res = executeQuery("SELECT * FROM " + tabla);

        System.out.println("Imprimiendo la tabla: " + tabla);
        try {
            ResultSetMetaData meta = res.getMetaData();
            int n = meta.getColumnCount();

            while(res.next()){
                StringBuilder out = new StringBuilder();
                for(int i = 1; i <= n; i++){
                    out.append(res.getString(i)).append(" ");
                }
                System.out.println(out);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: No existe la tabla " + tabla);
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
        // Creando tablas
        this.executeStatement("CREATE TABLE Configuracion (CodC INT NOT NULL AUTO_INCREMENT, colorPieza TEXT, musica TEXT, ladrillo TEXT, PRIMARY KEY (CodC))");
        this.executeStatement("CREATE TABLE Jugador (ID INT NOT NULL AUTO_INCREMENT, usuario TEXT NOT NULL, pass TEXT NOT NULL, email TEXT NOT NULL, puntosMax INT DEFAULT 0, esAdmin BIT NOT NULL DEFAULT 0, CodC INT, PRIMARY KEY(ID), FOREIGN KEY (CodC) REFERENCES Configuracion(CodC) ON DELETE CASCADE)");
        this.executeStatement("CREATE TABLE Partida (ID_Jugador INT NOT NULL, fechaHora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, puntuacion INT NOT NULL DEFAULT 0, nivel INT NOT NULL DEFAULT 0, PRIMARY KEY (ID_Jugador, fechaHora), FOREIGN KEY (ID_Jugador) REFERENCES Jugador(ID) ON DELETE CASCADE)");
        this.executeStatement("CREATE TABLE Premio (CodPremio INT NOT NULL AUTO_INCREMENT, recompensa INT NOT NULL DEFAULT 0, descripcion TEXT, PRIMARY KEY (CodPremio))");
        this.executeStatement("CREATE TABLE Gana (ID_Jugador INT NOT NULL, fechaHora TIMESTAMP NOT NULL, codPremio INT NOT NULL, PRIMARY KEY(ID_Jugador, fechaHora, codPremio), FOREIGN KEY (ID_Jugador, fechaHora) REFERENCES Partida(ID_Jugador, fechaHora) ON DELETE CASCADE, FOREIGN KEY (codPremio) REFERENCES Premio(CodPremio) ON DELETE CASCADE)");

    }

    public void fillDatabaseWithExampleData(){
        this.executeStatement("INSERT INTO Jugador (usuario, email, pass, esAdmin) VALUES ('Manuel', 'manuel@mail.com', '1234', 1)");
        this.executeStatement("INSERT INTO Jugador (usuario, email, pass) VALUES ('Gustavo', 'gustavo@mail.com', '1234')");
    }
}
