package com.visual.funcionalidad1;

import com.visual.GestorPaneles;
import com.visual.PlantillaInterfaces;
import com.visual.RecursosVisuales;
import com.visual.funcionalidad4.InterfazCargarPartida;
import com.zetcode.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Interfaz9 extends PlantillaInterfaces {
    /**
     * Interfaz de Log in del sistema.
     * Se presentan cuatro campos: un textarea para el username;
     * otro para la contraseña; un boton de volver y otro para confirmar
     * el inicio de sesión.
     */

    private String usuario;
    private boolean esAdmin;

    public Interfaz9(String pUsuario, boolean pEsAdmin){
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        usuario = pUsuario;
        esAdmin = pEsAdmin;

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Página Principal"), BorderLayout.CENTER);
    }

    @Override
    protected JPanel getSubtitlePanel(String subtitle){
        // Creamos el panel del subtitulo (borderlayout + flowlayout por el boton de "Cambiar contraseña")
        // Submain es el principal y le añadimos el boton y el flowlayout
        JPanel submain = new JPanel();
        submain.setLayout(new BorderLayout());

        // Nombre usuario loggead + boton cambiar contraseña
        JPanel panelUsuario = new JPanel();
        panelUsuario.setLayout(new FlowLayout());
        panelUsuario.add(new JLabel(usuario));

        if(esAdmin){
            JButton recButton = new JButton("Borrar usuario");
            recButton.addActionListener(mouseEventHandler());
            panelUsuario.add(recButton);
        }

        JButton recButton = new JButton("Cambiar contraseña");
        recButton.addActionListener(mouseEventHandler());
        panelUsuario.add(recButton);
        JButton logoutButton = new JButton("Log out");
        logoutButton.addActionListener(mouseEventHandler());
        panelUsuario.add(logoutButton);
        submain.add(panelUsuario, BorderLayout.EAST);

        //  Subtítulo
        JPanel subTitlePanel = new JPanel();
        subTitlePanel.setLayout(new FlowLayout());
        JLabel subTitle = new JLabel(subtitle);
        subTitle.setFont(RecursosVisuales.getInstance().subTitleFont);
        subTitlePanel.add(subTitle);

        submain.add(subTitlePanel, BorderLayout.CENTER);
        submain.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

        return submain;
    }

    @Override
    protected JPanel getContentPanel(){
        // Contenido principal de la vista
        JPanel content = new JPanel();

        content.add(new JLabel("Añadir contenido de la página aquí (clase: funcionalidad1.Interfaz9; método: getContentPanel())"));

        JButton CPartida = new JButton("Cargar Partida");
        CPartida.addActionListener(mouseEventHandler());
        content.add(CPartida);

        JButton JPartida = new JButton("Jugar Partida");
        JPartida.addActionListener(mouseEventHandler());
        content.add(JPartida);

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

                    if(Objects.equals(button.getText(), "Cambiar contraseña")){
                        // Abrimos la vista de cambio de contraseña
                        GestorPaneles.getInstance().bind(new Interfaz10(usuario, esAdmin));

                    }else if(Objects.equals(button.getText(), "Borrar usuario")){
                        // Abrimos la vista de borrar usuarios
                        GestorPaneles.getInstance().bind(new Interfaz12(usuario, esAdmin));

                    }else if(Objects.equals(button.getText(), "Log out")){
                        // Volvemos a la vista principal pero desloggeados
                        GestorPaneles.getInstance().bind(new Interfaz1());
                    }
                    else if(Objects.equals(button.getText(),"Cargar Partida")){
                        //System.out.println("Cargar Partida");
                        GestorPaneles.getInstance().bind(new InterfazCargarPartida(usuario));
                    }
                    else if(Objects.equals(button.getText(),"Jugar Partida")){
                        //System.out.println("Jugar Partida");
                        //GestorPaneles.getInstance().bind(new InterfazCargarPartida(usuario));
                        Sistema.getInstance().jugarNuevaPartida(usuario);
                    }
                }
            }
        };
    }

}
