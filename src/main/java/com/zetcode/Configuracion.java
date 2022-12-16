package com.zetcode;

public class Configuracion {
    private int codigoC;
    private String color;
    private String sonido;
    private String ladrillo;

    public Configuracion(int pCodigoC,String pColor, String pSonido, String pLadrillo){
        codigoC = pCodigoC;
        color= pColor;
        sonido= pSonido;
        ladrillo = pLadrillo;
    }

    public void actualizarConfiguracion(String pColor, String pSonido, String pLadrillo){
        color= pColor;
        sonido= pSonido;
        ladrillo = pLadrillo;
    }

    public String getColor(){return color;}
    public String getSonido(){return sonido;}

    public String getLadrillo() { return ladrillo;}
}
