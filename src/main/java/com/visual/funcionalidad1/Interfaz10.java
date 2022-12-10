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

public class Interfaz10 extends PlantillaInterfaces {
    /**
     * Interfaz de Cambio de contraseña.
     * Se presentan cuatro campos: dos inputText para introducir la nueva contraseña;
     * un boton de volver y un boton de confirmar.
     */

    private String usuario;
    private JPasswordField passInput1;
    private JPasswordField passInput2;

    public Interfaz10(String pUsuario){
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        usuario = pUsuario;

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Cambiar contraseña"), BorderLayout.CENTER);
    }

    @Override
    protected JPanel getContentPanel() {
        // contenido de la vista
        // Contenido principal de la vista
        // El contenido es un boxlayout con los textinputs, los labels y el boton confirmar (flowlayout para centrar)
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));

        JLabel userLabel = new JLabel("Nueva contraseña");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(userLabel);

        passInput1 = new JPasswordField();
        passInput1.setAlignmentX(Component.CENTER_ALIGNMENT);
        passInput1.setMaximumSize(new Dimension(400, 30));
        content.add(passInput1);

        JLabel mailLabel = new JLabel("Repetir contraseña");
        mailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(mailLabel);

        passInput2 = new JPasswordField();
        passInput2.setAlignmentX(Component.CENTER_ALIGNMENT);
        passInput2.setMaximumSize(new Dimension(400, 30));
        content.add(passInput2);

        JButton okButton = new JButton("Confirmar");
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
                        GestorPaneles.getInstance().bind(new Interfaz9(usuario));

                    }else if(Objects.equals(button.getText(), "Confirmar")){
                        // Tomamos los datos de los campos y se los enviamos al sistema
                        String pass1 = passInput1.getText();
                        String pass2 = passInput1.getText();

                        String error = Sistema.getInstance().cambiarContrasena(usuario, pass1, pass2);

                        if(error == null){
                            // Contraseña cambiada con éxito -> volvemos a la pagina principal
                            GestorPaneles.getInstance().bind(new Interfaz9(usuario));
                        }else{
                            // error -> mostramos vista de error
                            GestorPaneles.getInstance().bind(new Interfaz11(usuario, error));
                        }

                    }
                }
            }
        };
    }
}
