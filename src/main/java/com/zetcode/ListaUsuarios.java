package com.zetcode;

import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ListaUsuarios {
    private ArrayList<Usuario> lista;

    public ListaUsuarios(){
        lista=new ArrayList<Usuario>();
    }

    private Iterator<Usuario> getItr(){
        return this.lista.iterator();
    }
    public Usuario buscarUsuario(String pUsu){
        Iterator<Usuario> itr=this.getItr();
        boolean enc=false;
        Usuario x=null;
        while(itr.hasNext() && !enc){
            x=itr.next();
            if(x.tieneMismoNombre(pUsu)){
                enc=true;
            }
        }
        return x;
    }

    public void add(Usuario pUsuario){
        lista.add(pUsuario);
    }

    private void ordenar(){
        Comparator<Usuario> cm1=Comparator.comparing(Usuario::obtenerPuntuacionMax);
        Collections.sort(lista,cm1);
    }

    public org.json.JSONArray obtenerPuntuacionesMax(){
        ordenar();
        org.json.JSONArray listaPuntos=new org.json.JSONArray();
        Iterator<Usuario> itr=this.getItr();
        int i=0;
        Usuario x=null;
        while(itr.hasNext() && i<10){
            x=itr.next();
            JSONObject partida=new org.json.JSONObject();
            partida.put("usuario",x.getNombre());
            partida.put("puntos",x.obtenerPuntuacionMax());
            listaPuntos.put(partida);
            i++;
        }
        return listaPuntos;
    }
}
