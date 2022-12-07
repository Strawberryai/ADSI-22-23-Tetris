package com.zetcode;

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

    public Usuario tieneMismoNombre(String pUsu){
        if(pUsu.equals(usuario)){
            return this;
        }else{return null;}
    }

    public String getNombre(){
        return usuario;
    }

    public int obtenerPuntuacionesMax(){
        return puntosMax;
    }

    public void anadirListaPuntuacion(Puntuacion pPuntos){
        listaPuntos.add(pPuntos);
    }

}


