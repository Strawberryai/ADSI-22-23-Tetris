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
    public void actualizarNivel(String pUsuario,boolean esAdmin, int pNivel){
        setNivel(pNivel);
        if(Board.getInstance() != null){
            Board.getInstance().modificarBoardPorNiveles(pUsuario,esAdmin, getHeightPorNivel(pNivel), getWidthPorNivel(pNivel), getPeriodPorNivel(pNivel));
        }
        else{
            int Width = this.getWidthPorNivel(pNivel);
            int Height = this.getHeightPorNivel(pNivel);
            int Period = this.getPeriodPorNivel(pNivel);
            Sistema.getInstance().jugarNuevaPartida(pUsuario, esAdmin, Height, Width, Period);
        }
    }
    public int getWidthPorNivel(int pNivel){
        int statX = 10;
        if(pNivel == 1){
            statX = 10;

        } else if (pNivel == 2) {
            statX = 12;
        }
        else if(pNivel == 3){
            statX = 14;
        }
        return (statX);
    }
    public int getHeightPorNivel(int pNivel){
        int statY = 22;
        if(pNivel == 1){
            statY = 22;

        } else if (pNivel == 2) {
            statY = 21;
        }
        else if(pNivel == 3){
            statY = 20;
        }
        return (statY);
    }
    public int getPeriodPorNivel(int pNivel){
        int statV= 300;
        if(pNivel == 1){
            statV = 300;

        } else if (pNivel == 2) {
            statV = 150;
        }
        else if(pNivel == 3){
            statV = 75;
        }
        return (statV);
    }

    public void setNivel(int pNivel){
        GestorNivel.nivel = pNivel;
    }

}
