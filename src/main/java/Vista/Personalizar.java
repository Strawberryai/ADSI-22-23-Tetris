package Vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Personalizar extends JFrame{

    private static Personalizar miMenu;

    private JPanel panelBox;
    private JPanel panelBoton;
    private JComboBox Colores;
    private JComboBox Sonido;
    private JComboBox Ladrillo;
    private JButton actualizar;
    private JLabel etiqueta;
    private JLabel color;
    private JLabel sonido;
    private JLabel ladrillo;

    public  Personalizar(){
        super("Personalizar");
        setMinimumSize(new Dimension(300,300));
        setVisible(true);

        Container contentpane = getContentPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentpane.setLayout(new GridLayout(2, 1, 0, 0));


        panelBox=new JPanel();
        panelBoton= new JPanel();

        panelBox.setLayout(new FlowLayout());
        panelBoton.setLayout(new FlowLayout());
        getContentPane().add(panelBox, BorderLayout.CENTER);
        getContentPane().add(panelBoton,BorderLayout.CENTER);

        setLocationRelativeTo(null);
        String colores[]= new String[]{
                "azul",
                "verde",
                "rojo"
        };
        String sonidos[]= new String[]{
                "boom",
                "cuack",
                "casa"
        };
        String Ladrillos[]= new String[]{
                "basico",
                "moderno",
                "retro"
        };

        Colores = new JComboBox(colores);
        Sonido = new JComboBox(sonidos);
        Ladrillo = new JComboBox(Ladrillos);
        actualizar = getBoton("Actualizar configracion");
        color = new JLabel("Elige color");
        sonido= new JLabel("Elige sonido");
        ladrillo= new JLabel("Elige ladrillo");

        actualizar.setBackground(Color.ORANGE);
        actualizar.addActionListener(new BotonPulsadoListener());

        //salir.setBackground(new Color(255, 150, 150));
        panelBox.add(Colores,FlowLayout.LEFT);
        panelBox.add(Sonido, FlowLayout.CENTER);
        panelBox.add(Ladrillo,FlowLayout.RIGHT);
        panelBox.add(color,FlowLayout.LEFT);
        panelBox.add(sonido,FlowLayout.CENTER);
        panelBox.add(ladrillo,FlowLayout.RIGHT);
        panelBoton.add(actualizar, BorderLayout.NORTH);

        setVisible(true);



        //etiqueta = new JLabel("");
        //add(etiqueta);

    };
    private JButton getBoton(String text){
        JButton boton = new JButton(text);
        //boton.addMouseListener(ControladorVentanaMenu.getInstance());
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        return boton;
    }

    public static void main(String[] args) {

        new Personalizar();

    }
    private class BotonPulsadoListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String color = (String) Colores.getSelectedItem();
            String sonido = (String) Sonido.getSelectedItem();
            String ladrillo = (String) Ladrillo.getSelectedItem();
           // etiqueta.setText(color+"Has pulsado el botón " + e.getActionCommand());
            actualizar.setBackground(Color.GREEN);
        }
    }
}