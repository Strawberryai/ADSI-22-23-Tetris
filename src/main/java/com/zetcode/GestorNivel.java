package com.zetcode;

import java.util.Date;

public class GestorNivel {

    private static GestorNivel miGestorNivel;
    private static int nivel;

    private GestorNivel(){
        nivel = 1;
    }
    public static GestorNivel getInstance(){
        if(GestorNivel.miGestorNivel == null) GestorNivel.miGestorNivel = new GestorNivel();
        return GestorNivel.miGestorNivel;
    }
    public void actualizarNivel(int pNivel,int pCodUsua){
        Board.getInstance().modificarBoardPorNivel(pNivel);
        GestorBD database = GestorBD.getInstance();
        //database.executeStatement("INSERT INTO Partida (ID_JUGADOR, NIVEL, PUNTUACION,FECHAHORA) VALUES ('" + pCodUsuario+ "', '" + pNivel + "', ')");
        database.imprimirTabla("PARTIDA");
    }
    public int obtenerNivel(int pNivel, int pCodUsu){
      int x = 1;
      return x;

    }
}
