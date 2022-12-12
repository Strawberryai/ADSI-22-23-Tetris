package com.zetcode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONArray;
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

    public JSONObject comprobarUsuario(String usuario, String pass){
        // PRE: usuario y contraseña
        // POST: boolean indicando si el login es correcto

        GestorBD database = GestorBD.getInstance();

        ResultSet res = database.executeQuery("SELECT * FROM Jugador WHERE usuario='" + usuario + "' AND pass='" + pass + "'");
        boolean identificado = false;
        boolean esAdmin = false;

        try {
            identificado = res.next();
            if(identificado){
                esAdmin = res.getBoolean("esAdmin");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JSONObject obj = new JSONObject();
        obj.put("identificado", identificado);
        obj.put("esAdmin", esAdmin);

        return obj;
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

    public String cambiarContrasena(String usuario, String pass1, String pass2) {
        if(Objects.equals(pass1, "") || Objects.equals(pass2, ""))
            return "Campo vacío";

        if(!Objects.equals(pass1, pass2))
            return "Las contraseñas no coinciden";

        if(pass1.length() < 3)
            return "La contraseña requiere de al menos 3 carácteres";

        GestorBD database = GestorBD.getInstance();
        database.executeStatement("UPDATE Jugador SET pass = '" + pass1 + "' WHERE usuario = '" + usuario + "'");

        return null;
    }


    public void datosAObjetos() {
        // Reiniciar la lista de usuarios -> problema usuarios repetidos
        lista = new ListaUsuarios();

        GestorBD database = GestorBD.getInstance();
        boolean partida=true,usuario = true;
        ResultSet res = database.executeQuery("SELECT * FROM Jugador");
        int puntosMax,puntos,nivel;
        Date fecha;
        boolean esAdmin;
        String pass,nomUsu,email;
        int config,id;
        while (usuario) {
            try {
                usuario = res.next();
                if (usuario) {
                    id=res.getInt("ID");
                    nomUsu = res.getString("usuario");
                    pass = res.getString("pass");
                    email=res.getString("email");
                    puntosMax = res.getInt("puntosMax");
                    esAdmin = res.getBoolean("esAdmin");
                    config = res.getInt("codC");
                    Usuario x = new Usuario(nomUsu, pass,email, puntosMax, esAdmin, config);
                    lista.add(x);
                    ResultSet resPartida = database.executeQuery("SELECT * FROM Partida WHERE ID_Jugador='" + id + "'");

                    while (partida) {
                        try {
                            partida = resPartida.next();
                            if (partida) {
                                fecha = resPartida.getDate("fechaHora");
                                puntos = resPartida.getInt("puntuacion");
                                nivel = resPartida.getInt("nivel");
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

        Ranking.getInstance().resetearLista(lista);

    }

    public Usuario buscarUsuario(String pUsuario){
        return lista.buscarUsuario(pUsuario);
    }

    public String borrarUsuario(String usuario) {
        String error = "";

        // Borramos el usuario de la base de datos (todos sus datos!! -> CASCADE)
        GestorBD database = GestorBD.getInstance();
        ResultSet res = database.executeQuery("SELECT * FROM Jugador WHERE usuario='" + usuario + "'");
        boolean existeUsuario = true;

        try {
            existeUsuario = res.next();
        } catch (SQLException e) {e.printStackTrace();}

        if(!existeUsuario)
            return "El usuario introducido no existe";

        database.executeStatement("DELETE FROM Jugador WHERE usuario='" + usuario + "'");

        // TODO: Hay que borrar sus datos de los objetos cargados
        lista.eliminarJugador(usuario);

        // TODO: Hay que borrar sus partidas guardadas

        Sistema.getInstance().borrarSusPartidas(usuario);
        return error;
    }

    public JSONArray obtenerPuntuacionJug(Usuario pUsuario){
        return pUsuario.obtenerPuntuacionesMax();
    }


    public JSONArray obtenerMejoresPuntJug(int pNivel, Usuario pElUsuario){
        return pElUsuario.buscarMejoresPartidasJug(pNivel);
    }

}
