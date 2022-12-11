package com.visual.funcionalidad1;

import com.visual.GestorPaneles;
import com.visual.PlantillaInterfaces;
import com.visual.RecursosVisuales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Interfaz7 extends PlantillaInterfaces {
    /**
     * Interfaz de Informacion de recuperacion de contraseña.
     * Se presenta un único campo: un botón de volver.
     * Al pulsarlo se vuelve a la interfaz 6.
     */

    public Interfaz7(){
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Info"), BorderLayout.CENTER);
    }

    @Override
    protected JPanel getContentPanel() {
        // Contenido de la vista
        JPanel content = new JPanel();
        content.add(new JLabel("Se ha enviado un correo de recuperación a email del usuario."));
        content.add(new JLabel("(Por simplicidad, hemos decidico imprimir por consola la contraseña y no saturar el correo)."));

        return content;
    }

    @Override
    protected ActionListener mouseEventHandler() {
        // Control de los botones
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();

                if(o instanceof JButton){
                    JButton button = (JButton) o;

                    if(Objects.equals(button.getText(), "Volver")){
                        // Volver a la pagina principal
                        GestorPaneles.getInstance().bind(new Interfaz6());

                    }
                }
            }
        };
    }
}
