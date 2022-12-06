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
        Puntuacion x=new Puntuacion(10, new Partida());
        Puntuacion y=new Puntuacion(20, new Partida());
        Puntuacion z=new Puntuacion(30, new Partida());
        lista.add(z);
        lista.add(x);
        lista.add(y);

        Comparator<Puntuacion> cm1=Comparator.comparing(Puntuacion::getPuntos);
        Collections.sort(lista,cm1);
        System.out.println(lista.get(0));
        System.out.println(lista.get(1));
        System.out.println(lista.get(2));
    }

}
