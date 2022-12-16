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
    public void actualizarNivel(int pNivel){
        setNivel(pNivel);
        Board.getInstance().modificarBoardPorNivel(pNivel);


    }
    public void setNivel(int pNivel){
        GestorNivel.nivel = pNivel;
    }

}
