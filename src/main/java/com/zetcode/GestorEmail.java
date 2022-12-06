package com.zetcode;

public class GestorEmail {
    private static GestorEmail instance;

    private GestorEmail(){}

    public static GestorEmail getInstance(){
        if(GestorEmail.instance == null) GestorEmail.instance = new GestorEmail();
        return GestorEmail.instance;
    }

    public void enviarEmailRecuperacion(String email, String pass){
        // TODO: Implementar esto
        System.out.println("Email enviado a " + email + " -> contraseña: " + pass);
    }
}
