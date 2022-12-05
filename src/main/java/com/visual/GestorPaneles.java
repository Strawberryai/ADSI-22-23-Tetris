package com.visual;

import javax.swing.*;
import java.awt.*;

public class GestorPaneles extends JFrame {
    /**
     * Esta clase es la ventana que se encargará de mostrar las
     * distintas vistas. Para cambiar de vista se usa el método bind(panel).
     * Ejemplo:
     *          GestorPaneles.getInstance().bind(new Interfaz1());
     */

    private static GestorPaneles miGestor;

    private JPanel panelActual;

    private GestorPaneles(){
        super("Tetris ADSI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 900, 700);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setVisible(true);
    }

    public static GestorPaneles getInstance(){
        if(GestorPaneles.miGestor == null) GestorPaneles.miGestor = new GestorPaneles();
        return GestorPaneles.miGestor;
    }

    public void bind(JPanel panel){
        // Eliminamos el panel anterior
        if(this.panelActual != null){
            remove(this.panelActual);
        }

        // Actualizamos con el nuevo panel
        this.panelActual = panel;
        add(this.panelActual);
        setVisible(true);
    }
}
