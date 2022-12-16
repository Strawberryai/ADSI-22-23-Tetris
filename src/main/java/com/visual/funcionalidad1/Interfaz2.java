package com.visual.funcionalidad1;

import com.visual.GestorPaneles;
import com.visual.PlantillaInterfaces;
import com.visual.RecursosVisuales;
import com.visual.funcionalidad3.Sonido;
import com.zetcode.Sistema;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Interfaz2 extends PlantillaInterfaces {
    /**
     * Interfaz de Log in del sistema.
     * Se presentan cuatro campos: un textarea para el username;
     * otro para la contraseña; un boton de volver y otro para confirmar
     * el inicio de sesión.
     */

    private JTextField userInput;
    private JPasswordField passInput;

    public Interfaz2(){
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Log in"), BorderLayout.CENTER);
    }

    @Override
    protected JPanel getContentPanel(){
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

        JLabel passLabel = new JLabel("Contraseña");
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(passLabel);

        passInput = new JPasswordField();
        passInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        passInput.setMaximumSize(new Dimension(400, 30));
        content.add(passInput);

        JButton okButton = new JButton("Ok");
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
                        GestorPaneles.getInstance().bind(new Interfaz1());

                    }else if(Objects.equals(button.getText(), "Ok")){
                        // Tomamos los datos de los campos y se los enviamos al sistema
                        String usuario = userInput.getText();
                        String pass = passInput.getText();

                        JSONObject res = Sistema.getInstance().comprobarUsuario(usuario, pass);

                        // Si es un usuario válido entramos en el sistema. Si no se muestra un error
                        if(res.getBoolean("identificado")){
                            //GestorPaneles.getInstance().bind(new Interfaz9(usuario));
                            Sonido.getMiSonido().ReproducirSonido("/audios/predeterminada.wav");
                            Sistema.getInstance().cargarDatosUsuarios();
                            GestorPaneles.getInstance().bind(new Interfaz9(usuario, res.getBoolean("esAdmin")));
                        }else{
                            GestorPaneles.getInstance().bind(new Interfaz3());
                        }

                    }
                }
            }
        };
    }
}
