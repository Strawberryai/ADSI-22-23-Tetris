package eus.ehu.lsi.adsi;

import com.zetcode.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NivelTest {
    int nivel;
    Board board;
    //Guardador gBoard;
    String usuario;




    @Before
    public void setUp(){
        nivel = 0;
        usuario = "Manuel";
        //gBoard.setAllGuardador(10, 22, 300, false, false, 0, 0, 0, null, null, "Manuel", true);
        var game = new Tetris(10, 22, 300, false, false, false, 0, 0, 0, null, null, "Manuel", true);
        board = new Board(game, usuario);
    }
    @Test
    public void test1(){
        //board.modificarBoard(usuario, false, 1);
        board.modificarBoardSet(1);
        GestorNivel.getInstance().actualizarNivel(usuario, false, 1 );
        assertEquals(board.getBOARD_HEIGHT(),10);
        assertEquals(board.getBOARD_WIDTH(),22);
        assertEquals(board.getPERIOD_INTERVAL(),300);
        board.modificarBoardSet(3);
        assertNotEquals(board.getBOARD_HEIGHT(),10);
        assertNotEquals(board.getBOARD_WIDTH(),22);
        assertNotEquals(board.getPERIOD_INTERVAL(),00);
    }
}
