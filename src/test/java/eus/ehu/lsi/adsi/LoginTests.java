package eus.ehu.lsi.adsi;

import com.zetcode.Sistema;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

public class LoginTests {
    private static final Logger logger = LogManager.getLogger(DatabaseTests.class);

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
}
