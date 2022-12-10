package com.visual.funcionalidad4;
import com.visual.GestorPaneles;
import com.visual.RecursosVisuales;
import com.visual.funcionalidad1.Interfaz2;
import com.visual.funcionalidad1.Interfaz1;
import com.zetcode.Board;
import com.zetcode.Sistema;
import com.zetcode.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InterfazGuardar extends JPanel {
    private String usuario;
    public InterfazGuardar(String pUsuario) {
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getContentPanel(), BorderLayout.CENTER);
        this.usuario=pUsuario;

    }

    private JPanel getContentPanel(){
        // Contenido de la Interfaz -> main panel (subtitulo + contenido)
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        // Creamos el panel del subtitulo (flowlayout)
        JPanel subTitlePanel = new JPanel();
        subTitlePanel.setLayout(new FlowLayout());
        JLabel subTitle = new JLabel("GuardarPrueba");
        subTitle.setFont(RecursosVisuales.getInstance().subTitleFont);
        subTitlePanel.add(subTitle);

        // Añadimos el panel del subtitulo en la parte superior
        main.add(subTitlePanel, BorderLayout.NORTH);

        // [No loggeado] -> El contenido es un flowlayout con los botones de login...
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());

        // boton de login
        JButton Guardar = new JButton("Guardar");
        Guardar.addActionListener(mouseEventHandler());
        content.add(Guardar);
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

                    if(button.getText() == "Guardar") {
                        System.out.println("Guardar");
                        GestorPaneles.getInstance().bind(new Interfaz1());
                        try {
                            System.out.println(usuario);
                            Sistema.getInstance().guardarPartida(usuario);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        };
    }



}