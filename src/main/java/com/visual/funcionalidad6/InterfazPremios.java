package com.visual.funcionalidad6;

import com.visual.GestorPaneles;
import com.visual.PlantillaInterfaces;
import com.visual.RecursosVisuales;
import com.visual.funcionalidad1.Interfaz1;
import com.visual.funcionalidad1.Interfaz9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class InterfazPremios extends PlantillaInterfaces {
    /*Interfaz para representar la obetnción de un premio*/

    String usuario;
    boolean esAdmin;

    public InterfazPremios(String pUsuario, boolean pEsAdmin) {
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        usuario = pUsuario;
        esAdmin = pEsAdmin;

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Premio de prendida absoluta"), BorderLayout.CENTER);
    }

    @Override
    protected JPanel getSubtitlePanel(String subtitle){
        // Creamos el panel del subtitulo (borderlayout + flowlayout por el boton de "volver")
        // Submain es el principal y le añadimos el boton y el flowlayout
        JPanel submain = new JPanel();
        submain.setLayout(new BorderLayout());

        JButton returnButton = new JButton("Aceptar");
        returnButton.addActionListener(mouseEventHandler());
        submain.add(returnButton, BorderLayout.EAST);

        JPanel subTitlePanel = new JPanel();
        subTitlePanel.setLayout(new FlowLayout());
        JLabel subTitle = new JLabel(subtitle);
        subTitle.setFont(RecursosVisuales.getInstance().subTitleFont);
        subTitlePanel.add(subTitle);

        submain.add(subTitlePanel, BorderLayout.CENTER);
        submain.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

        return submain;
    }

    @Override
    protected JPanel getContentPanel() {

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        main.add(new JLabel("Premio maquineo"));
        

        return main;
    }

    @Override
    protected ActionListener mouseEventHandler() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();

                if(o instanceof JButton){
                    JButton button = (JButton) o;

                    if(Objects.equals(button.getText(), "Aceptar")){
                        // Volver a la pagina principal
                        GestorPaneles.getInstance().bind(new Interfaz9(usuario, esAdmin));
                    }
                }
            }
        };
    }
}
