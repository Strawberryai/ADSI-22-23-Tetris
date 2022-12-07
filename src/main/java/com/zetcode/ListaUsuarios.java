package com.zetcode;

import org.h2.util.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
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
}
