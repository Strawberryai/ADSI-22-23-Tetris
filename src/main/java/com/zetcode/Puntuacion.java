package com.zetcode;

public class Puntuacion {

    private int puntos;
    private Partida laPartida;

    public Puntuacion(int pPuntos, Partida pLaP){
        puntos=pPuntos;
        laPartida=pLaP;
    }

    public int getPuntos(){
        return puntos;
    }

    public int obtenerPuntuacionPorNivel(int pNivel){
        if(laPartida.getNivel()==pNivel){
            return puntos;
        }else{ return 0;}

    }

    public int nivelPartida(){
        return laPartida.getNivel();
    }
}
