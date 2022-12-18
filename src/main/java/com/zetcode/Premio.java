package com.zetcode;

import java.sql.Time;
import java.sql.Timestamp;

public class Premio {

    String descripcion;
    String usuario;
    int nivel;
    int recompensa; 
    Timestamp sqlTimestamp;




    public Premio(String pDescripcion, String pUsuario, int pNivel, int pRecompensa, Timestamp sqlTimestamp){
        this.descripcion = pDescripcion;
        this.usuario = pUsuario;
        this.nivel = pNivel;
        this.recompensa = pRecompensa;
        this.sqlTimestamp = sqlTimestamp;
    }

    public void imprimirPremio() {
        System.out.println(descripcion);
    }

    public String getDescripcion() {
        return descripcion;
    }
    public int getNivel() {
        return nivel;
    }
    public int getRecompensa() {
        return recompensa;
    }
    public String getUsuario() {
        return usuario;
    }
    public Timestamp getSqlTimestamp() {
        return sqlTimestamp;
    }
}
