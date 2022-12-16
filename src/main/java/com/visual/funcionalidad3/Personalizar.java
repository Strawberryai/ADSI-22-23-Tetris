package com.visual.funcionalidad3;

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

    private String usuario;
    private boolean esAdmin;

    public Personalizar(String pUsuario, boolean pEsAdmin) {
        RecursosVisuales rv = RecursosVisuales.getInstance();
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());

        usuario = pUsuario;
        esAdmin = pEsAdmin;

        add(rv.getTitle(), BorderLayout.NORTH);
        add(getMainPanel("Actualizar Configuracion"), BorderLayout.CENTER);
    }

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
                "predeterminado",
                "azul",
                "verde",
                "rojo"
        };
        String sonidos[] = new String[]{
                "predeterminado",
                "boom",
                "cuack",
                "casa"
        };
        String Ladrillos[] = new String[]{
                "predeterminado",
                "rojo",
                "azul",
                "verde"
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

                    if(Objects.equals(button.getText(), "Volver")){
                        // Volver a la pagina principal
                        GestorPaneles.getInstance().bind(new Interfaz9(usuario, esAdmin));

                    }else if (Objects.equals(button.getText(), "Actualizar configracion")) {
                        String pColor = (String) Colores.getSelectedItem();
                        String pSonido = (String) Sonido.getSelectedItem();
                        String pLadrillo = (String) Ladrillo.getSelectedItem();
                        Sistema.getInstance().actualizarConfiguracion(usuario,pColor, pSonido, pLadrillo);
<<<<<<< HEAD

                        Usuario usu = GestorUsuarios.getInstance().buscarUsuario(usuario);
                        String musica = usu.getConfig().getSonido();
                        if(musica.equals("Positiva")){
                            com.visual.funcionalidad3.Sonido.getMiSonido().pararSonido();
                            com.visual.funcionalidad3.Sonido.getMiSonido().ReproducirSonido("/audios/positivaC.wav");
                        } else if (musica.equals("Intriga")) {
                            com.visual.funcionalidad3.Sonido.getMiSonido().pararSonido();
                            com.visual.funcionalidad3.Sonido.getMiSonido().ReproducirSonido("/audios/intrigaC.wav");
                        }else if (musica.equals("Epico")) {
                            com.visual.funcionalidad3.Sonido.getMiSonido().pararSonido();
                            com.visual.funcionalidad3.Sonido.getMiSonido().ReproducirSonido("/audios/epica.wav");
                        }else if (musica.equals("Relajante")) {
                            com.visual.funcionalidad3.Sonido.getMiSonido().pararSonido();
                            com.visual.funcionalidad3.Sonido.getMiSonido().ReproducirSonido("/audios/relajanteC.wav");
                        }

=======
>>>>>>> parent of 5f49f50... nose
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

   /* public static void main(String[] args) {

        new Personalizar();

    }*/
}