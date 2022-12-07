package com.zetcode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.util.json.JSONArray;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

public class GestorUsuarios {
    private static final Logger logger = LogManager.getLogger(GestorUsuarios.class);
    private static GestorUsuarios misUsuarios;
    private ListaUsuarios lista;

    private GestorUsuarios(){
        lista=new ListaUsuarios();
    }
    public static GestorUsuarios getInstance(){
        if(GestorUsuarios.misUsuarios == null) GestorUsuarios.misUsuarios = new GestorUsuarios();
        return GestorUsuarios.misUsuarios;
    }

    public static boolean isValidEmail(String email) {
        // PRE: un string
        // POST: un boolean indicando si tiene formato de email

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public boolean comprobarUsuario(String usuario, String pass){
        // PRE: usuario y contraseña
        // POST: boolean indicando si el login es correcto

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

    public String validarRegistro(String usuario, String mail, String pass) {
        // PRE: datos de registro
        // POST: boolean indicando si son válidos

        GestorBD database = GestorBD.getInstance();
        ResultSet res = database.executeQuery("SELECT * FROM Jugador WHERE usuario='" + usuario + "'");
        boolean existeUsuario = true;

        try {
            existeUsuario = res.next();
        } catch (SQLException e) {e.printStackTrace();}

        if(Objects.equals(usuario, "") || Objects.equals(mail, "") || Objects.equals(pass, ""))
            return "Campo vacío";

        if(existeUsuario)
            return "Usuario ya registrado";

        if(!isValidEmail(mail))
            return "Email no válido";

        if(pass.length() < 3)
            return "Contraseña no válida. Requiere de al menos 3 carácteres.";

        return null;
    }

    public void registrarUsuario(String usuario, String mail, String pass) {
        // PRE: Datos de registro válidos
        // POST: Usuario registrado

        GestorBD database = GestorBD.getInstance();
        database.executeStatement("INSERT INTO Jugador (usuario, email, pass) VALUES ('" + usuario + "', '" + mail + "', '" + pass + "')");
    }

    public boolean recuperarContrasena(String usuario) {
        // PRE: string
        // POST: si es un usuario del sistema tomamos sus datos, enviamos un email de recuperacion y devolvemos true. Si no lo es devolvemos false.

        GestorBD database = GestorBD.getInstance();
        ResultSet res = database.executeQuery("SELECT * FROM Jugador WHERE usuario='" + usuario + "'");
        boolean existeUsuario = true;
        String email, pass;

        try {
            existeUsuario = res.next();
            if(existeUsuario){
                email = res.getString("email");
                pass = res.getString("pass");
                GestorEmail.getInstance().enviarEmailRecuperacion(email, pass);
            }

        } catch (SQLException e) {e.printStackTrace();}

        return existeUsuario;
    }

    public void datosAObjetos() {
        GestorBD database = GestorBD.getInstance();
        boolean partida=true,usuario = true;
        ResultSet res = database.executeQuery("SELECT * FROM Jugador");
        int puntosMax,puntos,nivel;
        Date fecha;
        boolean esAdmin;
        String pass,nomUsu;
        int config;
        while (usuario) {
            try {
                usuario = res.next();
                if (usuario) {
                    nomUsu = res.getString(1);
                    pass = res.getString(2);
                    puntosMax = res.getInt(3);
                    esAdmin = res.getBoolean(4);
                    config = res.getInt(5);
                    Usuario x = new Usuario(nomUsu, pass, puntosMax, esAdmin, config);
                    lista.add(x);
                    ResultSet resPartida = database.executeQuery("SELECT * FROM Partida WHERE JUGADORUsuario='" + nomUsu + "'");

                    while (partida) {
                        try {
                            partida = resPartida.next();
                            if (partida) {
                                fecha = resPartida.getDate(1);
                                puntos = resPartida.getInt(2);
                                nivel = resPartida.getInt(3);
                                Puntuacion y = new Puntuacion(puntos, new Partida(fecha, nivel));
                                x.anadirListaPuntuacion(y);
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public Usuario buscarUsuario(String pUsuario){
        return lista.buscarUsuario(pUsuario);
    }

    public org.json.JSONArray obtenerPuntuacionJug(Usuario pUsuario){
        return pUsuario.obtenerPuntuacionesMax();
    }
    public org.json.JSONArray obtenerPuntuacionesMax(){
        return lista.obtenerPuntuacionesMax();
    }
}
