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

public class Interfaz3 extends PlantillaInterfaces {
    /**
     * Interfaz ranking global: Usuario loggeado.
     * Se presenta el ranking global tanto personal como de todos los usuarios,
     * se da la opción de ver el ranking por niveles.
     */

    private String usuario;
    private boolean esAdmin;
    private int nivel;

    public Interfaz3(String pUsuario, int pNivel, boolean pEsAdmin) {
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        usuario = pUsuario;
        nivel=pNivel;
        esAdmin = pEsAdmin;

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Ranking Global"), BorderLayout.CENTER);
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


        DefaultListModel<String> lista1 = new DefaultListModel<>();
        JSONObject array = Sistema.getInstance().obtenerPuntuaciones(nivel,usuario);
        JSONArray global = (JSONArray) array.get("global");
        JSONArray personal = (JSONArray) array.get("personal");
        String[] columnNames = { "Usuario", "Puntuacion" };

        DefaultTableModel modelGlobal = new DefaultTableModel(columnNames, 0);

        for( int i= global.length()-1;i>=0;i--)
        {   JSONObject o=(JSONObject) global.getJSONObject(i);
            Vector<String> row = new Vector<String>();
            row.add(o.getString("usuario"));
            row.add(String.valueOf(o.getInt("puntuacion")));
            modelGlobal.addRow( row );
        }

        JTable tableG = new JTable( modelGlobal );
        JScrollPane scrollPane2 = new JScrollPane( tableG );
        central.add(scrollPane2);
        DefaultTableModel modelPersonal = new DefaultTableModel(columnNames, 0);

        for(int i= personal.length()-1;i>=0;i--)
        {   JSONObject o=(JSONObject) personal.getJSONObject(i);
            Vector<String> row = new Vector<String>();
            row.add(o.getString("usuario"));
            row.add(String.valueOf(o.getInt("puntuacion")));
            modelPersonal.addRow( row );
        }

        JTable tableP = new JTable( modelPersonal );
        JScrollPane scrollPane = new JScrollPane( tableP );
        central.add(scrollPane);


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

                    if (Objects.equals(button.getText(), "Volver")) {
                        // Volvemos a la pagina principal
                        GestorPaneles.getInstance().bind(new Interfaz9(usuario, esAdmin));

                    }else if (Objects.equals(button.getText(), "Visualizar Global")) {
                        // Abrimos la vista de log in
                        GestorPaneles.getInstance().bind(new com.visual.funcionalidad5.Interfaz2(usuario, esAdmin));
                    }else if(Objects.equals(button.getText(), "Nivel 1")) {
                        GestorPaneles.getInstance().bind(new com.visual.funcionalidad5.Interfaz3(usuario, 1, esAdmin));
                    }else if(Objects.equals(button.getText(), "Nivel 2")) {
                        GestorPaneles.getInstance().bind(new com.visual.funcionalidad5.Interfaz3(usuario, 2, esAdmin));
                    }else if(Objects.equals(button.getText(), "Nivel 3")) {
                        GestorPaneles.getInstance().bind(new com.visual.funcionalidad5.Interfaz3(usuario, 3, esAdmin));
                    }
                }
            }
        };


    }
}