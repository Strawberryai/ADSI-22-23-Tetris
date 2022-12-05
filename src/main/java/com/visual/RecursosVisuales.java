package com.visual;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RecursosVisuales {
    /**
     * Esta clase se encarga de manejar todos los recursos externos y o comunes
     * de las interfaces
     */
    private static RecursosVisuales instance;
    private final String imagePath = "assets/images/";
    private final String fontPath = "assets/fonts/";

    // Iconos de tetris
    private JLabel redSprite;
    private JLabel blueSprite;
    private JLabel orangeSprite;

    // Fuentes del sistema
    public Font titleFont;
    public Font subTitleFont;

    private RecursosVisuales(){
        try {
            redSprite = new JLabel(getIconImage(imagePath + "sprite_rojo.png"));
            blueSprite = new JLabel(getIconImage(imagePath + "sprite_azul.png"));
            orangeSprite = new JLabel(getIconImage(imagePath + "sprite_naranja.png"));
        } catch (IOException e) {e.printStackTrace();}


        //create the font

        try {
            //create the font to use. Specify the size!
            titleFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath + "ZenDots-Regular.ttf")).deriveFont(40f);
            subTitleFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath + "ZenDots-Regular.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(titleFont);
            ge.registerFont(subTitleFont);
        } catch (Exception e) {
            titleFont = new Font("Serif", Font.PLAIN, 40);
            subTitleFont = new Font("Serif", Font.PLAIN, 20);
            e.printStackTrace();
        }


    }

    public static RecursosVisuales getInstance(){
        if(RecursosVisuales.instance == null) RecursosVisuales.instance = new RecursosVisuales();
        return RecursosVisuales.instance;
    }

    private ImageIcon getIconImage(String path) throws IOException {
        BufferedImage myPicture = null;
        myPicture = ImageIO.read(new File(path));

        return new ImageIcon(myPicture);
    }

    public JPanel getTitle(){
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new FlowLayout());

        // Añadimos el icono azul
        if(orangeSprite != null)
            titlePanel.add(orangeSprite);

        // Añadimos el texto
        JLabel title = new JLabel("TETRIS ADSI 2022");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(titleFont);
        titlePanel.add(title);

        // Añadimos el icono rojo
        if(redSprite != null)
            titlePanel.add(redSprite);

        // Creamos el borde inferior
        titlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 10, 0, Color.BLACK));

        return titlePanel;
    }
}
