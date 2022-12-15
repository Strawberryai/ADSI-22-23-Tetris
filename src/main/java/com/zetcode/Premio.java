package com.zetcode;

public class Premio {

    String descripcion;

    public Premio(String pDescripcion){
        this.descripcion = pDescripcion;
    }

    public void imprimirPremio() {
        System.out.println(descripcion);
    }
}
