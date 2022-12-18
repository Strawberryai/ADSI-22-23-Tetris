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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PremioTest {

    private static final Logger logger = LogManager.getLogger(GuardarTests.class);

    @Test
    public void testPrimeraVictoria() throws SQLException {

        Boolean haRecibido = false;

        //Creamos un timestamp con el que guardaremos una partida de prueba (asumimos que guardar partida funciona perfectamente)
        java.util.Date date = new java.util.Date();
        long t = date.getTime();
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(t);

        //Gestores con los que hacer pruebas
        GestorBD SGBD = GestorBD.getInstance();
        GestorPartida GP = GestorPartida.getInstance();

        //Generamos una partida que supuestamente ha conseguido un premio para el jugador con ID == 2, que no tiene ninguna partida ganada
        GP.guardarPartida(sqlTimestamp,1,2,2);


        haRecibido = Sistema.getInstance().comprobarPremio("Gustavo", 1, sqlTimestamp, 1);
        assertTrue(haRecibido);
    }

    @Test
    //Este test mide si acaba de superar una partida y si supera la puntuación mínima, ya que el superar un nivel o no se mide por puntuación.
    public void testNoSupera() throws SQLException {

        Boolean haRecibido = false;

        //Creamos un timestamp con el que guardaremos una partida de prueba (asumimos que guardar partida funciona perfectamente)
        java.util.Date date = new java.util.Date();
        long t = date.getTime();
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(t);

        date = new java.util.Date();
        t = date.getTime();
        java.sql.Timestamp sqlTimestamp2 = new java.sql.Timestamp(t);

        date = new java.util.Date();
        t = date.getTime();
        java.sql.Timestamp sqlTimestamp3 = new java.sql.Timestamp(t);

        //Gestores con los que hacer pruebas
        GestorBD SGBD = GestorBD.getInstance();
        GestorPartida GP = GestorPartida.getInstance();

        //Generamos una partida que supuestamente ha conseguido un premio para el jugador con ID == 2, que no tiene ninguna partida ganada
        GP.guardarPartida(sqlTimestamp,1,0,2);


        haRecibido = Sistema.getInstance().comprobarPremio("Gustavo", 1, sqlTimestamp, 1);
        assertTrue(!haRecibido);
    }

    @Test
    public void testPartida8() throws SQLException {

        Boolean haRecibido = false;

        //Creamos un timestamp con el que guardaremos una partida de prueba (asumimos que guardar partida funciona perfectamente)
        java.util.Date date = new java.util.Date();
        long t = date.getTime();
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(t);

        //Gestores con los que hacer pruebas
        GestorBD SGBD = GestorBD.getInstance();
        GestorPartida GP = GestorPartida.getInstance();

        //Generamos una partida que supuestamente ha conseguido un premio para el jugador con ID == 2, que no tiene ninguna partida ganada
        GP.guardarPartida(sqlTimestamp,8,16,2);


        haRecibido = Sistema.getInstance().comprobarPremio("Gustavo", 1, sqlTimestamp, 1);
        assertTrue(haRecibido);
    }
    
}

