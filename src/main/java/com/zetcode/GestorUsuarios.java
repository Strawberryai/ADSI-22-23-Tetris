package com.zetcode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorUsuarios {
    private static final Logger logger = LogManager.getLogger(GestorUsuarios.class);
    private static GestorUsuarios misUsuarios;

    private GestorUsuarios(){}
    public static GestorUsuarios getInstance(){
        if(GestorUsuarios.misUsuarios == null) GestorUsuarios.misUsuarios = new GestorUsuarios();
        return GestorUsuarios.misUsuarios;
    }

    public boolean comprobarUsuario(String usuario, String pass){
        GestorBD database = GestorBD.getInstance();

        ResultSet res = database.executeQuery("SELECT * FROM Jugador WHERE usuario='" + usuario + "' AND pass='" + pass + "'");
        boolean identificado = false;

        try {
            identificado = res.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return identificado;
    }
}
