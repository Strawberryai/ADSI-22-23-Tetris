package com.zetcode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
        database.imprimirTabla("JUGADOR");
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

    public Timestamp acabarPartida(int pPuntuacion, String pUsuario, int pNivel){
        int codUsuario=-1;//si es 1 error
        java.util.Date date = new java.util.Date();
        long t = date.getTime();
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(t);
        GestorBD BD=GestorBD.getInstance();
        ResultSet res=BD.executeQuery("SELECT ID FROM Jugador WHERE usuario='" + pUsuario + "'");
        try {
            if(res.next()){
                codUsuario=res.getInt("ID");
                GestorUsuarios.getInstance().datosAObjetos();
                Usuario usu=GestorUsuarios.getInstance().buscarUsuario(pUsuario);
                if(pPuntuacion> usu.obtenerPuntuacionMax()){
                    BD.executeStatement("UPDATE Jugador SET puntosMax='"+pPuntuacion+"' WHERE usuario='" + pUsuario + "'");
                    usu.actualizarPuntosMax(pPuntuacion);
                }
            }


        } catch (SQLException e) {e.printStackTrace();}

        guardarPartida(sqlTimestamp,pNivel,pPuntuacion,codUsuario);
        
        System.out.println("Se ha guardado la partida");
        return sqlTimestamp;
    }
}
