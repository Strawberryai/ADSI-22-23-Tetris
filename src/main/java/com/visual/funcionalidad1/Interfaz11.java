package com.visual.funcionalidad1;

import com.visual.GestorPaneles;
import com.visual.PlantillaInterfaces;
import com.visual.RecursosVisuales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Interfaz11 extends PlantillaInterfaces {
    /**
     * Interfaz de Error de Cambio de contraseña.
     * Se presenta un único campo: un botón de volver.
     * Al pulsarlo se vuelve a la interfaz 9 (pagina principal loggeado).
     */

    private String usuario;
    private boolean esAdmin;
    private String error;

    public Interfaz11(String pUsuario, boolean pEsAdmin, String pError){
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        usuario = pUsuario;
        esAdmin = pEsAdmin;
        error = pError;

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Error"), BorderLayout.CENTER);
    }

    @Override
    protected JPanel getContentPanel() {
        // Contenido de la vista
        JPanel content = new JPanel();
        content.add(new JLabel(error));

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
                        GestorPaneles.getInstance().bind(new Interfaz9(usuario, esAdmin));

                    }
                }
            }
        };
    }
}
