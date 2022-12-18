package eus.ehu.lsi.adsi;

import com.zetcode.*;
import junit.framework.TestCase;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PersonalizarTest {
    Usuario usu1;
    Usuario usu2;
    Usuario usu3;
    Usuario usu4;
    Configuracion conf1;
    Configuracion conf2;
    String color;
    String sonido;
    String ladrillo;
    
   /* public PersonalizarTest() {
        usu1 = new Usuario("U1", "123", "usuario1@gmail.com", 1,false, 1 );
        usu2 = new Usuario("U2", "123", "usuario2@gmail.com", 1,false, 1 );
        usu3 = new Usuario("U3", "123", "usuario3@gmail.com", 1,false, 1 );
        usu4 = new Usuario("U4", "123", "usuario4@gmail.com", 1,false, 1 );
        conf1= new Configuracion(1, "rojo","Epico","verde");
        conf2= new Configuracion(2, "azul","Intriga","amarillo");
        GestorUsuarios.getInstance().anadirUsuario(usu1);
        GestorUsuarios.getInstance().anadirUsuario(usu2);
        GestorUsuarios.getInstance().anadirUsuario(usu3);
        GestorUsuarios.getInstance().anadirUsuario(usu4);
        usu1.actualizarConfiguracion(conf1.getColor(),conf1.getSonido(),conf1.getLadrillo());
        usu1.getConfig();
    }*/

    @Before
    public void setUp(){
        usu1 = new Usuario("U1", "123", "usuario1@gmail.com", 1,false, 1 );
        usu2 = new Usuario("U2", "123", "usuario2@gmail.com", 1,false, 1 );
        usu3 = new Usuario("U3", "123", "usuario3@gmail.com", 1,false, 1 );
        usu4 = new Usuario("U4", "123", "usuario4@gmail.com", 1,false, 1 );

        conf1= new Configuracion(1, "rojo","Epico","verde");
        conf2= new Configuracion(2, "azul","Intriga","amarillo");

        GestorUsuarios.getInstance().anadirUsuario(usu1);
        GestorUsuarios.getInstance().anadirUsuario(usu2);
        GestorUsuarios.getInstance().anadirUsuario(usu3);
        GestorUsuarios.getInstance().anadirUsuario(usu4);

        usu1.actualizarConfiguracion(conf1.getColor(),conf1.getSonido(),conf1.getLadrillo());
        usu1.getConfig();
    }
    @Test
    public void test1(){// el usuario pulsa actualizar sin seleccionar ninguna elección
        color= usu1.getConfig().getColor();
        sonido= usu1.getConfig().getSonido();
        ladrillo= usu1.getConfig().getLadrillo();
        String usu= usu1.getNombre();
        assertTrue(ladrillo.equals("verde"));
        color= "predeterminado";
        sonido= "predeterminado";
        ladrillo= "predeterminado";
        Sistema.getInstance().actualizarConfiguracion(usu,color,sonido,ladrillo);
        ladrillo=usu1.getConfig().getLadrillo();
        System.out.println(ladrillo);
        assertTrue(ladrillo.equals("predeterminado"));

        usu1.actualizarConfiguracion(conf1.getColor(),conf1.getSonido(),conf1.getLadrillo());
    }
    @Test
    public void test2(){//El usuario selecciona un color y cambia el color
        color= usu1.getConfig().getColor();
        sonido= usu1.getConfig().getSonido();
        ladrillo= usu1.getConfig().getLadrillo();
        String usu= usu1.getNombre();
        color=usu1.getConfig().getColor();
        assertTrue(color.equals("rojo"));
        color= "negro";
        System.out.println(color);
        Sistema.getInstance().actualizarConfiguracion(usu,color,sonido,ladrillo);
        color=usu1.getConfig().getColor();
        System.out.println(color);
        assertTrue(color.equals("negro"));

        usu1.actualizarConfiguracion(conf1.getColor(),conf1.getSonido(),conf1.getLadrillo());
    }
    @Test
    public void test2A(){//El usuario selecciona un ladrillo y cambia el ladrillo
        color= usu1.getConfig().getColor();
        sonido= usu1.getConfig().getSonido();
        ladrillo= usu1.getConfig().getLadrillo();
        String usu= usu1.getNombre();
        assertTrue(ladrillo.equals("verde"));
        ladrillo= "azul";
        Sistema.getInstance().actualizarConfiguracion(usu,color,sonido,ladrillo);
        ladrillo=usu1.getConfig().getLadrillo();
        assertTrue(ladrillo.equals("azul"));

        usu1.actualizarConfiguracion(conf1.getColor(),conf1.getSonido(),conf1.getLadrillo());
    }
    @Test
    public void test2B(){//El usuario selecciona un sonido y cambia el sonido
        color= usu1.getConfig().getColor();
        sonido= usu1.getConfig().getSonido();
        ladrillo= usu1.getConfig().getLadrillo();
        System.out.println(sonido);
        String usu= usu1.getNombre();
        assertTrue(sonido.equals("Epico"));
        sonido= "Intriga";
        Sistema.getInstance().actualizarConfiguracion(usu,color,sonido,ladrillo);
        sonido=usu1.getConfig().getSonido();
        System.out.println(sonido);
        assertTrue(sonido.equals("Intriga"));

        usu1.actualizarConfiguracion(conf1.getColor(),conf1.getSonido(),conf1.getLadrillo());
    }
    @Test
    public void test3(){
        color= usu1.getConfig().getColor();
        sonido= usu1.getConfig().getSonido();
        ladrillo= usu1.getConfig().getLadrillo();
        String usu= usu1.getNombre();
        System.out.println(ladrillo+","+sonido+","+color);
        assertTrue(sonido.equals("Epico"));
        assertTrue(ladrillo.equals("verde"));
        sonido = "Intriga";
        ladrillo = "azul";
        Sistema.getInstance().actualizarConfiguracion(usu,color,sonido,ladrillo);
        sonido= usu1.getConfig().getSonido();
        ladrillo= usu1.getConfig().getLadrillo();
        System.out.println(sonido);
        assertTrue(sonido.equals("Intriga"));
        assertTrue(ladrillo.equals("azul"));

        usu1.actualizarConfiguracion(conf1.getColor(),conf1.getSonido(),conf1.getLadrillo());
    }
    @Test
    public void test4(){
        color= usu1.getConfig().getColor();
        sonido= usu1.getConfig().getSonido();
        ladrillo= usu1.getConfig().getLadrillo();
        String usu= usu1.getNombre();
        assertTrue(sonido.equals("Epico"));
        assertTrue(ladrillo.equals("verde"));
        assertTrue(color.equals("rojo"));
        sonido= "Intriga";
        ladrillo= "azul";
        color= "negro";
        Sistema.getInstance().actualizarConfiguracion(usu,color,sonido,ladrillo);
        sonido= usu1.getConfig().getSonido();
        ladrillo= usu1.getConfig().getLadrillo();
        color= usu1.getConfig().getColor();
        assertTrue(sonido.equals("Intriga"));
        assertTrue(ladrillo.equals("azul"));
        assertTrue(color.equals("negro"));

        usu1.actualizarConfiguracion(conf1.getColor(),conf1.getSonido(),conf1.getLadrillo());
    }
}
