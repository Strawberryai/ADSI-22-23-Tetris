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

public class Interfaz2 extends PlantillaInterfaces {
    /**
     * Interfaz ranking global: Usuario loggeado.
     * Se presenta el ranking global tanto personal como de todos los usuarios,
     * se da la opción de ver el ranking por niveles.
     */

    private String usuario;

    public Interfaz2(String pUsuario) {
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        usuario = pUsuario;
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
        JPanel central = new JPanel();
        central.setLayout(new GridLayout(1, 2));
        content.add(central, BorderLayout.CENTER);
        // boton de Visualizar por niveles
        JButton verNiveles = new JButton("Visualizar Por Niveles");
        verNiveles.addActionListener(mouseEventHandler());
        content.add(verNiveles, BorderLayout.NORTH);

        Sistema.getInstance().datosAObjetosRanking();

        DefaultListModel<String> lista1 = new DefaultListModel<>();
        JSONObject array = Sistema.getInstance().obtenerRankingGlobal(usuario);
        JSONArray global = (JSONArray) array.get("global");
        JSONArray personal = (JSONArray) array.get("personal");
        lista1.addElement("Ranking Global");
        for (int i = global.length()-1; i > 0; i--) {
            JSONObject x=global.getJSONObject(i);
            lista1.addElement(x.getString("usuario") + x.getInt("puntuacion"));
        }

        JList<String> listaGl = new JList<>(lista1);
        listaGl.setBounds(100, 100, 200, 100);

        DefaultListModel<String> lista2 = new DefaultListModel<>();
        lista2.addElement("Ranking Personal");
        for (int i = personal.length()-1; i > 0; i--) {
            JSONObject x=personal.getJSONObject(i);
            lista2.addElement(x.getString("usuario") + x.getInt("puntuacion"));
        }
        JList<String> listaPers = new JList<>(lista2);
        listaPers.setBounds(100, 100, 200, 100);
        central.add(listaGl);
        central.add(listaPers);


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

                    if (Objects.equals(button.getText(), "Visualizar Por Niveles")) {
                        // Abrimos la vista de log in
                        GestorPaneles.getInstance().bind(new com.visual.funcionalidad5.Interfaz3(usuario,1));
                    }
                }
            }
        };


    }
}
