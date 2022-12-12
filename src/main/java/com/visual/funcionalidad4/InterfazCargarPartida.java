package com.visual.funcionalidad4;
import com.visual.GestorPaneles;
import com.visual.PlantillaInterfaces;
import com.visual.RecursosVisuales;
import com.visual.funcionalidad1.Interfaz2;
import com.visual.funcionalidad1.Interfaz1;
import com.visual.funcionalidad1.Interfaz9;
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
    private boolean esAdmin;
    public InterfazCargarPartida(String pUsuario,boolean esAdmin) {
        this.usuario=pUsuario;
        this.esAdmin=esAdmin;
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Cargar Partida"), BorderLayout.CENTER);
        System.out.println(pUsuario);


    }

    protected JPanel getContentPanel(){
        // Contenido de la Interfaz -> main panel (subtitulo + contenido)
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        // [No loggeado] -> El contenido es un flowlayout con los botones de login...
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());

        // botones de los arhcivos

        String path= Paths.get("").toAbsolutePath().toString();
        String directorioGuardados=path+File.separator+"assets"+ File.separator+"tetris_files"+File.separator+usuario+"guardado";
        System.out.println(directorioGuardados);
        File dir=new File(directorioGuardados);
        if(!dir.exists()){
            dir.mkdirs();
        }
        directorioGuardados=directorioGuardados+File.separator;
        File f=new File(directorioGuardados);
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
        /*else{
            JLabel ERROR= new JLabel("ERROR:No existen partidas guardadas");
            content.add(ERROR);
            JButton volverMenu = new JButton("volver al menu");
            volverMenu.addActionListener(mouseEventHandler());
            content.add(volverMenu);
            main.add(content, BorderLayout.CENTER);
        }*/
        return main;
    }

    protected ActionListener mouseEventHandler(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();


                if(o instanceof JButton){
                    JButton button = (JButton) o;
                    String path= Paths.get("").toAbsolutePath().toString();
                    String directorioGuardados=path+File.separator+"assets"+ File.separator+"tetris_files"+File.separator+usuario+"guardado"+File.separator;
                    File f=new File(directorioGuardados+button.getText());
                    if(button.getText()=="volver al menu"){
                        System.out.println("volviendo");
                        GestorPaneles.getInstance().bind(new Interfaz9(usuario,esAdmin));
                    }
                    else if(button.getText()=="Volver") {
                        System.out.println("volviendo");
                        GestorPaneles.getInstance().bind(new Interfaz9(usuario,esAdmin));
                    }
                    else if(f.exists()) {
                        System.out.println("Cargando:" +directorioGuardados+button.getText());
                        try {

                            Sistema.getInstance().cargarPartida(directorioGuardados+button.getText(),usuario,esAdmin);
                        } catch (IOException ex) {
                            GestorPaneles.getInstance().bind(new InterfazError(usuario,esAdmin));
                            throw new RuntimeException(ex);

                        }
                    }
                    else if(!f.exists()){
                        GestorPaneles.getInstance().bind(new InterfazError(usuario,esAdmin));
                    }

                }
            }
        };
    }



}