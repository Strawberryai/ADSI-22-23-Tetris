package com.zetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ListaPuntuacion {

    private ArrayList<Puntuacion> lista;

    public ListaPuntuacion(){
        lista=new ArrayList<Puntuacion>();
    }

    private void ordenarLista(){
        Comparator<Puntuacion> cm1=Comparator.comparing(Puntuacion::getPuntos);
        Collections.sort(lista,cm1);
    }

    private Iterator<Puntuacion> getItr(){return lista.iterator();
    }
    public void add(Puntuacion pPun){
        lista.add(pPun);
    }

    public int size(){
        return lista.size();
    }

    public int getPuntos(int indice){
        return lista.get(indice).getPuntos();
    }

    private void ordenarPorNivel(){
        Comparator<Puntuacion> cm1=Comparator.comparing(Puntuacion::nivelPartida);
        Collections.sort(lista,cm1);
    }



   /* public org.json.JSONArray buscarMejoresPartidasJug(int pNivel){
        ordenarPorNivel();
        while(){}

    }
    */

}
