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
}
