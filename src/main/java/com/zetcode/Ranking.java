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
        miLista.imprimirLista();
        return miLista.obtenerMejoresJug(pNivel);
    }

    public JSONArray obtenerPuntuacionesMax(){
        return miLista.obtenerPuntuacionesMax();
    }

    public void resetearLista(ListaUsuarios lista) {
        this.miLista = lista;
    }
}
