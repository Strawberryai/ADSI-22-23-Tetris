package com.visual.funcionalidad3;

import org.apache.logging.log4j.util.SystemPropertiesPropertySource;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Sonido extends JFrame implements ActionListener{
    private static Sonido miSonido;
    private Clip clip;
    private AudioInputStream audioInputStream;
    JButton boton1 = new JButton("Pulsame");
    /*  public Sonido(){
          super("Pulse el boton");
          setSize(400,80);
          setLocationRelativeTo(null);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setLayout(new FlowLayout());
          add(boton1);
          boton1.addActionListener(this);
          setVisible(true);
      }*/
    public static void main(String[] Dar10){
        Sonido sonido = new Sonido();

        //getMiSonido().ReproducirSonido("/audios/epica.wav");
        getMiSonido().reproducirSondoEnLoop("/audios/predeterminada.wav");
        System.out.println("Hola esto es una prueba");
    }
    public void ReproducirSonido(String nombreSonido){
        try {
            audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(nombreSonido));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
            System.out.println("Error al reproducir el sonido.");
        }
    }

    public void reproducirSondoEnLoop(String nombreSonido) {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(nombreSonido));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
          //  Thread.sleep(3600000);
            System.out.println("se esta reproduciendo: "+nombreSonido);
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException  ex) {
            ex.printStackTrace();
            System.out.println("Error al reproducir el sonido.");
        }
    }
    public void pararSonido(){

           /* audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(nombreSonido));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);*/
        clip.stop();
        //clip.flush();
        //clip.close();
        System.out.println("se ha parado la cancion: ");

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == boton1){

            ReproducirSonido("src/Resources/gameOver.wav");
        }
    }
    private Sonido() {
    }
    public static Sonido getMiSonido(){
        if (miSonido==null) {
            miSonido= new Sonido();
        }
        return miSonido;
    }
}