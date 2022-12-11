package com.zetcode;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.visual.GestorPaneles;
import com.visual.funcionalidad1.Interfaz1;
import com.visual.funcionalidad1.Interfaz9;
import com.visual.funcionalidad4.InterfazGuardar;
import com.visual.funcionalidad1.Interfaz9;
import com.visual.funcionalidad5.Interfaz2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/*
Java Tetris game clone

Author: Jan Bodnar
Website: https://zetcode.com
 */
public class Tetris extends JFrame {
    private String usuario;
    private  boolean esAdmin;
	private static Tetris tetris=null;
	private static final Logger logger = LogManager.getLogger(Tetris.class);

    private JLabel statusbar;

    public Tetris(int BOARD_HEIGHT,int BOARD_WIDTH,int PERIOD_INTERVAL,boolean cargar,boolean isFallingFinished,boolean isPaused,int numLinesRemoved,int curX,int curY,Shape curPiece,Shape.Tetrominoe[] board,String pUsuario,boolean esAdmin) {
        this.usuario=pUsuario;
        this.esAdmin=esAdmin;
        System.out.println(this.usuario);
        if (cargar){
            initUICargar(BOARD_HEIGHT,BOARD_WIDTH,PERIOD_INTERVAL,isFallingFinished,isPaused,numLinesRemoved,curX,curY,curPiece,board);
        }
        else{
            initUI();
        }
        tetris=this;

    }

    private void initUI() {

        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);

        var board = new Board(this,usuario);
        add(board);
        board.start();

        setTitle("Tetris");
        setSize(200, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        GestorPaneles.getInstance().bind( new InterfazGuardar(this.usuario,this.esAdmin));
        this.setVisible(true);
    }
    private void initUICargar(int BOARD_HEIGHT,int BOARD_WIDTH,int PERIOD_INTERVAL,boolean isFallingFinished,boolean isPaused,int numLinesRemoved,int curX,int curY,Shape curPiece,Shape.Tetrominoe[] board){

        statusbar = new JLabel(Integer.toString(numLinesRemoved));
        add(statusbar, BorderLayout.SOUTH);
        var boardc = new Board(this,usuario);
        add(boardc);
        boardc.cargar(isFallingFinished,isPaused,numLinesRemoved,curX,curY,curPiece,board);

        setTitle("Tetris");
        setSize(200, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        GestorPaneles.getInstance().bind( new InterfazGuardar(this.usuario,this.esAdmin));
        this.setVisible(true);
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
        GestorPaneles.getInstance().bind(new Interfaz9("Manuel",true));
    }
    public static void acabar(){tetris.setVisible(false);}
    public static void finalizarPartida(int puntuacion){tetris.setVisible(false);
        GestorPaneles.getInstance().bind(new Interfaz9(tetris.usuario,tetris.esAdmin));
        Sistema.getInstance().acabarPartida(puntuacion,tetris.usuario,1);
    }
    }
