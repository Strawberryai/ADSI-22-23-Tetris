package com.zetcode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Guardador {

    private  int BOARD_WIDTH;
    private  int BOARD_HEIGHT;
    private  int PERIOD_INTERVAL;
    @JsonIgnore
    private Timer timer;

    private boolean isFallingFinished = false;
    private boolean isPaused = false;
    private int numLinesRemoved = 0;
    private int curX = 0;
    private int curY = 0;
    private Shape curPiece;
    private Shape.Tetrominoe[] board;

    public Guardador(){

    }

    public void setAllGuardador(int BOARD_HEIGHT,int BOARD_WIDTH,int PERIOD_INTERVAL, boolean isFallingFinished,boolean isPaused,int numLinesRemoved,int curX,int curY,Shape curPiece,Shape.Tetrominoe[] board){
        this.isFallingFinished=isFallingFinished;
        this.isPaused=isPaused;
        this.numLinesRemoved=numLinesRemoved;
        this.curX=curX;
        this.curY=curY;
        this.curPiece=curPiece;
        this.board=board;
        this.BOARD_HEIGHT=BOARD_HEIGHT;
        this.BOARD_WIDTH=BOARD_WIDTH;
        this.PERIOD_INTERVAL=PERIOD_INTERVAL;
    }

    public void setBOARD_HEIGHT(int BOARD_HEIGHT) {
        this.BOARD_HEIGHT = BOARD_HEIGHT;
    }

    public void setBOARD_WIDTH(int BOARD_WIDTH) {
        this.BOARD_WIDTH = BOARD_WIDTH;
    }

    public void setPERIOD_INTERVAL(int PERIOD_INTERVAL) {
        this.PERIOD_INTERVAL = PERIOD_INTERVAL;
    }
    public int getBOARD_WIDTH(){
        return this.BOARD_WIDTH;
    }

    public int getBOARD_HEIGHT() {
        return BOARD_HEIGHT;
    }

    public int getPERIOD_INTERVAL() {
        return PERIOD_INTERVAL;
    }

    public boolean getIsFallingFinished(){
        return this.isFallingFinished;
    }

    public void setBoard(Shape.Tetrominoe[] board) {
        this.board = board;
    }

    public void setCurPiece(Shape curPiece) {
        this.curPiece = curPiece;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setCurX(int curX) {
        this.curX = curX;
    }

    public void setCurY(int curY) {
        this.curY = curY;
    }

    public void setFallingFinished(boolean fallingFinished) {
        isFallingFinished = fallingFinished;
    }

    public void setNumLinesRemoved(int numLinesRemoved) {
        this.numLinesRemoved = numLinesRemoved;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public int getCurX() {
        return curX;
    }

    public int getCurY() {
        return curY;
    }

    public int getNumLinesRemoved() {
        return numLinesRemoved;
    }

    public Shape getCurPiece() {
        return curPiece;
    }

    public Timer getTimer() {
        return timer;
    }
    public boolean getIsPaused(){
        return this.isPaused;
    }
    public Shape.Tetrominoe[] getBoard() {
        return board;
    }
    public static void guardarPartida(String usuario) throws IOException{
        System.out.println(usuario);
        String path= Paths.get("").toAbsolutePath().toString();
        String directorioGuardados=path+ File.separator+"assets"+ File.separator+"tetris_files";//Miramos si el directorio de todos los guardados esta creado

        if(Files.notExists(Path.of(directorioGuardados))){//si no lo esta se crea para evitar error
            File dir = new File(directorioGuardados);
            dir.mkdirs();
        }

        directorioGuardados=directorioGuardados+File.separator;
        System.out.println(usuario);
        if(Files.notExists(Path.of(directorioGuardados + usuario + "guardado"))){//se mira si el directorio de guardados del usuario esta creado, si no lo esta se crea
            File dir = new File(directorioGuardados+ usuario + "guardado");
            dir.mkdirs();
        }else{
            System.out.println(directorioGuardados + usuario + "guardado");
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        String fecha=formatter.format(date);
        String jsonString=Board.getInstance().guardar();//Se obtiene el json que tiene todos los datos de la partida
        FileWriter file=new FileWriter(directorioGuardados + usuario + "guardado"+File.separator+ fecha);//se mete en un fichero
        file.write(jsonString);
        file.close();
        Tetris.acabar();
    }

    public void eliminarSusPartidas(String usuario) {
        String path= Paths.get("").toAbsolutePath().toString();
        String directorioGuardados=path+ File.separator+"assets"+ File.separator+"tetris_files"+File.separator+ usuario + "guardado";
        System.out.println("Eliminando:"+directorioGuardados);
        File dir=new File(directorioGuardados);
        String[] pathnames=dir.list();
        int i=0;
        directorioGuardados=directorioGuardados+File.separator;
        if(dir.exists()) {
            for (String pathname : pathnames) {
                String file=directorioGuardados+pathname;
                File f=new File(file);
                f.delete();
            }
        }
        File f=new File(directorioGuardados);
        if(f.exists()){
            f.delete();
        }
    }
}
