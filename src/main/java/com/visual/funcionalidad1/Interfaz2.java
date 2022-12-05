package com.visual.funcionalidad1;

import com.visual.GestorPaneles;
import com.visual.RecursosVisuales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz2 extends JPanel {
    /**
     * Interfaz de Log in del sistema.
     * Se presentan cuatr campos: un textarea para el username;
     * otro para la contraseña; un boton de volver y otro para confirmar
     * el inicio de sesión.
     */

    public Interfaz2(){
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getContentPanel(), BorderLayout.CENTER);
    }

    private JPanel getContentPanel(){
        // Contenido de la Interfaz -> main panel (subtitulo + contenido)
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        // Creamos el panel del subtitulo (borderlayout + flowlayout por el boton de "volver")
        // Submain es el principal y le añadimos el boton y el flowlayout
        JPanel submain = new JPanel();
        submain.setLayout(new BorderLayout());

        JButton returnButton = new JButton("Volver");
        returnButton.addActionListener(mouseEventHandler());
        submain.add(returnButton, BorderLayout.WEST);

        JPanel subTitlePanel = new JPanel();
        subTitlePanel.setLayout(new FlowLayout());
        JLabel subTitle = new JLabel("Log in");
        subTitle.setFont(RecursosVisuales.getInstance().subTitleFont);
        subTitlePanel.add(subTitle);
        submain.add(subTitlePanel, BorderLayout.CENTER);

        // Añadimos el panel del subtitulo en la parte superior
        main.add(submain, BorderLayout.NORTH);

        // [No loggeado] -> El contenido es un flowlayout con los botones de login...
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());

        // añadimos el contenido al centro del panel principal
        main.add(content, BorderLayout.CENTER);

        return main;
    }

    private ActionListener mouseEventHandler(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();

                if(o instanceof JButton){
                    JButton button = (JButton) o;

                    if(button.getText() == "Volver"){
                        System.out.println("volviendo a la página principal");
                        GestorPaneles.getInstance().bind(new Interfaz1());

                    }
                }
            }
        };
    }
}
