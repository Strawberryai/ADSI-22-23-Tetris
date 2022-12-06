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

public class Interfaz4 extends PlantillaInterfaces {
    /**
     * Interfaz de Registro del sistema.
     * Se presentan cinco campos: un textarea para el username;
     * otro para el mail; otro para la contraseña; un boton de volver y otro para confirmar
     * el registro.
     */

    private JTextField userInput;
    private JTextField emailInput;
    private JPasswordField passInput;

    public Interfaz4(){
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Registro"), BorderLayout.CENTER);
    }

    @Override
    protected JPanel getContentPanel() {
        // contenido de la vista
        // Contenido principal de la vista
        // El contenido es un boxlayout con los textinputs, los labels y el boton confirmar (flowlayout para centrar)
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));

        JLabel userLabel = new JLabel("Usuario");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(userLabel);

        userInput = new JTextField();
        userInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        userInput.setMaximumSize(new Dimension(400, 30));
        content.add(userInput);

        JLabel mailLabel = new JLabel("E-mail");
        mailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(mailLabel);

        emailInput = new JTextField();
        emailInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailInput.setMaximumSize(new Dimension(400, 30));
        content.add(emailInput);

        JLabel passLabel = new JLabel("Contraseña");
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(passLabel);

        passInput = new JPasswordField();
        passInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        passInput.setMaximumSize(new Dimension(400, 30));
        content.add(passInput);

        JButton okButton = new JButton("Registrarse");
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

                    }else if(Objects.equals(button.getText(), "Registrarse")){
                        // Tomamos los datos de los campos y se los enviamos al sistema
                        String usuario = userInput.getText();
                        String mail = emailInput.getText();
                        String pass = passInput.getText();

                        String error = Sistema.getInstance().validarRegistro(usuario, mail, pass);

                        // Si los datos son correctos registramos el usuario y mostramos la ventana del log in.
                        if(error == null){
                            Sistema.getInstance().registrarUsuario(usuario, mail, pass);
                            GestorPaneles.getInstance().bind(new Interfaz2());
                        }else{
                            GestorPaneles.getInstance().bind(new Interfaz5(error));
                        }

                        // Si no son correctos mostramos una ventana con el error.
                    }
                }
            }
        };
    }
}
