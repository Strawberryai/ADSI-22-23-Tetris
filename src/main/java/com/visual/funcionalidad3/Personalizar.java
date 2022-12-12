package com.visual.funcionalidad3;

import com.visual.GestorPaneles;
import com.visual.PlantillaInterfaces;
import com.visual.RecursosVisuales;
import com.visual.funcionalidad1.Interfaz2;
import com.visual.funcionalidad1.Interfaz4;
import com.visual.funcionalidad1.Interfaz6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Personalizar extends PlantillaInterfaces {

    private static Personalizar miMenu;

    private JPanel panelBox;
    private JPanel panelBoton;
    private JPanel panelLbanel;
    private JComboBox Colores;
    private JComboBox Sonido;
    private JComboBox Ladrillo;
    private JButton actualizar;
    private JLabel etiqueta;
    private JLabel color;
    private JLabel sonido;
    private JLabel ladrillo;

    public Personalizar() {

        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Actualizar Configuracion"), BorderLayout.CENTER);


    }

    ;

    protected JPanel getContentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panelBox = new JPanel();
        panelBoton = new JPanel();
        panelLbanel = new JPanel();

        panelBox.setLayout(new FlowLayout());
        panelBoton.setLayout(new FlowLayout());
        panel.add(panelBox, BorderLayout.CENTER);
        panel.add(panelLbanel, BorderLayout.NORTH);
        panel.add(panelBoton, BorderLayout.SOUTH);

        String colores[] = new String[]{
                "azul",
                "verde",
                "rojo"
        };
        String sonidos[] = new String[]{
                "boom",
                "cuack",
                "casa"
        };
        String Ladrillos[] = new String[]{
                "basico",
                "moderno",
                "retro"
        };

        Colores = new JComboBox(colores);
        Sonido = new JComboBox(sonidos);
        Ladrillo = new JComboBox(Ladrillos);
        actualizar = getBoton("Actualizar configracion");
        color = new JLabel("Elige color");
        sonido = new JLabel("Elige sonido");
        ladrillo = new JLabel("Elige ladrillo");

        actualizar.setBackground(Color.ORANGE);
        actualizar.addActionListener(mouseEventHandler());

        //salir.setBackground(new Color(255, 150, 150));
        panelBox.add(Colores, FlowLayout.LEFT);
        panelBox.add(Sonido, FlowLayout.CENTER);
        panelBox.add(Ladrillo, FlowLayout.RIGHT);
        panelLbanel.add(color, FlowLayout.LEFT);
        panelLbanel.add(sonido, FlowLayout.CENTER);
        panelLbanel.add(ladrillo, FlowLayout.RIGHT);
        panelBoton.add(actualizar, BorderLayout.NORTH);

        return panel;
    }

    @Override
    protected ActionListener mouseEventHandler() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();

                if (o instanceof JButton) {
                    JButton button = (JButton) o;

                    if (Objects.equals(button.getText(), "Actualizar configracion")) {
                        actualizar.setBackground(Color.GREEN);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        actualizar.setBackground(Color.ORANGE);


                    }
                }
            }
        };
    }

    private JButton getBoton(String text) {
        JButton boton = new JButton(text);
        //boton.addMouseListener(ControladorVentanaMenu.getInstance());
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        return boton;
    }

    public static void main(String[] args) {

        new Personalizar();

    }
}