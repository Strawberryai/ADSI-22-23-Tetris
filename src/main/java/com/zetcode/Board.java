package com.zetcode;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visual.GestorPaneles;
import com.visual.funcionalidad1.Interfaz9;
import com.visual.funcionalidad3.Sonido;
import com.zetcode.Shape.Tetrominoe;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Board extends JPanel {
    private String usuario;
    private static Board miPartida;
    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 22;
    private final int PERIOD_INTERVAL = 300;

    private Timer timer;
    private boolean isFallingFinished = false;
    private boolean isPaused = false;
    private int numLinesRemoved = 0;
    private int curX = 0;
    private int curY = 0;
    private JLabel statusbar;
    private Shape curPiece;
    private Tetrominoe[] board;

    public static Board getInstance(){
        return Board.miPartida;
    }
    public Board(Tetris parent,String usuario) {
        miPartida=this;
        miPartida.usuario=usuario;
        initBoard(parent);
    }
    public static String guardar() throws IOException {
        ObjectMapper mapper= new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Guardador guardador= new Guardador();
        guardador.setAllGuardador(miPartida.BOARD_HEIGHT,miPartida.BOARD_WIDTH, miPartida.PERIOD_INTERVAL, miPartida.isFallingFinished,miPartida.isPaused,miPartida.numLinesRemoved,miPartida.curX,miPartida.curY,miPartida.curPiece,miPartida.board);
        return (mapper.writeValueAsString(guardador));
    }
    public  void cargar(boolean isFallingFinished,boolean isPaused,int numLinesRemoved,int curX,int curY,Shape curPiece,Shape.Tetrominoe[] board){
        this.isFallingFinished=isFallingFinished;
        this.isPaused=isPaused;
        this.numLinesRemoved=numLinesRemoved;
        this.curX=curX;
        this.curY=curY;
        this.curPiece=curPiece;
        this.board=board;
        timer = new Timer(PERIOD_INTERVAL, new GameCycle());
        timer.start();
    }
    private void initBoard(Tetris parent) {

        setFocusable(true);
        statusbar = parent.getStatusBar();
        addKeyListener(new TAdapter());
    }

    private int squareWidth() {

        return (int) getSize().getWidth() / BOARD_WIDTH;
    }

    private int squareHeight() {

        return (int) getSize().getHeight() / BOARD_HEIGHT;
    }

    private Tetrominoe shapeAt(int x, int y) {

        return board[(y * BOARD_WIDTH) + x];
    }

    void start() {

        curPiece = new Shape();
        board = new Tetrominoe[BOARD_WIDTH * BOARD_HEIGHT];

        clearBoard();
        newPiece();

        timer = new Timer(PERIOD_INTERVAL, new GameCycle());
        timer.start();
    }

    private void pause() {

        isPaused = !isPaused;

        if (isPaused) {

            statusbar.setText("paused");
        } else {

            statusbar.setText(String.valueOf(numLinesRemoved));
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        var size = getSize();
        int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight();

        for (int i = 0; i < BOARD_HEIGHT; i++) {

            for (int j = 0; j < BOARD_WIDTH; j++) {

                Tetrominoe shape = shapeAt(j, BOARD_HEIGHT - i - 1);

                if (shape != Tetrominoe.NoShape) {

                    drawSquare(g, j * squareWidth(),
                            boardTop + i * squareHeight(), shape);
                }
            }
        }

        if (curPiece.getShape() != Tetrominoe.NoShape) {

            for (int i = 0; i < 4; i++) {

                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);

                drawSquare(g, x * squareWidth(),
                        boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(),
                        curPiece.getShape());
            }
        }
    }

    private void dropDown() {

        int newY = curY;

        while (newY > 0) {

            if (!tryMove(curPiece, curX, newY - 1)) {

                break;
            }

            newY--;
        }

        pieceDropped();
    }

    private void oneLineDown() {

        if (!tryMove(curPiece, curX, curY - 1)) {

            pieceDropped();
        }
    }

    private void clearBoard() {

        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++) {

            board[i] = Tetrominoe.NoShape;
        }
    }

    private void pieceDropped() {

        for (int i = 0; i < 4; i++) {

            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * BOARD_WIDTH) + x] = curPiece.getShape();
        }

        removeFullLines();

        if (!isFallingFinished) {

            newPiece();
        }
    }

    private void newPiece() {

        curPiece.setRandomShape();
        curX = BOARD_WIDTH / 2 + 1;
        curY = BOARD_HEIGHT - 1 + curPiece.minY();

        if (!tryMove(curPiece, curX, curY)) {
            //TODO: añadir sonido de gameover
            Sonido.getMiSonido().pararSonido();
            Sonido.getMiSonido().ReproducirSonido("/audios/gameOver.wav");
            Usuario usu = GestorUsuarios.getInstance().buscarUsuario(usuario);
            String musica = usu.getConfig().getSonido();
            if(musica.equals("Positiva")){
                com.visual.funcionalidad3.Sonido.getMiSonido().reproducirSondoEnLoop("/audios/positivaC.wav");
            } else if (musica.equals("Intriga")) {
                com.visual.funcionalidad3.Sonido.getMiSonido().reproducirSondoEnLoop("/audios/intrigaC.wav");
            }else if (musica.equals("Epico")) {
                com.visual.funcionalidad3.Sonido.getMiSonido().reproducirSondoEnLoop("/audios/epica.wav");
            }else if (musica.equals("Relajante")) {
                com.visual.funcionalidad3.Sonido.getMiSonido().reproducirSondoEnLoop("/audios/relajanteC.wav");
            }
            curPiece.setShape(Tetrominoe.NoShape);
            timer.stop();

            var msg = String.format("Game over. Score: %d", numLinesRemoved);
            statusbar.setText(msg);

            Tetris.finalizarPartida(numLinesRemoved);

        }
    }

    private boolean tryMove(Shape newPiece, int newX, int newY) {

        for (int i = 0; i < 4; i++) {

            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);

            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {

                return false;
            }

            if (shapeAt(x, y) != Tetrominoe.NoShape) {

                return false;
            }
        }

        curPiece = newPiece;
        curX = newX;
        curY = newY;

        repaint();

        return true;
    }

    private void removeFullLines() {

        int numFullLines = 0;

        for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {

            boolean lineIsFull = true;

            for (int j = 0; j < BOARD_WIDTH; j++) {

                if (shapeAt(j, i) == Tetrominoe.NoShape) {

                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {

                numFullLines++;

                for (int k = i; k < BOARD_HEIGHT - 1; k++) {
                    for (int j = 0; j < BOARD_WIDTH; j++) {
                        board[(k * BOARD_WIDTH) + j] = shapeAt(j, k + 1);
                    }
                }
            }
        }

        if (numFullLines > 0) {

            numLinesRemoved += numFullLines;

            statusbar.setText(String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            curPiece.setShape(Tetrominoe.NoShape);
        }
    }

    private void drawSquare(Graphics g, int x, int y, Tetrominoe shape) {

        Usuario activo =GestorUsuarios.getInstance().buscarUsuario(usuario);
        String ladrillo = activo.getConfig().getLadrillo();
        Color color= new Color(0,0,0);
        if(ladrillo.equals("predeterminado")){
            Color colors[] = {new Color(0, 0, 0), new Color(204, 102, 102),
                    new Color(102, 204, 102), new Color(102, 102, 204),
                    new Color(204, 204, 102), new Color(204, 102, 204),
                    new Color(102, 204, 204), new Color(218, 170, 0),
            };

            color = colors[shape.ordinal()];
        } else if (ladrillo.equals("rojo")) {
            color= new Color(255, 0, 0);
        }else if (ladrillo.equals("verde")) {
            color= new Color(0, 204, 0);
        }else if (ladrillo.equals("azul")) {
            color= new Color(0, 0, 255);
        }else if (ladrillo.equals("amarillo")) {
            color= new Color(255, 255, 0);
        }else if (ladrillo.equals("naranja")) {
            color= new Color(255, 100, 0);
        }



        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + 1);
    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    private void doGameCycle() {

        update();
        repaint();
    }

    private void update() {

        if (isPaused) {

            return;
        }

        if (isFallingFinished) {

            isFallingFinished = false;
            newPiece();
        } else {

            oneLineDown();
        }
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (curPiece.getShape() == Tetrominoe.NoShape) {

                return;
            }

            int keycode = e.getKeyCode();

            // Java 12 switch expressions
            switch (keycode) {

                case KeyEvent.VK_P -> pause();
                case KeyEvent.VK_LEFT -> tryMove(curPiece, curX - 1, curY);
                case KeyEvent.VK_RIGHT -> tryMove(curPiece, curX + 1, curY);
                case KeyEvent.VK_DOWN -> tryMove(curPiece.rotateRight(), curX, curY);
                case KeyEvent.VK_UP -> tryMove(curPiece.rotateLeft(), curX, curY);
                case KeyEvent.VK_SPACE -> dropDown();
                case KeyEvent.VK_D -> oneLineDown();
            }
        }
    }
}