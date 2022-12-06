package com.visual;

import com.visual.RecursosVisuales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class PlantillaInterfaces extends JPanel {

    protected JPanel getMainPanel(String subtitle){
        // Contenido de la Interfaz -> main panel (subtitulo + contenido)
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        // Añadimos el panel del subtitulo en la parte superior
        main.add(getSubtitlePanel(subtitle), BorderLayout.NORTH);

        // añadimos el contenido al centro del panel principal
        main.add(getContentPanel(), BorderLayout.CENTER);

        return main;
    }

    protected JPanel getSubtitlePanel(String subtitle){
        // Creamos el panel del subtitulo (borderlayout + flowlayout por el boton de "volver")
        // Submain es el principal y le añadimos el boton y el flowlayout
        JPanel submain = new JPanel();
        submain.setLayout(new BorderLayout());

        JButton returnButton = new JButton("Volver");
        returnButton.addActionListener(mouseEventHandler());
        submain.add(returnButton, BorderLayout.WEST);

        JPanel subTitlePanel = new JPanel();
        subTitlePanel.setLayout(new FlowLayout());
        JLabel subTitle = new JLabel(subtitle);
        subTitle.setFont(RecursosVisuales.getInstance().subTitleFont);
        subTitlePanel.add(subTitle);

        submain.add(subTitlePanel, BorderLayout.CENTER);
        submain.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

        return submain;
    }

    protected abstract JPanel getContentPanel();
    protected abstract ActionListener mouseEventHandler();
}
