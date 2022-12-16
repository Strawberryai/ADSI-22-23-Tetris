package com.zetcode;


import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class Usuario {
    private String usuario;
    private String contrasena;

    private String email;
    private int puntosMax;
    private boolean esAdmin;
    private Configuracion config;
    private Collection<Premio> listaP;
    private ListaPuntuacion listaPuntos;


    public Usuario(String pUsuario,String pContra,String pEmail, int pPuntosMax,boolean pAdmin,int pConfig){
        usuario=pUsuario;
        contrasena=pContra;
        email=pEmail;
        puntosMax = pPuntosMax;
        esAdmin=pAdmin;
        //listaP=new Collection<Premio>();
        listaPuntos=new ListaPuntuacion();

        config = cargarConfiguracion(pConfig);
    }

    private Configuracion cargarConfiguracion(int pCodC){
        GestorBD dataase = GestorBD.getInstance();
        ResultSet res = dataase.executeQuery("SELECT * FROM Configuracion");
        String color="predeterminado";
        String ladrillo = "predeterminado";
        String sonido = "predeterminado";
        boolean usuario ;

        try {
            usuario=res.next();
            if(usuario){
                color = res.getString("color");
                ladrillo = res.getString("ladrillo");
                sonido = res.getString("musica");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //TODO: meter los datos
        return new Configuracion(pCodC,color, ladrillo, sonido);
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
        config.actualizarConfiguracion(pColor, pSonido, pLadrillo);
    }
    public Configuracion getConfig(){
        return config;
    }

    public void actualizarPuntosMax(int pPuntos){
        puntosMax=pPuntos;
    }
}


