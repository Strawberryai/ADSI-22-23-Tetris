package com.zetcode;

import org.json.JSONArray;

public class Ranking {
    private ListaUsuarios miLista;
    private static Ranking miRanking;

    private Ranking(){
        miLista=new ListaUsuarios();
    }
    public static Ranking getInstance(){
        if(miRanking==null){
            miRanking=new Ranking();
        }
        return miRanking;
    }

    public JSONArray buscarMejoresJugadores(int pNivel){
        return miLista.obtenerMejoresJug(pNivel);
    }

    public org.json.JSONArray obtenerPuntuacionesMax(){
        return miLista.obtenerPuntuacionesMax();
    }

    public void anadirJugador(Usuario pUsuario){
        miLista.anadirJugadorRanking(pUsuario);
    }
}
