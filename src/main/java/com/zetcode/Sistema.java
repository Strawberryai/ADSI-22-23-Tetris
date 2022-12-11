package com.zetcode;


import org.json.JSONObject;
import org.json.JSONArray;


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
       org.json.JSONArray puntGlobal=Ranking.getInstance().obtenerPuntuacionesMax();
       ranking.put("global",puntGlobal);
       ranking.put("personal",puntPers);
       return ranking;

    }

    public JSONObject obtenerPuntuaciones(int pNivel, String pUsuario){
        JSONObject elRanking=new JSONObject();
        Usuario usu= GestorUsuarios.getInstance().buscarUsuario(pUsuario);

        JSONArray puntPers=GestorUsuarios.getInstance().obtenerMejoresPuntJug(pNivel, usu);
        JSONArray puntGlobales=Ranking.getInstance().buscarMejoresJugadores(pNivel);
        elRanking.put("global", puntGlobales);
        elRanking.put("personal", puntPers);
        return elRanking;
    }

    public String cambiarContrasena(String usuario, String pass1, String pass2) {
        return GestorUsuarios.getInstance().cambiarContrasena(usuario, pass1, pass2);
    }





}
