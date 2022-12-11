package com.zetcode;

import org.json.JSONArray;
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

    public void anadirJugadorRanking(Usuario pUsuario){
        lista.add(pUsuario);
        ordenar();
    }

    public JSONArray obtenerMejoresJug(int pNivel){
        JSONArray listaDef = new JSONArray();
        Iterator<Usuario> itr=getItr();
        Usuario x=null;
        int min=0;
        int puntos;
        while(itr.hasNext()){
            x=itr.next();
            JSONArray partidasOrdNivel=x.buscarMejoresPartidasJug(pNivel);
            int i=0;
            int j=0; //jsonObjects en el array
            while(i< partidasOrdNivel.length()) {
                puntos=partidasOrdNivel.getJSONObject(i).getInt("puntuacion");
                if(puntos>min || j<25){
                    JSONObject nuevo=new JSONObject();
                    nuevo.put("usuario", x.getNombre());
                    nuevo.put("puntuacion", puntos);
                    int z=0;
                    boolean fin=false;
                    while(z<listaDef.length() && !fin && j==25){
                        if(listaDef.getJSONObject(z).getInt("puntuacion")<puntos){
                              while(z<listaDef.length()){
                                  if(listaDef.getJSONObject(z).getInt("puntuacion")==min){
                                      listaDef.remove(z);
                                      j--;
                                      fin=true;
                                  }
                              }
                        }
                        z++;
                    }
                    listaDef.put(nuevo);
                    j++;
                    min=puntos;
                }
                i++;
            }
        }

        return listaDef;
    }

    public void eliminarJugador(String usuario){
        Usuario usu = this.buscarUsuario(usuario);
        if(usu != null)
            this.lista.remove(usu);
    }
}
