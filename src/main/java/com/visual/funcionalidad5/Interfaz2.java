package com.visual.funcionalidad5;


import com.visual.GestorPaneles;
import com.visual.PlantillaInterfaces;
import com.visual.RecursosVisuales;
import com.visual.funcionalidad1.Interfaz4;
import com.visual.funcionalidad1.Interfaz6;
import com.visual.funcionalidad1.Interfaz9;
import com.zetcode.Sistema;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;

public class Interfaz2 extends PlantillaInterfaces {
    /**
     * Interfaz ranking global: Usuario loggeado.
     * Se presenta el ranking global tanto personal como de todos los usuarios,
     * se da la opción de ver el ranking por niveles.
     */

    private String usuario;
    private boolean esAdmin;

    public Interfaz2(String pUsuario, boolean pEsAdmin) {
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        usuario = pUsuario;
        esAdmin = pEsAdmin;
        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Ranking Global"), BorderLayout.CENTER);
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
        JSONObject array = Sistema.getInstance().obtenerRankingGlobal(usuario);

        JSONArray global = (JSONArray) array.get("global");
        JSONArray personal = (JSONArray) array.get("personal");
        String[] columnNames = { "Usuario", "Puntuacion" };
        DefaultTableModel modelPersonal = new DefaultTableModel(columnNames, 0);

        for( Object obj : personal)
        {   JSONObject o=(JSONObject) obj;
            Vector<String> row = new Vector<String>();
            row.add(o.getString("usuario"));
            row.add(String.valueOf(o.getInt("puntuacion")));
            modelPersonal.addRow( row );
        }

        JTable tableP = new JTable( modelPersonal );
        JScrollPane scrollPane = new JScrollPane( tableP );
        central.add(scrollPane);

        DefaultTableModel modelGlobal = new DefaultTableModel(columnNames, 0);

        for( Object obj : global)
        {   JSONObject o=(JSONObject) obj;
            Vector<String> row = new Vector<String>();
            row.add(o.getString("usuario"));
            row.add(String.valueOf(o.getInt("puntuacion")));
            modelGlobal.addRow( row );
        }

        JTable tableG = new JTable( modelGlobal );
        JScrollPane scrollPane2 = new JScrollPane( tableG );
        central.add(scrollPane2);

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
                    if (Objects.equals(button.getText(), "Volver")) {
                        // Volvemos a la pagina principal
                        GestorPaneles.getInstance().bind(new Interfaz9(usuario,esAdmin));

                    } else if (Objects.equals(button.getText(), "Visualizar Por Niveles")) {
                        // Abrimos la vista de log in
                        GestorPaneles.getInstance().bind(new com.visual.funcionalidad5.Interfaz3(usuario,1, esAdmin));
                    }
                }
            }
        };


    }
}
