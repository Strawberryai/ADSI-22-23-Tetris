package eus.ehu.lsi.adsi;
import com.zetcode.ListaUsuarios;
import com.zetcode.Ranking;
import com.zetcode.Usuario;
import com.zetcode.Sistema;
import static org.junit.Assert.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
public class RankingTest {
    Usuario x,y,z;
    ListaUsuarios lista;
    @Before
    public void setUp(){
        x=new Usuario("Mario","hola","hola@gmail.com",2,false,0);
        y=new Usuario("Manuel","hola","hola@gmail.com",10,false,0);
        z=new Usuario("Dario","hola","hola@gmail.com",5,false,0);
        lista=new ListaUsuarios();
        lista.add(x);
        lista.add(y);
        lista.add(z);
    }
    @Test
    public void buscarMejoresJugadores(){
        Ranking.getInstance().resetearLista(lista);
        JSONArray arr=Ranking.getInstance().obtenerPuntuacionesMax();
        JSONObject js=arr.getJSONObject(arr.length()-1);
        assertTrue(js.getString("usuario").equals("Manuel"));

    }
}
