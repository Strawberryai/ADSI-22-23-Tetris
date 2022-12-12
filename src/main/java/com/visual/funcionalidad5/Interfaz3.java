package com.visual.funcionalidad5;


import com.visual.GestorPaneles;
import com.visual.PlantillaInterfaces;
import com.visual.RecursosVisuales;
import com.visual.funcionalidad1.Interfaz4;
import com.visual.funcionalidad1.Interfaz6;
import com.zetcode.Sistema;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Interfaz3 extends PlantillaInterfaces {
    /**
     * Interfaz ranking global: Usuario loggeado.
     * Se presenta el ranking global tanto personal como de todos los usuarios,
     * se da la opción de ver el ranking por niveles.
     */

    private String usuario;
    private int nivel;

    public Interfaz3(String pUsuario, int pNivel) {
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        usuario = pUsuario;
        nivel=pNivel;
        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Ranking Global"), BorderLayout.CENTER);
    }

    @Override
    protected JPanel getSubtitlePanel(String subtitle) {
        // Creamos el panel del subtitulo (flowlayout)
        JPanel subTitlePanel = new JPanel();
        subTitlePanel.setLayout(new FlowLayout());
        JLabel subTitle = new JLabel(subtitle);
        subTitle.setFont(RecursosVisuales.getInstance().subTitleFont);

        subTitlePanel.add(subTitle);
        subTitlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

        return subTitlePanel;
    }

    @Override
    protected JPanel getContentPanel() {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        JPanel botones =new JPanel();
        botones.setLayout(new FlowLayout());
        JPanel central = new JPanel();
        central.setLayout(new GridLayout(1, 2));
        content.add(central, BorderLayout.CENTER);
        content.add(botones,BorderLayout.SOUTH);
        // boton de Visualizar por niveles
        JButton verGlobal = new JButton("Visualizar Global");
        verGlobal.addActionListener(mouseEventHandler());
        content.add(verGlobal, BorderLayout.NORTH);
/*
        DefaultListModel<String> lista1 = new DefaultListModel<>();
        JSONObject array = Sistema.getInstance().obtenerPuntuaciones(nivel,usuario);
        JSONArray global = (JSONArray) array.get("global");
        JSONArray personal = (JSONArray) array.get("personal");
        lista1.addElement("Ranking Global");
        for (int i = 0; i < global.length(); i++) {
            lista1.addElement(global.getString(i) + global.getInt(i));
        }

        JList<String> listaGl = new JList<>(lista1);
        listaGl.setBounds(100, 100, 200, 100);

        DefaultListModel<String> lista2 = new DefaultListModel<>();
        lista2.addElement("Ranking Personal");
        for (int i = 0; i < personal.length(); i++) {
            lista2.addElement(personal.getString(i) + personal.getInt(i));
        }
        JList<String> listaPers = new JList<>(lista2);
        listaPers.setBounds(100, 100, 200, 100);
        central.add(listaGl);
        central.add(listaPers);
        */
        if(nivel==1){
            JButton nivel2=new JButton("Nivel 2");
            nivel2.addActionListener(mouseEventHandler());
            botones.add(nivel2);
            JButton nivel3=new JButton("Nivel 3");
            nivel3.addActionListener(mouseEventHandler());
            botones.add(nivel3);
        }else if(nivel ==2){
            JButton nivel1=new JButton("Nivel 1");
            nivel1.addActionListener(mouseEventHandler());
            botones.add(nivel1);
            JButton nivel3=new JButton("Nivel 3");
            nivel3.addActionListener(mouseEventHandler());
            botones.add(nivel3);
        }else{
            JButton nivel1=new JButton("Nivel 1");
            nivel1.addActionListener(mouseEventHandler());
            botones.add(nivel1);
            JButton nivel2=new JButton("Nivel 2");
            nivel2.addActionListener(mouseEventHandler());
            botones.add(nivel2);
        }


        return content;
    }

    @Override
    protected ActionListener mouseEventHandler() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();

                if (o instanceof JButton) {
                    JButton button = (JButton) o;

                    if (Objects.equals(button.getText(), "Visualizar Global")) {
                        // Abrimos la vista de log in
                        GestorPaneles.getInstance().bind(new com.visual.funcionalidad5.Interfaz2(usuario));
                    }else if(Objects.equals(button.getText(), "Nivel 1")) {
                        GestorPaneles.getInstance().bind(new com.visual.funcionalidad5.Interfaz3(usuario, 1));
                    }else if(Objects.equals(button.getText(), "Nivel 2")) {
                        GestorPaneles.getInstance().bind(new com.visual.funcionalidad5.Interfaz3(usuario, 2));
                    }else if(Objects.equals(button.getText(), "Nivel 3")) {
                        GestorPaneles.getInstance().bind(new com.visual.funcionalidad5.Interfaz3(usuario, 3));
                    }
                }
            }
        };


    }
}