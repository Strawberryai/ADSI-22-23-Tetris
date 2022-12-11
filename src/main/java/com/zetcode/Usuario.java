package com.zetcode;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;

public class Usuario {
    private String usuario;
    private String contrasena;
    private int puntosMax;
    private boolean esAdmin;
    private int laCfg;
    private Collection<Premio> listaP;
    private ListaPuntuacion listaPuntos;

    public Usuario(String pUsuario,String pContra,int pPuntosMax,boolean pAdmin,int pConfig){
        usuario=pUsuario;
        contrasena=pContra;
        puntosMax = pPuntosMax;
        esAdmin=pAdmin;
        laCfg=pConfig;
        //listaP=new Collection<Premio>();
        listaPuntos=new ListaPuntuacion();
    }

    public boolean tieneMismoNombre(String pUsu){
        if(pUsu.equals(usuario)){
            return true;
        }else{return false;}
    }

    public String getNombre(){
        return usuario;
    }

    public int obtenerPuntuacionMax(){
        return puntosMax;
    }

    public void anadirListaPuntuacion(Puntuacion pPuntos){
        listaPuntos.add(pPuntos);
    }

    public JSONArray obtenerPuntuacionesMax(){
        JSONArray listaA=new JSONArray();
        int i=0;
        listaPuntos.ordenarLista(listaPuntos.getLista());
        while(i<listaPuntos.size()){
            JSONObject partida=new JSONObject();
            partida.put("usuario",usuario);
            partida.put("puntos",listaPuntos.getPuntos(i));
            listaA.put(partida);
            i++;
        }
        return listaA;
    }

    public org.json.JSONArray buscarMejoresPartidasJug(int pNivel){
       return listaPuntos.buscarMejoresPartidasJug(pNivel, usuario);
    }

}


