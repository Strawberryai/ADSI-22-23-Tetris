package com.zetcode;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.yaml.snakeyaml.events.Event.ID;

public class GestorPremios {

    private static GestorPremios miGestor;
    ArrayList<Premio> lista;
    private GestorPremios(){lista = new ArrayList<Premio>();}
    public static GestorPremios getInstance(){
        if(GestorPremios.miGestor == null) GestorPremios.miGestor = new GestorPremios();
        return GestorPremios.miGestor;
    }
    public void anadirPremio(Premio pPremio) throws SQLException{
        this.lista.add(pPremio);
        String pUsuario = pPremio.getUsuario();
        int pRecompensa = pPremio.getRecompensa();
        String pDescr = pPremio.getDescripcion();
        Timestamp sqlTimestamp = pPremio.getSqlTimestamp();
        int pNivel = pPremio.getNivel();
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
