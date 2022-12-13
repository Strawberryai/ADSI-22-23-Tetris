package com.zetcode;

public class Configuracion {
    private String color;
    private String sonido;
    private String ladrillo;

    public Configuracion(String pColor, String pSonido, String pLadrillo){
        color= pColor;
        sonido= pSonido;
        ladrillo = pLadrillo;
    }

    public void actualizarConfiguracion(String pColor, String pSonido, String pLadrillo){
        color= pColor;
        sonido= pSonido;
        ladrillo = pLadrillo;
    }
}
