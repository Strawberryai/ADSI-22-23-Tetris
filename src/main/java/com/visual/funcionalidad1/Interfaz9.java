package com.visual.funcionalidad1;

import com.visual.GestorPaneles;
import com.visual.PlantillaInterfaces;
import com.visual.RecursosVisuales;
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

    public Interfaz9(String pUsuario){
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        usuario = pUsuario;

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
                        GestorPaneles.getInstance().bind(new Interfaz10(usuario));
                    }else if(Objects.equals(button.getText(), "Log out")){
                        // Volvemos a la vista principal pero desloggeados
                        GestorPaneles.getInstance().bind(new Interfaz1());
                    }
                }
            }
        };
    }

}
