package com.zetcode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class GestorPartida {
    private static GestorPartida miGestor;
    private Connection connection = null;
    private GestorPartida(){}
    public static GestorPartida getInstance(){
        if(GestorPartida.miGestor == null) GestorPartida.miGestor = new GestorPartida();
        return GestorPartida.miGestor;
    }
    public void guardarPartida(Date pFecha,int pNivel,int pPuntuacion,int pCodUsuario){
        GestorBD database = GestorBD.getInstance();
        database.executeStatement("INSERT INTO Partida (ID_JUGADOR, NIVEL, PUNTUACION,FECHAHORA) VALUES ('" + pCodUsuario+ "', '" + pNivel + "', '" + pPuntuacion + "', '" + pFecha + "')");
        
        database.imprimirTabla("PARTIDA");
    }

    public int obtenerVecesSuperada(String pUsuario, int nivel) throws SQLException {
        int idJugador = 0;
        GestorBD db = GestorBD.getInstance();
        int puntNecesaria = nivel*2;
        ResultSet queryID = db.executeQuery("SELECT ID FROM JUGADOR WHERE usuario = '" + pUsuario + "'");
        if (queryID.next()) {
            idJugador = queryID.getInt("ID");
        }
        ResultSet ResSQL = db.executeQuery("SELECT Count(*) as total FROM Partida WHERE ID_JUGADOR = " + idJugador + " AND nivel = " + nivel + " AND PUNTUACION >="+puntNecesaria);
            if (ResSQL.next()) {
            int vSuperada = ResSQL.getInt(1);
            System.out.println("Se ha superado " + vSuperada + " veces el nivel " + nivel);
            return vSuperada;
        }else{
            System.out.println("No ha devuleto ninguna columna");
            return 0;
        }
    }
}
