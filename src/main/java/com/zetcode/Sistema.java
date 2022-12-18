package com.zetcode;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import twitter4j.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.simple.JSONValue;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;



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
    public void jugarNuevaPartida(String pUsuario,boolean esAdmin, int Height, int Width, int Period){
        EventQueue.invokeLater(() -> {
            var game = new Tetris(Height,Width,Period,false,false,false,0,0,0,null,null,pUsuario,esAdmin);
            game.setVisible(true);
        });
    }
    public void actualizarNivel(String pUsuario,boolean esAdmin, int pNivel){
        GestorNivel.getInstance().actualizarNivel(pUsuario,esAdmin, pNivel);
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
        System.out.println(puntPers.length());
        JSONArray puntGlobales=Ranking.getInstance().buscarMejoresJugadores(pNivel);
        System.out.println(puntGlobales.toString());
        elRanking.put("global", puntGlobales);
        elRanking.put("personal", puntPers);
        return elRanking;
    }

    public String cambiarContrasena(String usuario, String pass1, String pass2) {
        return GestorUsuarios.getInstance().cambiarContrasena(usuario, pass1, pass2);
    }

    //public void publicarResultadoFacebook(){
    //    
    //}

    //public void publicarResultadoInstagram(){
        
    //}

    public String borrarUsuario(String usuario) {
        return GestorUsuarios.getInstance().borrarUsuario(usuario);
    }
    public Timestamp acabarPartida(int puntuacion, String usuario, int nivel){
        int codUsuario=-1;//si es 1 error
        java.util.Date date = new java.util.Date();
        long t = date.getTime();
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(t);
        GestorBD BD=GestorBD.getInstance();
        ResultSet res=BD.executeQuery("SELECT ID FROM Jugador WHERE usuario='" + usuario + "'");
        try {
           if(res.next()){
               codUsuario=res.getInt("ID");
           }


        } catch (SQLException e) {e.printStackTrace();}
        GestorPartida.getInstance().guardarPartida(sqlTimestamp,nivel,puntuacion,codUsuario);
        return sqlTimestamp;
    }
    public void borrarSusPartidas(String usuario){
        Guardador eliminador=new Guardador();
        eliminador.eliminarSusPartidas(usuario);
    }

    public void actualizarConfiguracion(String pUsuario, String pColor, String pSonido, String pLadrillo){
        Usuario nuevo = GestorUsuarios.getInstance().buscarUsuario(pUsuario);
        GestorUsuarios.getInstance().actualizarConfiguracion(nuevo, pColor, pSonido,pLadrillo);
    }

    public boolean comprobarPremio(String pUsuario, int nivel, Timestamp sqlTimestamp, int premioAlDe) throws SQLException {
        int vSuperada = GestorPartida.getInstance().obtenerVecesSuperada(pUsuario, nivel);
        GestorPremios gestorPremios = GestorPremios.getInstance();
        boolean acabaDeGanar = false;
        int punt = 0;
        int puntNec = nivel*2;
        ResultSet resSQL = GestorBD.getInstance().executeQuery("SELECT PUNTUACION FROM Partida WHERE FECHAHORA = '" + sqlTimestamp + "'");
        if (resSQL.next()) {
            punt = resSQL.getInt("PUNTUACION");
            if (punt >= puntNec) {
                acabaDeGanar = true;
            }
        }

        if (vSuperada % premioAlDe == 0 && acabaDeGanar) {
            Premio premio = new Premio("Ha superado el nivel"+nivel, pUsuario, nivel, nivel, sqlTimestamp);
            gestorPremios.anadirPremio(premio);
            return true;
        }
        return false;
    }


    public void cargarDatosUsuarios(){
        GestorUsuarios.getInstance().datosAObjetos();
    }
}

