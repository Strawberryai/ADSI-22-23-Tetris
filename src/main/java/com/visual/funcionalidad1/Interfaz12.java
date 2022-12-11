package com.visual.funcionalidad1;

import com.visual.GestorPaneles;
import com.visual.PlantillaInterfaces;
import com.visual.RecursosVisuales;
import com.zetcode.Sistema;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Interfaz12 extends PlantillaInterfaces {
    /**
     * Interfaz de Borrado de usuarios.
     * Se presentan tres campos: un boton de volver, un
     * textInput para introducir el usuario a borrar y
     * un boton de confirmar el borrado.
     */

    private String usuario;
    private boolean esAdmin;
    private JTextField userInput;

    public Interfaz12(String pUsuario, boolean pEsAdmin){
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        usuario = pUsuario;
        esAdmin = pEsAdmin;

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Borrado de usuarios"), BorderLayout.CENTER);
    }

    @Override
    protected JPanel getContentPanel(){
        // Contenido principal de la vista
        // El contenido es un boxlayout con el textInput y el boton
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));

        JLabel userLabel = new JLabel("Usuario");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(userLabel);

        userInput = new JTextField();
        userInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        userInput.setMaximumSize(new Dimension(400, 30));
        content.add(userInput);

        JButton okButton = new JButton("Confirmar borrado");
        okButton.addActionListener(mouseEventHandler());
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(okButton);

        return content;
    }

    @Override
    protected ActionListener mouseEventHandler(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();

                if(o instanceof JButton){
                    JButton button = (JButton) o;

                    if(Objects.equals(button.getText(), "Volver")){
                        // Volver a la pagina principal
                        GestorPaneles.getInstance().bind(new Interfaz9(usuario, esAdmin));

                    }else if(Objects.equals(button.getText(), "Confirmar borrado")){
                        // Borrar usuario y volver a la pagina principal
                        String error = Sistema.getInstance().borrarUsuario(userInput.getText());

                        if(Objects.equals(error, "")){
                            // Usuario borrado, volvemos a la pagina principal
                            GestorPaneles.getInstance().bind(new Interfaz9(usuario, esAdmin));
                        }else{
                            // Mostramos la vista de error
                            GestorPaneles.getInstance().bind(new Interfaz13(usuario, esAdmin, error));
                        }
                    }
                }
            }
        };
    }

}
