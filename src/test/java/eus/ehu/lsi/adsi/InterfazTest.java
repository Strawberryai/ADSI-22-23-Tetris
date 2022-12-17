package eus.ehu.lsi.adsi;

import com.visual.GestorPaneles;
import com.visual.funcionalidad1.Interfaz10;
import com.visual.funcionalidad1.Interfaz1;
import com.visual.funcionalidad2.InterfazNivel;

public class InterfazTest {

    public static void main(String arg[]){
        GestorPaneles.getInstance().bind(new InterfazNivel("Manuel", false));
    }
}
