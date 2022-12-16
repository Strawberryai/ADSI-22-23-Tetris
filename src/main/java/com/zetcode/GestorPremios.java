package com.zetcode;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.yaml.snakeyaml.events.Event.ID;

public class GestorPremios {

    private static GestorPremios miGestor;
    private GestorPremios(){}
    public static GestorPremios getInstance(){
        if(GestorPremios.miGestor == null) GestorPremios.miGestor = new GestorPremios();
        return GestorPremios.miGestor;
    }
    public void anadirPremio(String pUsuario, int pNivel,int pRecompensa, String pDescr, Timestamp sqlTimestamp) throws SQLException{
        int ID = 0;
        int idPremio = 0;
        Date fechaUltima;
        GestorBD SGBD = GestorBD.getInstance();
        ResultSet resSQL = SGBD.executeQuery("SELECT ID FROM JUGADOR WHERE usuario = '" + pUsuario + "'");
        if (resSQL.next()) {
            try {
                ID = resSQL.getInt("ID");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        SGBD.executeStatement("INSERT INTO Premio (recompensa, descripcion) VALUES ("+pRecompensa+", '"+pDescr+"')");
        SGBD.executeStatement("INSERT INTO GANA VALUES ("+ID+", '"+sqlTimestamp+"', "+pNivel+")");

        
    }
}
