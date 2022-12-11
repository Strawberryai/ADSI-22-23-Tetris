package com.zetcode;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.visual.GestorPaneles;
import com.visual.funcionalidad1.Interfaz1;
import com.visual.funcionalidad1.Interfaz9;
import com.visual.funcionalidad5.Interfaz2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
Java Tetris game clone

Author: Jan Bodnar
Website: https://zetcode.com
 */
public class Tetris extends JFrame {
	
	private static final Logger logger = LogManager.getLogger(Tetris.class);

    private JLabel statusbar;

    public Tetris() {

        initUI();
    }

    private void initUI() {

        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);

        var board = new Board(this);
        add(board);
        board.start();

        setTitle("Tetris");
        setSize(200, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    JLabel getStatusBar() {

        return statusbar;
    }

    public static void main(String[] args) {

    	logger.info("Playing");
        /*EventQueue.invokeLater(() -> {

            var game = new Tetris();
            game.setVisible(true);
        });*/
        GestorBD.getInstance().fillDatabaseWithExampleData();
        GestorPaneles.getInstance().bind(new Interfaz9("Manuel"));
    }
}