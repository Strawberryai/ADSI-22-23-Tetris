package eus.ehu.lsi.adsi;

import static org.junit.Assert.*;

import com.zetcode.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class GuardarTests {

    private static final Logger logger = LogManager.getLogger(GuardarTests.class);

    @Test
    public void insertINTO(){



    }

    @Test
    public void testGuardar() throws InterruptedException {
        String path = Paths.get("").toAbsolutePath().toString();
        String directorioGuardados = path + File.separator + "assets" + File.separator + "tetris_files" + File.separator + "Manuel" + "guardado" + File.separator;
        File fantes = new File(directorioGuardados);
        int cantArchivosAntes=0;
        if(fantes.exists()){//si existe el directorio se cuentan cuantas partidas hay, si no existe se toma 0
           cantArchivosAntes=fantes.list().length;
        }

        var game = new Tetris(10, 22, 300, false, false, false, 0, 0, 0, null, null, "Manuel", true);
        try {
            Guardador.guardarPartida("Manuel");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File fDesp = new File(directorioGuardados);
        int cantArchivosDesp = fDesp.list().length;
        System.out.println(cantArchivosDesp);
        System.out.println(cantArchivosAntes);
        String[] pathnames = fDesp.list();
        int i = 0;
        assertTrue(cantArchivosAntes + 1 == cantArchivosDesp);//Se comprueba que se genera un nuevo archivo

    }

    @Test
    public void testGuardarSinDirectorio(){
        String path = Paths.get("").toAbsolutePath().toString();
        String directorioGuardados = path + File.separator + "assets" + File.separator + "tetris_files" + File.separator + "JunitTests" + "guardado" + File.separator;
        File fantes = new File(directorioGuardados);
        if (fantes.exists()){
            Sistema.getInstance().borrarSusPartidas("JunitTests");
        }//se borra la carpeta
        int cantArchivosAntes = 0;//como no hay carpeta ni ficheros, antes habian 0 ficheros de partidas

        var game = new Tetris(10, 22, 300, false, false, false, 0, 0, 0, null, null, "Manuel", true);
        try {
            Guardador.guardarPartida("JunitTests");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File fDesp = new File(directorioGuardados);
        int cantArchivosDesp = fDesp.list().length; // se cuentan cuantos ficheros hay
        System.out.println(cantArchivosDesp);
        System.out.println(cantArchivosAntes);
        String[] pathnames = fDesp.list();
        int i = 0;
        assertTrue(cantArchivosAntes + 1 == cantArchivosDesp);//Se comprueba que se genera un nuevo archivo y las carpetas

    }
    @Test
    public void testCargar(){
        String path = Paths.get("").toAbsolutePath().toString();
        String directorioGuardados = path + File.separator + "assets" + File.separator + "tetris_files" + File.separator + "JunitJSON" + File.separator+"test";//se carga un archivo preparado para la prueba
        File fantes = new File(directorioGuardados);
        try {
            Guardador g=Sistema.getInstance().cargarPartida(directorioGuardados,"test",false);
            assertTrue((10==g.getNumLinesRemoved()&&(300==(g.getPERIOD_INTERVAL()))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testCargarDefect(){
        String path = Paths.get("").toAbsolutePath().toString();
        String directorioGuardados = path + File.separator + "assets" + File.separator + "tetris_files" + File.separator + "JunitJSON" + File.separator+"testDefect";//se carga un archivo preparado para la prueba
        File fantes = new File(directorioGuardados);
        try {
            Guardador g=Sistema.getInstance().cargarPartida(directorioGuardados,"test",false);
            assertTrue(false);//si sigue no detecta el error

        } catch (IOException e) {
            assertTrue(true);//si lo detecta el error es canalizado, si no fuera un JUNIT, saltaría una interfaz de error
        }

    }

    @Test
    public void testCargarArchivoInexistente(){
        String path = Paths.get("").toAbsolutePath().toString();
        String directorioGuardados = path + File.separator + "assets" + File.separator + "tetris_files" + File.separator + "JunitJSON" + File.separator+"JAJAJAXDLOLOLOLO";//se carga un archivo preparado para la prueba
        File fantes = new File(directorioGuardados);
        try {
            Guardador g=Sistema.getInstance().cargarPartida(directorioGuardados,"test",false);
            assertTrue(false);//si sigue no detecta el error

        } catch (IOException e) {
            assertTrue(true);//si lo detecta el error es canalizado, si no fuera un JUNIT, saltaría una interfaz de error
        }

    }
}
