package com.zetcode;

import java.sql.Timestamp;
import java.util.Date;

public class Partida {
        private Date fecha;

        private int nivel;
    public Partida(Date pFecha, int pNivel){
        fecha=pFecha;
        nivel=pNivel;
    }
}
