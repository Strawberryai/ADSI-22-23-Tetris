package com.visual.funcionalidad4;
import com.visual.GestorPaneles;
import com.visual.PlantillaInterfaces;
import com.visual.RecursosVisuales;
import com.visual.funcionalidad1.Interfaz2;
import com.visual.funcionalidad1.Interfaz1;
import com.zetcode.Board;
import com.zetcode.Sistema;
import com.zetcode.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class InterfazCargarPartida extends PlantillaInterfaces {
    private String usuario;
    public InterfazCargarPartida(String pUsuario) {
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Cargar Partida"), BorderLayout.CENTER);
        this.usuario=pUsuario;

    }

    protected JPanel getContentPanel(){
        // Contenido de la Interfaz -> main panel (subtitulo + contenido)
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        // Creamos el panel del subtitulo (flowlayout)
        JPanel subTitlePanel = new JPanel();
        subTitlePanel.setLayout(new FlowLayout());
        JLabel subTitle = new JLabel("Cargar Partida");
        subTitle.setFont(RecursosVisuales.getInstance().subTitleFont);
        subTitlePanel.add(subTitle);

        // Añadimos el panel del subtitulo en la parte superior
        main.add(subTitlePanel, BorderLayout.NORTH);

        // [No loggeado] -> El contenido es un flowlayout con los botones de login...
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());

        // botones de los arhcivos
        String usuario=this.usuario;
        String userHomeDir = System.getProperty("user.home");
        String directorio=userHomeDir+ "/TetrisSaveFiles/"+usuario+"/";
        System.out.println(directorio);
        File f=new File(directorio);
        String[] pathnames=f.list();
        int i=0;
        if(f.exists()) {
            for (String pathname : pathnames) {
                JButton cargar = new JButton(pathname);
                cargar.addActionListener(mouseEventHandler());
                content.add(cargar);
                main.add(content, BorderLayout.CENTER);
            }
        }
        else{
            JLabel ERROR= new JLabel("ERROR:No existen partidas guardadas");
            content.add(ERROR);
            JButton volver = new JButton("volver");
            volver.addActionListener(mouseEventHandler());
            content.add(volver);
            main.add(content, BorderLayout.CENTER);
        }
        return main;
    }

    protected ActionListener mouseEventHandler(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();
                String userHomeDir = System.getProperty("user.home");


                if(o instanceof JButton){
                    JButton button = (JButton) o;
                    File f=new File(userHomeDir+ "/TetrisSaveFiles/"+usuario+"/"+button.getText());
                    if(button.getText()=="volver"){
                        GestorPaneles.getInstance().bind(new Interfaz1());
                    }
                    else if(f.exists()) {
                        System.out.println("Cargando:" + userHomeDir+ "/TetrisSaveFiles/"+usuario+"/"+button.getText());
                        try {

                            Sistema.getInstance().cargarPartida(userHomeDir+ "/TetrisSaveFiles/"+usuario+"/"+button.getText(),usuario);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        };
    }



}