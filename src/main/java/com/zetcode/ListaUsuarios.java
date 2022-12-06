package com.zetcode;

import org.h2.util.json.JSONObject;

import java.sql.Array;

public class ListaUsuarios {
    private Usuario[] lista;

    public ListaUsuarios(){
        lista=new Usuario[100];
    }

    public Usuario buscarUsuario(String pUsu){
        int i=0;
        boolean enc=false;
        while(!enc && i<100){
            if(lista[i].getNombre().equals(pUsu)){
                enc=true;
            }else {
                i++;
            }
        }
        return lista[i];
    }


}
