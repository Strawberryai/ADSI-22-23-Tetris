package com.visual.funcionalidad3;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Sonido extends JFrame implements ActionListener{
    private static Sonido miSonido;
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
       // sonido.ReproducirSonido("C:\\Users\\Mario\\OneDrive\\Escritorio\\Sonidos\\0008368.wav");
        sonido.ReproducirSonido("Resouces/Escudo.wav");
    }
    public void ReproducirSonido(String nombreSonido){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(nombreSonido));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == boton1){

            ReproducirSonido("src/Resources/Impacto_Arma.wav");
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