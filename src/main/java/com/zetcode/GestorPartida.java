package com.zetcode;

import java.sql.Connection;
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
        database.executeStatement("INSERT INTO Partida (ID_JUGADOR, NIVEL, PUNTUACION,FECHAHORA) VALUES ('" + pCodUsuario+ "', '" + pNivel + "', '" + pNivel + "', '" + pFecha + "')");
    }
}
