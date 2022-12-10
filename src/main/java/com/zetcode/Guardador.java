package com.zetcode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;

public class Guardador {
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

    public void setAllGuardador(boolean isFallingFinished,boolean isPaused,int numLinesRemoved,int curX,int curY,Shape curPiece,Shape.Tetrominoe[] board){
        this.isFallingFinished=isFallingFinished;
        this.isPaused=isPaused;
        this.numLinesRemoved=numLinesRemoved;
        this.curX=curX;
        this.curY=curY;
        this.curPiece=curPiece;
        this.board=board;
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
}
