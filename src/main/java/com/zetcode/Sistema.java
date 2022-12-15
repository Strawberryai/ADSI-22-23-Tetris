package com.zetcode;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.simple.JSONValue;


public class Sistema {
    private static Sistema miSistema;
    private Sistema(){}

    public static Sistema getInstance(){
        if(Sistema.miSistema == null) Sistema.miSistema = new Sistema();
        return Sistema.miSistema;
    }

    public JSONObject comprobarUsuario(String usuario, String pass){
        return GestorUsuarios.getInstance().comprobarUsuario(usuario, pass);
    }

    public void guardarPartida(String usuario) throws IOException {
        Guardador.guardarPartida(usuario);
    }

    public Guardador cargarPartida(String f,String pUsuario,boolean esAdmin) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String jsonstring =Files.readString(Path.of(f));
        System.out.println(jsonstring);
        Guardador cargador=mapper.readValue(jsonstring, Guardador.class);
        EventQueue.invokeLater(() -> {

            Tetris tetris=new Tetris(cargador.getBOARD_HEIGHT(),cargador.getBOARD_WIDTH(),cargador.getPERIOD_INTERVAL(),true,cargador.getIsFallingFinished(),cargador.getIsPaused(),cargador.getNumLinesRemoved(),cargador.getCurX(),cargador.getCurY(),cargador.getCurPiece(),cargador.getBoard(),pUsuario,esAdmin);

        });
        return cargador;
    }
    public void jugarNuevaPartida(String pUsuario,boolean esAdmin){
        EventQueue.invokeLater(() -> {

            var game = new Tetris(10,22,300,false,false,false,0,0,0,null,null,pUsuario,esAdmin);
            game.setVisible(true);
        });
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

    private void datosAObjetosRanking(){
        GestorUsuarios.getInstance().datosAObjetos();
    }

    public org.json.JSONObject obtenerRankingGlobal(String pUsuario){
        datosAObjetosRanking();

        JSONObject ranking=new org.json.JSONObject();

        Usuario usu= GestorUsuarios.getInstance().buscarUsuario(pUsuario);
        org.json.JSONArray puntPers=GestorUsuarios.getInstance().obtenerPuntuacionJug(usu);
        org.json.JSONArray puntGlobal=Ranking.getInstance().obtenerPuntuacionesMax();
        ranking.put("global",puntGlobal);
        ranking.put("personal",puntPers);
        return ranking;

    }

    public JSONObject obtenerPuntuaciones(int pNivel, String pUsuario){
        datosAObjetosRanking();

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


    public String borrarUsuario(String usuario) {
        return GestorUsuarios.getInstance().borrarUsuario(usuario);
    }
    public void acabarPartida(int puntuacion,String usuario,int nivel){
        GestorPartida.getInstance().acabarPartida(puntuacion,usuario,nivel);
    }
    public void borrarSusPartidas(String usuario){
        Guardador eliminador=new Guardador();
        eliminador.eliminarSusPartidas(usuario);
    }
}
