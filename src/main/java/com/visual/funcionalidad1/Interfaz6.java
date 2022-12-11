package com.visual.funcionalidad1;

import com.visual.GestorPaneles;
import com.visual.PlantillaInterfaces;
import com.visual.RecursosVisuales;
import com.zetcode.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Interfaz6 extends PlantillaInterfaces {
    /**
     * Interfaz de Recuperacion de contraseña
     * Se presenta tres campos: un botón de volver; un textinput y un boton de recuperar.
     * Al pulsar "volver" se vuelve a la interfaz 1 (pagina principal).
     */

    private JTextField userInput;

    public Interfaz6(){
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Recuperar contraseña"), BorderLayout.CENTER);
    }

    @Override
    protected JPanel getContentPanel() {
        // Contenido principal de la vista
        // El contenido es un boxlayout con un label, un textinput y un boton.
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));

        JLabel userLabel = new JLabel("Usuario");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(userLabel);

        userInput = new JTextField();
        userInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        userInput.setMaximumSize(new Dimension(400, 30));
        content.add(userInput);

        JButton okButton = new JButton("Recuperar");
        okButton.addActionListener(mouseEventHandler());
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(okButton);

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
                        GestorPaneles.getInstance().bind(new Interfaz1());

                    }else if(Objects.equals(button.getText(), "Recuperar")){
                        // Tomamos los datos de los campos y se los enviamos al sistema
                        String usuario = userInput.getText();
                        boolean usuarioValido = Sistema.getInstance().recuperarContrasena(usuario);

                        if(usuarioValido){
                            // Mostramos una vista informando que se ha enviado un email de recuperacion
                            GestorPaneles.getInstance().bind(new Interfaz7());
                        }else{
                            // Mostramos una vista de error avisando de que el usuario no es valido
                            GestorPaneles.getInstance().bind(new Interfaz8());
                        }
                    }
                }
            }
        };
    }
}
