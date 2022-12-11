package com.visual.funcionalidad4;
import com.visual.GestorPaneles;
import com.visual.PlantillaInterfaces;
import com.visual.RecursosVisuales;
import com.visual.funcionalidad1.Interfaz2;
import com.visual.funcionalidad1.Interfaz1;
import com.visual.funcionalidad1.Interfaz9;
import com.zetcode.Board;
import com.zetcode.Sistema;
import com.zetcode.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InterfazError extends PlantillaInterfaces {
    private String usuario;
    private Boolean esAdmin;
    public InterfazError(String pUsuario,boolean esAdmin) {
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getContentPanel(), BorderLayout.CENTER);
        this.usuario=pUsuario;
        this.esAdmin=esAdmin;

    }

    protected JPanel getContentPanel(){
        // Contenido de la Interfaz -> main panel (subtitulo + contenido)
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        // Creamos el panel del subtitulo (flowlayout)
        JPanel subTitlePanel = new JPanel();
        subTitlePanel.setLayout(new FlowLayout());
        // Añadimos el panel del subtitulo en la parte superior
        JLabel subTitle = new JLabel("Error");
        subTitle.setFont(RecursosVisuales.getInstance().subTitleFont);
        subTitlePanel.add(subTitle);
        main.add(subTitlePanel, BorderLayout.NORTH);
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        // [No loggeado] -> El contenido es un flowlayout con los botones de login...
        JLabel ERROR= new JLabel("ERROR:La partida ha sido eliminada o modificada. Compruebe si se ha corrompido o modificado");
        content.add(ERROR);
        JButton volver = new JButton("Volver al menu");
        volver.addActionListener(mouseEventHandler());
        content.add(volver);
        main.add(content, BorderLayout.CENTER);



        return main;
    }

    protected ActionListener mouseEventHandler(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();

                if(o instanceof JButton){
                    JButton button = (JButton) o;

                    if(button.getText() == "Volver al menu") {
                        System.out.println("Volver");
                        GestorPaneles.getInstance().bind(new Interfaz9(usuario,esAdmin));

                    }
                }
            }
        };
    }



}