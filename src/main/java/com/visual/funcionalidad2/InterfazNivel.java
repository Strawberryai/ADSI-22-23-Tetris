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
     */
    private String usuario;
    private boolean esAdmin;
    private int level;
    public InterfazNivel(String pUsuario, Boolean pEsAdmin){
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        usuario = pUsuario;
        esAdmin = pEsAdmin;
        level = 1;

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Elegir nivel"), BorderLayout.CENTER);
    }
    public int getLevel(){ return level; }
    public void setLevel(int pInt){ level=pInt;}

    protected JPanel getSubtitlePanel(String subtitle){
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
    protected JPanel getContentPanel(){
        // Contenido principal de la vista
        // El contenido es un borderlayout con los botones de elegir nivel y demás
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        ButtonGroup grupoNiveles = new ButtonGroup();

        // creamos los botones de los niveles
        JRadioButton nFacil = new JRadioButton("Facil");
        nFacil.addActionListener(mouseEventHandler());
        JRadioButton nIntermedio = new JRadioButton("Intermedio");
        nIntermedio.addActionListener(mouseEventHandler());
        JRadioButton nDificl = new JRadioButton("Dificil");
        nDificl.addActionListener(mouseEventHandler());

        // los añadimos al group button
        grupoNiveles.add(nFacil);
        grupoNiveles.add(nIntermedio);
        grupoNiveles.add(nDificl);

        content.add(nFacil);
        content.add(nIntermedio);
        content.add(nDificl);

        // boton de seleccionar nivel
        JButton selectLevel = new JButton("Seleccionar nivel");
        selectLevel.addActionListener(mouseEventHandler());
        content.add(selectLevel);

        return content;
    }

    @Override
    protected ActionListener mouseEventHandler(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();

                if(o instanceof JButton){
                    JButton button = (JButton) o;

                    if ((Objects.equals(button.getText(), "Volver"))){
                        // Volvemos a la interfaz del usuario logeado
                        GestorPaneles.getInstance().bind(new Interfaz9(usuario, esAdmin));
                    } else if ((Objects.equals(button.getText(), "Seleccionar nivel"))) {
                        if(true){
                            Sistema.getInstance().actualizarNivel(usuario,esAdmin,getLevel());

                        }
                    }
                }
            }
        };
    }

}
