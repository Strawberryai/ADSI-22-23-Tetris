package com.zetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListaPuntuacion {

    private ArrayList<Puntuacion> lista;

    public ListaPuntuacion(){
        lista=new ArrayList<Puntuacion>();
    }

    private void ordenarLista(){

        Comparator<Puntuacion> cm1=Comparator.comparing(Puntuacion::getPuntos);
        Collections.sort(lista,cm1);
    }

    public void add(Puntuacion pPun){
        lista.add(pPun);
    }

}
