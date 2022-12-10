package com.zetcode;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;
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
        System.out.println("Prueba de guardado");
        String userHomeDir = System.getProperty("user.home");
        if(Files.notExists(Path.of(userHomeDir + "/TetrisSaveFiles"))){
            File dir = new File(userHomeDir + "/TetrisSaveFiles");
            dir.mkdirs();
        }

        if(Files.notExists(Path.of(userHomeDir + "/TetrisSaveFiles/" + usuario + "guardado"))){
            File dir = new File(userHomeDir + "/TetrisSaveFiles/" + usuario + "guardado");
            dir.mkdirs();
        }
        else{
            System.out.println("directorio ya creado");
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        String fecha=formatter.format(date);
        String jsonString=Board.getInstance().guardar();
        FileWriter file=new FileWriter(userHomeDir + "/TetrisSaveFiles/" + usuario + "guardado/"+ fecha);
        file.write(jsonString);
        file.close();
        Tetris.acabar();
    }

    public void cargarPartida(String f) throws IOException {
        //Tetris.cargar();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        //PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder().build();
        //mapper.activateDefaultTyping(ptv); // default to using DefaultTyping.OBJECT_AND_NON_CONCRETE
        //mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        String jsonstring =Files.readString(Path.of(f));
        System.out.println(jsonstring);
        //Guardador cargador=null;
        Object obj= JSONValue.parse(jsonstring);
        JSONObject jsonObject = (JSONObject) obj;
        /*try{
            cargador=new Gson().fromJson(jsonstring,Guardador.class);
        }
        catch (JsonIOException e){
            cargador=null;
        }*/
        //System.out.println(cargador);

        //Timer timer=(Timer)jsonObject.get("timer");

        Guardador cargador=mapper.readValue(jsonstring, Guardador.class);
        //boolean isFallingFinished =(boolean)jsonObject.get("isFallingFinished");

        //boolean isPaused =(boolean)jsonObject.get("isPaused");
        //int numLinesRemoved = (int)(long)jsonObject.get("numLinesRemoved");
        //int curX = (int)(long)jsonObject.get("curX");
        //int curY = (int)(long)jsonObject.get("curY");
        //Shape curPiece=(Shape)jsonObject.get("curPiece");
        System.out.println(cargador.getIsFallingFinished());
        System.out.println(cargador.getCurX());
        System.out.println(cargador.getCurY());

        //Shape curpiece=mapper.readValue(jsonstring, Shape.class);
        //Shape.Tetrominoe[] board= new Shape.Tetrominoe[]{mapper.readValue(jsonstring, Shape.Tetrominoe.class)};
        //Board.getInstance().cargar(isFallingFinished,isPaused,numLinesRemoved,curX,curY,curPiece,board);
        Tetris tetris=new Tetris(true,cargador.getIsFallingFinished(),cargador.getIsPaused(),cargador.getNumLinesRemoved(),cargador.getCurX(),cargador.getCurY(),cargador.getCurPiece(),cargador.getBoard());

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
/*  PROBLEMAS CON EL JSON
    public org.json.JSONObject obtenerRankingGlobal(String pUsuario){
        JSONObject ranking=new org.json.JSONObject();

       Usuario usu= GestorUsuarios.getInstance().buscarUsuario(pUsuario);
       org.json.JSONArray puntPers=GestorUsuarios.getInstance().obtenerPuntuacionJug(usu);
       org.json.JSONArray puntGlobal=Ranking.getInstance().obtenerPuntuacionesMax();
       ranking.put("global",puntGlobal);
       ranking.put("personal",puntPers);
       return ranking;

    }

    public org.json.JSONObject obtenerPuntuaciones(int pNivel, String pUsuario){
        JSONObject elRanking=new org.json.JSONObject();
        Usuario usu= GestorUsuarios.getInstance().buscarUsuario(pUsuario);
        org.json.JSONArray puntPers=GestorUsuarios.getInstance().obtenerMejoresPuntJug(pNivel, usu);
        org.json.JSONArray puntGlobales=Ranking.getInstance().buscarMejoresJugadores(pNivel);
        elRanking.put("global", puntGlobales);
        elRanking.put("personal", puntPers);
        return elRanking;
    }
*/
    public String cambiarContrasena(String usuario, String pass1, String pass2) {
        return GestorUsuarios.getInstance().cambiarContrasena(usuario, pass1, pass2);
    }


    public String borrarUsuario(String usuario) {
        return GestorUsuarios.getInstance().borrarUsuario(usuario);
    }
}
