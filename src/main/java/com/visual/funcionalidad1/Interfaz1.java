package com.visual.funcionalidad1;

import com.visual.GestorPaneles;
import com.visual.RecursosVisuales;
import com.visual.funcionalidad3.Personalizar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz1 extends JPanel {
    /**
     * Interfaz inicial: Usuario no loggeado.
     * Se presentan las opciones de loggearse, registrarse y de
     * recuperacion de contraseña.
     */

    public Interfaz1(){
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

        // Creamos el panel del subtitulo (flowlayout)
        JPanel subTitlePanel = new JPanel();
        subTitlePanel.setLayout(new FlowLayout());
        JLabel subTitle = new JLabel("Página Principal");
        subTitle.setFont(RecursosVisuales.getInstance().subTitleFont);
        subTitlePanel.add(subTitle);

        // Añadimos el panel del subtitulo en la parte superior
        main.add(subTitlePanel, BorderLayout.NORTH);

        // [No loggeado] -> El contenido es un flowlayout con los botones de login...
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());

        // boton de login
        JButton login = new JButton("Log in");
        login.addActionListener(mouseEventHandler());
        content.add(login);

        // boton de registro
        JButton register = new JButton("Register");
        register.addActionListener(mouseEventHandler());
        content.add(register);

        // boton de recuperacion de contraseña
        JButton rec = new JButton("Recuperar Contraseña");
        rec.addActionListener(mouseEventHandler());
        content.add(rec);

        //boto de personalizacion de la configuracion
        JButton pers = new JButton("Personalizar Configuracion");
        pers.addActionListener(mouseEventHandler());
        content.add(pers);

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

                    if(button.getText() == "Log in"){
                        System.out.println("Log in");
                        GestorPaneles.getInstance().bind(new Interfaz2());

                    }else if(button.getText() == "Register"){
                        System.out.println("Register");

                    }else if(button.getText() == "Recuperar Contraseña"){
                        System.out.println("Recuperar Contraseña");

                    }else if(button.getText() == "Personalizar Configuracion"){
                        System.out.println("Personalizar Configuracion");
                        GestorPaneles.getInstance().bind(new Personalizar());

                    }
                }
            }
        };
    }



}
