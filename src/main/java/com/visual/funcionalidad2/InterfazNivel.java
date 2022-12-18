package com.visual.funcionalidad2;

import com.visual.GestorPaneles;
import com.visual.PlantillaInterfaces;
import com.visual.RecursosVisuales;
import com.visual.funcionalidad1.*;
import com.zetcode.Sistema;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class InterfazNivel extends PlantillaInterfaces {
    /**
     * Interfaz de selección de nivel;
     * Se presentan tres campos; uno para cada dificultad de nivel: ;
     * fácil, intermedio y difícil ;
     * Un botón para aplicar la dificultad seleccionada: Elegir nivel;
     */
    private String usuario;
    private boolean esAdmin;
    private int nivel;

    JRadioButton nFacil;
    JRadioButton nIntermedio;
    JRadioButton nDificl;

    public InterfazNivel(String pUsuario, Boolean pEsAdmin){
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        usuario = pUsuario;
        esAdmin = pEsAdmin;

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Elegir nivel"), BorderLayout.CENTER);
    }
    private int getNivelSelected() {
        int nivel = 0;
        if (nFacil.isSelected()){
            nivel = 1;
        } else if (nIntermedio.isSelected()) {
            nivel = 2;
        } else if (nDificl.isSelected()) {
            nivel = 3;
        }
        return nivel;
    }
    public void deseleccionarBotones(int pNiv){
        if (pNiv == 1){

            nIntermedio.setEnabled(false);
            nDificl.setEnabled(false);
        }
        else if (pNiv == 2){
            nFacil.setEnabled(false);

            nDificl.setEnabled(false);
        }
        else if (pNiv == 3){
            nFacil.setEnabled(false);
            nIntermedio.setEnabled(false);

        }

    }

    @Override
    protected JPanel getContentPanel(){
        // Contenido principal de la vista
        // El contenido es un borderlayout con los botones de elegir nivel y demás
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        ButtonGroup grupoNiveles = new ButtonGroup();

        // creamos los botones de los niveles
        nFacil = new JRadioButton("Facil");
        nFacil.addActionListener(mouseEventHandler());
        nIntermedio = new JRadioButton("Intermedio");
        nIntermedio.addActionListener(mouseEventHandler());
        nDificl = new JRadioButton("Dificil");
        nDificl.addActionListener(mouseEventHandler());

        // los añadimos al group button
        grupoNiveles.add(nFacil);
        grupoNiveles.add(nIntermedio);
        grupoNiveles.add(nDificl);

        content.add(nFacil);
        content.add(nIntermedio);
        content.add(nDificl);

        // boton de seleccionar nivel
        JButton selectLevel = new JButton("Jugar Partida");
        selectLevel.addActionListener(mouseEventHandler());
        content.add(selectLevel);

        return content;
    }
    public int getWidthPorNivel(int pNivel){
        int statX = 10;
        if(pNivel == 1){
            statX = 10;

        } else if (pNivel == 2) {
            statX = 12;
        }
        else if(pNivel == 3){
            statX = 14;
        }
        return (statX);
    }
    public int getHeightPorNivel(int pNivel){
        int statY = 22;
        if(pNivel == 1){
            statY = 22;

        } else if (pNivel == 2) {
            statY = 21;
        }
        else if(pNivel == 3){
            statY = 20;
        }
        return (statY);
    }
    public int getPeriodPorNivel(int pNivel){
        int statV= 300;
        if(pNivel == 1){
            statV = 300;

        } else if (pNivel == 2) {
            statV = 150;
        }
        else if(pNivel == 3){
            statV = 75;
        }
        return (statV);
    }

    @Override
    protected ActionListener mouseEventHandler(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();

                if(o instanceof  JRadioButton){
                    JRadioButton button = (JRadioButton) o;
                    if(Objects.equals(button.getText(), "Facil")){
                        setNivel(1);
                        System.out.println(nivel);
                    }
                    else if(Objects.equals(button.getText(), "Intermedio")){
                        setNivel(2);
                        System.out.println(nivel);
                    }
                    else if(Objects.equals(button.getText(), "Dificil")){
                        setNivel(3);
                        System.out.println(nivel);
                    }
                }

                if(o instanceof JButton){
                    JButton button = (JButton) o;

                    if ((Objects.equals(button.getText(), "Volver"))){
                        // Volvemos a la interfaz del usuario logeado
                        GestorPaneles.getInstance().bind(new Interfaz9(usuario, esAdmin));
                    } else if ((Objects.equals(button.getText(), "Jugar Partida"))) {
                            //int nivel = getNivelSelected();
                            System.out.println(nivel);
                        if (nivel > 0){
                                //Sistema.getInstance().actualizarNivel(usuario,esAdmin, nivel);
                                button.setEnabled(false);
                                deseleccionarBotones(nivel);
                                Sistema.getInstance().actualizarNivel(usuario,esAdmin, getHeightPorNivel(nivel), getWidthPorNivel(nivel), getPeriodPorNivel(nivel), nivel);
                            }
                        else{
                            JOptionPane.showMessageDialog(null, "No has seleccionado nivel");
                        }

                        }
                    }
                }

        };
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
