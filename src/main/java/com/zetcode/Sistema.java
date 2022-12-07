package com.zetcode;

import org.h2.util.json.JSONArray;
import org.json.JSONObject;

public class Sistema {
    private static Sistema miSistema;
    private Sistema(){}

    public static Sistema getInstance(){
        if(Sistema.miSistema == null) Sistema.miSistema = new Sistema();
        return Sistema.miSistema;
    }

    public boolean comprobarUsuario(String usuario, String pass){
        return GestorUsuarios.getInstance().comprobarUsuario(usuario, pass);
    }

    public String validarRegistro(String usuario, String mail, String pass) {
        return GestorUsuarios.getInstance().validarRegistro(usuario, mail, pass);
    }

    public void registrarUsuario(String usuario, String mail, String pass) {
        GestorUsuarios.getInstance().registrarUsuario(usuario, mail, pass);
    }

    public boolean recuperarContrasena(String usuario) {
        return GestorUsuarios.getInstance().recuperarContrasena(usuario);
    }

    public void datosAObjetosRanking(){
        GestorUsuarios.getInstance().datosAObjetos();
    }

    public org.json.JSONObject obtenerRankingGlobal(String pUsuario){
        JSONObject ranking=new org.json.JSONObject();

       Usuario usu= GestorUsuarios.getInstance().buscarUsuario(pUsuario);
       org.json.JSONArray puntPers=GestorUsuarios.getInstance().obtenerPuntuacionJug(usu);
       org.json.JSONArray puntGlobal=GestorUsuarios.getInstance().obtenerPuntuacionesMax();
        ranking.put("global",puntGlobal);
        ranking.put("personal",puntPers);
       return ranking;

    }

   /* public org.json.JSONObject obtenerRankingPorNiveles(String pUsuario){
        JSONObject ranking=new org.json.JSONObject();
        Usuario usu= GestorUsuarios.getInstance().buscarUsuario(pUsuario);
        org.json.JSONArray puntPers=GestorUsuarios.getInstance().obtenerPuntuacionJug(usu);
    }

*/


}
