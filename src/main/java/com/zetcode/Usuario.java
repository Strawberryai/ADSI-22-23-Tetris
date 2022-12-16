package com.zetcode;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;

public class Usuario {
    private String usuario;
    private String contrasena;

    private String email;
    private int puntosMax;
    private boolean esAdmin;
    private int laCfg;
    private Collection<Premio> listaP;
    private ListaPuntuacion listaPuntos;

    public Usuario(String pUsuario,String pContra,String pEmail, int pPuntosMax,boolean pAdmin,int pConfig){
        usuario=pUsuario;
        contrasena=pContra;
        email=pEmail;
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
        System.out.println(listaPuntos.size()+" Num en lista Personal");
        while(i<listaPuntos.size()){
            JSONObject partida=new JSONObject();
            partida.put("usuario",usuario);
            partida.put("puntuacion",listaPuntos.getPuntos(i));
            listaA.put(partida);
            i++;
        }
        return listaA;
    }

    public JSONArray buscarMejoresPartidasJug(int pNivel){
        System.out.println(listaPuntos.size()+" Num en lista Puntos Global");
       return listaPuntos.buscarMejoresPartidasJug(pNivel, usuario);
    }

    public void actualizarConfiguracion(String pColor, String pSonido, String pLadrillo){
        // TODO: implementar esto
    }

    public void actualizarPuntosMax(int pPuntos){
        puntosMax=pPuntos;
    }
}


