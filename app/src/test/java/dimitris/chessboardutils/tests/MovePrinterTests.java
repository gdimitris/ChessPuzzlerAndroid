package dimitris.chessboardutils.tests;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dimitris.chessboardutils.Board;
import dimitris.chessboardutils.BoardFactory;
import dimitris.chessboardutils.Move;
import dimitris.chessboardutils.MoveFactory;
import dimitris.chessboardutils.MovePrinter;
import dimitris.chessboardutils.Piece;

import static junit.framework.Assert.assertEquals;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MovePrinterTests {

    private MovePrinter movePrinter;
    private Board board;
    private MoveFactory factory;

    @Before
    public void setup(){

    }

    private void setupInitialBoard() {
        board = BoardFactory.createInitialBoard();
        movePrinter = new MovePrinter(board);
        factory = new MoveFactory(board);
    }

    private void setupEmptyBoard(){
        board = BoardFactory.createEmptyBoard();
        movePrinter = new MovePrinter(board);
        factory = new MoveFactory(board);
    }

    @Test
    public void test_print_printsWhitePawnMove(){
        setupInitialBoard();

        Move toPlay = factory.createMove("d2", "d4");
        board.playMove(toPlay);

        String expected = "1. d4";
        assertExpectedSingleMove(expected);
    }

    @Test
    public void test_print_printsBlackPawnMove(){
        setupInitialBoard();
        Move toPlay = factory.createMove("d7", "d5");
        board.playMove(toPlay);

        String expected = "1... d5";
        assertExpectedSingleMove(expected);
    }

    @Test
    public void test_print_printsWhitePawnCapture(){
        setupEmptyBoard();
        board.setPieceAtSquare(Piece.create('P'), "e4");
        board.setPieceAtSquare(Piece.create('p'), "d5");

        Move toPlay = factory.createMove("e4","d5");
        board.playMove(toPlay);
        String expected = "1. exd5";

        assertExpectedSingleMove(expected);
    }

    @Test
    public void test_print_printsWhiteKnightCapture(){
        setupEmptyBoard();
        board.setPieceAtSquare(Piece.create('N'),"f3");
        board.setPieceAtSquare(Piece.create('p'), "g5");
        Move toPlay = factory.createMove("f3","g5");
        board.playMove(toPlay);

        String expected = "1. Nxg5";
        assertExpectedSingleMove(expected);
    }

    @Test
    public void test_print_printsCheckMate(){
        setupEmptyBoard();
        board.setPieceAtSquare(Piece.create('k'), "e8");
        board.setPieceAtSquare(Piece.create('R'),"a7");
        board.setPieceAtSquare(Piece.create('Q'),"c1");
        Move toPlay = factory.createMove("c1", "c8");
        toPlay.isMate = true;
        board.playMove(toPlay);

        String expected = "1. Qc8#";
        assertExpectedSingleMove(expected);

    }

    @Test
    public void test_print_printsCheckMove(){
        setupEmptyBoard();
        board.setPieceAtSquare(Piece.create('k'), "e8");
        board.setPieceAtSquare(Piece.create('Q'), "d1");
        Move toPlay = factory.createMove("d1","e1");
        toPlay.isCheck = true;
        board.playMove(toPlay);

        String expected = "1. Qe1+";
        assertExpectedSingleMove(expected);
    }

    @Test
    public void test_print_printsMultipleMoves(){
        setupInitialBoard();
        Move move1 = factory.createMove("d2","d4");
        board.playMove(move1);
        Move move2 = factory.createMove("d7","d5");
        board.playMove(move2);
        Move move3 = factory.createMove("c2","c4");
        board.playMove(move3);
        Move move4 = factory.createMove("d5","c4");
        move4.isCapture = true;
        board.playMove(move4);
        Move move5 = factory.createMove("b1","c3");
        board.playMove(move5);


        String expected = "1. d4 d5 2. c4 dxc4 3. Nc3";
        String result = movePrinter.printMovesPlayed();
        assertEquals(expected, result);
    }

    @Test
    public void test_print_handlesBlackFirstToMove(){
        setupInitialBoard();

        Move move1 = factory.createMove("d7","d5");
        board.playMove(move1);
        Move move2 = factory.createMove("d2","d4");
        board.playMove(move2);
        Move move3 = factory.createMove("e7", "e5");
        board.playMove(move3);
        Move move4 = factory.createMove("b1", "c3");
        board.playMove(move4);

        String expected = "1... d5 2. d4 e5 3. Nc3";
        String result = movePrinter.printMovesPlayed();

        assertEquals(expected, result);
    }


    @Test
    public void test_print_PuzzleMatein2(){
        board = BoardFactory.create("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R");
        factory = new MoveFactory(board);
        movePrinter = new MovePrinter(board);


        Move move1 = factory.createMove("d5", "f6");
        move1.isCheck = true;
        board.playMove(move1);
        Move move2 = factory.createMove("g7","f6");
        board.playMove(move2);
        Move move3 = factory.createMove("c4","f7");
        move3.isMate = true;
        board.playMove(move3);

        String expected = "1. Nf6+ gxf6 2. Bxf7#";
        String result = movePrinter.printMovesPlayed();
        assertEquals(expected, result);
    }

    private void assertExpectedSingleMove(String expected) {
        String result = movePrinter.printMovesPlayed();

        assertEquals(expected, result);
    }



}
