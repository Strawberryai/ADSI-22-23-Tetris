package com.visual.funcionalidad7;

import com.visual.GestorPaneles;
import com.visual.PlantillaInterfaces;
import com.visual.RecursosVisuales;
import com.visual.funcionalidad1.Interfaz1;
import com.visual.funcionalidad1.Interfaz9;
import com.zetcode.Sistema;

import twitter4j.TwitterException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.JOptionPane;


public class InterfazD extends PlantillaInterfaces {
    /*Interfaz para representar la obetnción de un premio*/

    String usuario;
    boolean esAdmin;
    int puntuacion;

    public InterfazD(String pUsuario, boolean pEsAdmin, int pPuntuacion) {
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        usuario = pUsuario;
        esAdmin = pEsAdmin;

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Partida Finalizada"), BorderLayout.CENTER);
    }

    @Override
    protected JPanel getSubtitlePanel(String subtitle){
        // Creamos una interfaz en la que nos deje publicar el resultado de nuestra partida
        JPanel submain = new JPanel();
        submain.setLayout(new BorderLayout());

        JButton returnButton = new JButton("Aceptar");
        returnButton.addActionListener(mouseEventHandler());
        submain.add(returnButton, BorderLayout.EAST);

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

        content.add(new JLabel("Felicidades, has conseguido " + puntuacion+" puntos, quieres publicar tu resultado?"));

        JButton pubTwitter = new JButton("Twitter");
        pubTwitter.addActionListener(mouseEventHandler());
        content.add(pubTwitter);
        
        JButton pubFacebook = new JButton("Facebook");
        pubFacebook.addActionListener(mouseEventHandler());
        content.add(pubFacebook);

        JButton pubInstagram = new JButton("Instagram");
        pubInstagram.addActionListener(mouseEventHandler());
        content.add(pubInstagram);

        JButton Volver = new JButton("Volver");
        Volver.addActionListener(mouseEventHandler());
        content.add(Volver);
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

                    ImageIcon icono = new ImageIcon("resources/imagenes/trofeo.jpg");

                    JPanel panel = new JPanel();
                    panel.setBackground(new Color(102, 205, 170));
                    panel.setSize(new Dimension(200, 64));
                    panel.setLayout(null);

                    JLabel label = new JLabel("Publicado correctamente");
                    label.setBounds(0, 0, 200, 64);
                    label.setFont(new Font("Arial", Font.BOLD, 13));
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    panel.add(label);
                    UIManager.put("OptionPane.minimumSize", new Dimension(300, 120));
                    
                    if(Objects.equals(button.getText(), "Facebook")){
                        JOptionPane.showMessageDialog(null, panel,"Notificación", JOptionPane.PLAIN_MESSAGE,icono);
                        GestorPaneles.getInstance().bind(new Interfaz9(usuario, esAdmin));
                        

                    }else if(Objects.equals(button.getText(), "Twitter")){
                        JOptionPane.showMessageDialog(null, panel,"Notificación", JOptionPane.PLAIN_MESSAGE,icono);
                        GestorPaneles.getInstance().bind(new Interfaz9(usuario, esAdmin));

                    }else if(Objects.equals(button.getText(), "Instagram")){
                        JOptionPane.showMessageDialog(null, panel,"Notificación", JOptionPane.PLAIN_MESSAGE,icono);
                        GestorPaneles.getInstance().bind(new Interfaz9(usuario,esAdmin));
                    }
                    else if(Objects.equals(button.getText(), "Volver")){
                        GestorPaneles.getInstance().bind(new Interfaz9(usuario,esAdmin));
                    }
                }
            }
        };
    }

}
