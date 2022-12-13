package com.zetcode;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ListaPuntuacion {

    private ArrayList<Puntuacion> lista;

    public ListaPuntuacion(){
        lista=new ArrayList<Puntuacion>();
    }

    public void ordenarLista(ArrayList<Puntuacion> pLista){
        Comparator<Puntuacion> cm1=Comparator.comparing(Puntuacion::getPuntos);
        Collections.sort(pLista,cm1);
    }

    public ArrayList<Puntuacion> getLista(){
        return lista;
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

    public JSONArray buscarMejoresPartidasJug(int pNivel, String pUsu){
        JSONArray listaPart=new JSONArray();
        int anadido=0;
        ArrayList<Puntuacion> aux=new ArrayList<Puntuacion>();
        ordenarPorNivel();
        Iterator<Puntuacion> itr=getItr();
        while(itr.hasNext() && anadido<20){
            Puntuacion punt=itr.next();
            if(punt.nivelPartida()==pNivel){
                aux.add(punt);
                anadido++;
            }
        }

        ordenarLista(aux);
        Iterator<Puntuacion> itr2= aux.iterator();
        while(itr2.hasNext()){
            Puntuacion punt=itr2.next();
            JSONObject part=new JSONObject();
            part.put("usuario",pUsu);
            part.put("puntuacion", punt.getPuntos());
            listaPart.put(part);
        }

        return listaPart;

    }

}
