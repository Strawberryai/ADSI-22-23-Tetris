package eus.ehu.lsi.adsi;

import com.zetcode.Sistema;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

public class LoginTests {
    private static final Logger logger = LogManager.getLogger(DatabaseTests.class);

    /*
    * Toda la lógica indicada en las especificaciones de los tests
    * están probadas en DatabaseTests
    */

    @Test
    public void logIn() {
        Sistema sis = Sistema.getInstance();

        JSONObject res = sis.comprobarUsuario("Manuel", "1234");
        assertTrue(res.getBoolean("identificado"));
        assertTrue(res.getBoolean("esAdmin"));

        res = sis.comprobarUsuario("Manuel", "12345");
        assertFalse(res.getBoolean("identificado"));
        assertFalse(res.getBoolean("esAdmin"));

        res = sis.comprobarUsuario("USUARioRandomquenuncaVaAestarRegistrado", "1234");
        assertFalse(res.getBoolean("identificado"));
    }

    @Test
    public void registro(){
        Sistema sis = Sistema.getInstance();
        JSONObject res = sis.comprobarUsuario("test_user1", "1234");
        assertFalse(res.getBoolean("identificado"));

        sis.registrarUsuario("test_user1", "mail@mail.com", "1234");
        res = sis.comprobarUsuario("test_user1", "1234");
        assertTrue(res.getBoolean("identificado"));
    }

    @Test
    public void cambiarPass(){
        Sistema sis = Sistema.getInstance();

        JSONObject res = sis.comprobarUsuario("test_user1", "1234");
        assertTrue(res.getBoolean("identificado")); // si falla aquí es que no existe el test_user1

        String error = sis.cambiarContrasena("test_user1", "", "");
        assertEquals("Campo vacío", error);

        error = sis.cambiarContrasena("test_user1", "12", "12");
        assertEquals("La contraseña requiere de al menos 3 carácteres", error);

        error = sis.cambiarContrasena("test_user1", "nueva1", "diferente");
        assertEquals("Las contraseñas no coinciden", error);

        error = sis.cambiarContrasena("test_user1", "nueva", "nueva");
        assertNull(error);
    }
}
