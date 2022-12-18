package com.zetcode;

import java.util.Date;

public class GestorNivel {

    private static GestorNivel miGestorNivel;
    public static int nivel;

    private GestorNivel(){
        nivel = 1;
    }
    public static GestorNivel getInstance(){
        if(GestorNivel.miGestorNivel == null) GestorNivel.miGestorNivel = new GestorNivel();
        return GestorNivel.miGestorNivel;
    }
    public void actualizarNivel(String pUsu, boolean esAdmin, int Height, int Width, int Period, int pNiv){
        setNivel(pNiv);
        if(Board.getInstance() != null) {
            Board.getInstance().modificarBoardPorNivel(pUsu, esAdmin, Height, Width, Period);
        }
        else{
            Sistema.getInstance().jugarNuevaPartida(pUsu, esAdmin, Height, Width, Period);
        }


    }
    public void setNivel(int pNivel){
        GestorNivel.nivel = pNivel;
    }

}
