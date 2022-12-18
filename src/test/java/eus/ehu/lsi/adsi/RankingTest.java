package eus.ehu.lsi.adsi;
import com.zetcode.*;

import static org.junit.Assert.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

public class RankingTest {
    Usuario x,y,z;
    ListaUsuarios lista;
    Partida pA,pB,pC;

    Puntuacion t1,t2,t3;
    @Before
    public void setUp(){
        Date fecha= new Date();
        x=new Usuario("Mario","hola","hola@gmail.com",2,false,0);
        y=new Usuario("Manuel","hola","hola@gmail.com",10,false,0);
        z=new Usuario("Dario","hola","hola@gmail.com",5,false,0);
        pA=new Partida(fecha,2);
        t1=new Puntuacion(15,pA);
        x.anadirListaPuntuacion(t1);


        lista=new ListaUsuarios();
        lista.add(x);
        lista.add(y);
        lista.add(z);
    }
    @Test
    public void buscarMejoresJugadores(){
        Date fecha=new Date();
        Ranking.getInstance().resetearLista(lista);
        JSONArray arr=Ranking.getInstance().obtenerPuntuacionesMax();
        JSONObject js=arr.getJSONObject(arr.length()-1);
        assertTrue(js.getString("usuario").equals("Manuel"));
        pC=new Partida(fecha,3);
        t3=new Puntuacion(0,pC);
        z.anadirListaPuntuacion(t3);
        arr=Ranking.getInstance().obtenerPuntuacionesMax();
        js=arr.getJSONObject(arr.length()-1);
        assertFalse(js.getString("usuario").equals("Dario"));
    }
    @Test
    public void buscarMejoresJugadoresNivel(){

        Ranking.getInstance().resetearLista(lista);
        JSONArray arr=Ranking.getInstance().buscarMejoresJugadores(2);
        JSONObject ob=arr.getJSONObject(arr.length()-1);
        assertTrue(ob.getString("usuario").equals("Mario"));

        Date fecha=new Date();
        pB=new Partida(fecha,2);
        t2=new Puntuacion(20,pB);
        y.anadirListaPuntuacion(t2);

        arr=Ranking.getInstance().buscarMejoresJugadores(2);
        ob=arr.getJSONObject(arr.length()-1);
        assertFalse(ob.getString("usuario").equals("Mario"));


        pC=new Partida(fecha,2);
        t3=new Puntuacion(5,pC);
        z.anadirListaPuntuacion(t3);
        arr=Ranking.getInstance().buscarMejoresJugadores(2);
        ob=arr.getJSONObject(2);
        assertFalse(ob.getString("usuario").equals("Dario"));

        pC=new Partida(fecha,2);
        t3=new Puntuacion(12,pC);
        z.anadirListaPuntuacion(t3);

        arr=Ranking.getInstance().buscarMejoresJugadores(2);
        ob=arr.getJSONObject(1);
        assertTrue(ob.getString("usuario").equals("Dario"));
    }

    @Test
    public void prueba(){

    }

}
