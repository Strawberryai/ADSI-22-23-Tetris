package eus.ehu.lsi.adsi;

import static org.junit.Assert.*;

import com.zetcode.GestorBD;
import com.zetcode.Sistema;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.zetcode.Shape;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;

public class DatabaseTests {

    private static final Logger logger = LogManager.getLogger(DatabaseTests.class);

    @Test
    public void insertINTO(){
        GestorBD database = GestorBD.getInstance();
        ResultSet res;

        // Tablas vacias ¿? -> Comentado porque si se ejecutan primero los tests de abajo falla porque ya hay datos
        /*try {
            res = database.executeQuery("SELECT * FROM Configuracion");
            assertFalse(res.next());
            res = database.executeQuery("SELECT * FROM Jugador");
            assertFalse(res.next());
            res = database.executeQuery("SELECT * FROM Partida");
            assertFalse(res.next());
            res = database.executeQuery("SELECT * FROM Gana");
            assertFalse(res.next());
        }catch (Exception e){e.printStackTrace();}*/

        // Inserts simples en tabla Jugador -> Manuel(1) y Gustavo(2)
        database.executeStatement("INSERT INTO Jugador (usuario, email, pass) VALUES ('Manuel', 'manuel@mail.com', '1234')");
        try{
            res = database.executeQuery("SELECT * FROM Jugador");
            assertTrue(res.next());
        }catch (Exception e){e.printStackTrace();}

        database.executeStatement("INSERT INTO Jugador (usuario, email, pass) VALUES ('Gustavo', 'gustavo@mail.com', '1234')");

        // Inserts en tabla Configuracion -> (1, "Azul" ,"Zelda Theme", "Pixelado")
        database.executeStatement("INSERT INTO Configuracion (colorPieza, musica, ladrillo) VALUES ('Azul', 'Zelda Theme', 'Pixelado')");
        try{
            res = database.executeQuery("SELECT * FROM Configuracion");
            assertTrue(res.next());
        }catch (Exception e){e.printStackTrace();}

        // Probamos a insertar un elemento con la misma clave en Configuracion -> No deja insertarlo
        // Queda comentado para poder ejecutar el resto de tests
        // database.executeStatement("INSERT INTO Configuracion (CodC, colorPieza, musica, ladrillo) VALUES (1, 'Rojo', 'Tetris Theme', 'Saturado')");

        // Insertamos el resultado de una partida de ejemplo -> Manuel(1), 100, 3
        database.executeStatement("INSERT INTO Partida (ID_Jugador, puntuacion, nivel) VALUES (1, 100, 3)");
        try{
            res = database.executeQuery("SELECT * FROM Partida");
            assertTrue(res.next());
        }catch (Exception e){e.printStackTrace();}

        // Insertamos un Premio de ejemplo en la tabla Premio -> (1, 200, "Un premio de ejemplo")
        database.executeStatement("INSERT INTO Premio (recompensa, descripcion) VALUES (200, 'Un premio de ejemplo')");
        try{
            res = database.executeQuery("SELECT * FROM Premio");
            assertTrue(res.next());
        }catch (Exception e){e.printStackTrace();}

        // Instertamos una tupla en Gana de ejemplo -> (1, TIMESTAMP, 1) -> Manuel gana el premio 1
        // Tomamaos el TIMESTAMP de una partida jugadoa por Manuel
        Timestamp timeStamp = Timestamp.from(Instant.now());
        try{
            res = database.executeQuery("SELECT * FROM Partida WHERE ID_Jugador = '1'");
            while(res.next()){
                int id_usuario = res.getInt("ID_Jugador");
                assertEquals(1, id_usuario);
                timeStamp = res.getTimestamp("fechaHora");

                logger.info("Partida: (" + id_usuario + ", " + timeStamp + ")");
            }

        }catch (Exception e){e.printStackTrace();}

        database.executeStatement("INSERT INTO Gana (ID_Jugador, fechaHora, codPremio) VALUES (1, '" + timeStamp + "', 1)");
        try{
            res = database.executeQuery("SELECT * FROM Gana");
            assertTrue(res.next());
        }catch (Exception e){e.printStackTrace();}
    }

    @Test
    public void testLog(){
        GestorBD database = GestorBD.getInstance();

        // Volcando datos en tablas
        database.executeStatement("INSERT INTO Jugador (usuario, email, pass) VALUES ('Manuel', 'manuel@mail.com', '1234')");
        database.executeStatement("INSERT INTO Jugador (usuario, email, pass) VALUES ('Gustavo', 'gustavo@mail.com', '1234')");

        ResultSet res = database.executeQuery("SELECT * FROM Jugador");
        int count = 0;
        try {
            while (res.next()) {
                count++;
                int ID = res.getInt("ID");
                String usuario = res.getString("usuario");
                String pass = res.getString("pass");
                logger.info("Student #" + count + ": " + ID + ", " + usuario + ", " + pass);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        String usuario = "Manuel";
        String pass = "1234";
        boolean ident = Sistema.getInstance().comprobarUsuario("Manuel", "1234");
        logger.info("Usuario: " + usuario + " con contraseña: " + pass + " -> identificado: " + ident);
        assertTrue(ident);

        usuario = "Manuel";
        pass = "1";
        ident = Sistema.getInstance().comprobarUsuario(usuario, pass);
        logger.info("Usuario: " + usuario + " con contraseña: " + pass + " -> identificado: " + ident);
        assertFalse(ident);

        usuario = "Fernando";
        pass = "1234";
        ident = Sistema.getInstance().comprobarUsuario(usuario, pass);
        logger.info("Usuario: " + usuario + " con contraseña: " + pass + " -> identificado: " + ident);
        assertFalse(ident);

        usuario = "Fernando";
        pass = "1";
        ident = Sistema.getInstance().comprobarUsuario(usuario, pass);
        logger.info("Usuario: " + usuario + " con contraseña: " + pass + " -> identificado: " + ident);
        assertFalse(ident);

        usuario = "Gustavo";
        pass = "1";
        ident = Sistema.getInstance().comprobarUsuario(usuario, pass);
        logger.info("Usuario: " + usuario + " con contraseña: " + pass + " -> identificado: " + ident);
        assertFalse(ident);

        usuario = "Gustavo";
        pass = "1234";
        ident = Sistema.getInstance().comprobarUsuario(usuario, pass);
        logger.info("Usuario: " + usuario + " con contraseña: " + pass + " -> identificado: " + ident);
        assertTrue(ident);
    }


}
