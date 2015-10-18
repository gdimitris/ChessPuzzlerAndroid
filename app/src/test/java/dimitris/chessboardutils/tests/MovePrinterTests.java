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

    private void setupFromFEN(String fen) {
        board = BoardFactory.create(fen);
        factory = new MoveFactory(board);
        movePrinter = new MovePrinter(board);
    }

    @Test
    public void test_print_printsWhitePawnMove(){
        setupInitialBoard();

        Move toPlay = factory.createMove("d2", "d4");
        board.playMove(toPlay);

        String expected = "1. d4";
        assertMovesPlayed(expected);
    }

    @Test
    public void test_print_printsBlackPawnMove(){
        setupInitialBoard();
        Move toPlay = factory.createMove("d7", "d5");
        board.playMove(toPlay);

        String expected = "1... d5";
        assertMovesPlayed(expected);
    }

    @Test
    public void test_print_printsWhitePawnCapture(){
        setupEmptyBoard();
        board.setPieceAtSquare(Piece.create('P'), "e4");
        board.setPieceAtSquare(Piece.create('p'), "d5");


        Move toPlay = factory.createMove("e4","d5");
        board.playMove(toPlay);

        String expected = "1. exd5";

        assertMovesPlayed(expected);
    }

    @Test
    public void test_print_printsWhiteKnightCapture(){
        setupEmptyBoard();
        board.setPieceAtSquare(Piece.create('N'),"f3");
        board.setPieceAtSquare(Piece.create('p'), "g5");
        Move toPlay = factory.createMove("f3","g5");
        board.playMove(toPlay);

        String expected = "1. Nxg5";
        assertMovesPlayed(expected);
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
        assertMovesPlayed(expected);

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
        assertMovesPlayed(expected);
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
        assertMovesPlayed(expected);
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
        assertMovesPlayed(expected);
    }


    @Test
    public void test_print_PuzzleMatein2(){
        setupFromFEN("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R");

        Move move1 = factory.createMove("d5", "f6");
        move1.isCheck = true;
        board.playMove(move1);

        Move move2 = factory.createMove("g7","f6");
        board.playMove(move2);

        Move move3 = factory.createMove("c4","f7");
        move3.isMate = true;
        board.playMove(move3);

        String expected = "1. Nf6+ gxf6 2. Bxf7#";
        assertMovesPlayed(expected);
    }

    @Test
    public void test_print_PuzzleMatein3(){
        setupFromFEN("r3k2r/ppp2Npp/1b5n/4p2b/2B1P2q/BQP2P2/P5PP/RN5K");

        Move move1 = factory.createMove("c4","b5");
        move1.isCheck = true;
        board.playMove(move1);

        Move move2 = factory.createMove("c7","c6");
        board.playMove(move2);

        Move move3 = factory.createMove("b3","e6");
        move3.isCheck = true;
        board.playMove(move3);

        Move move4 = factory.createMove("h4","e7");
        board.playMove(move4);

        Move move5 = factory.createMove("e6","e7");
        move5.isMate = true;
        board.playMove(move5);

        String expected = "1. Bb5+ c6 2. Qe6+ Qe7 3. Qxe7#";
        assertMovesPlayed(expected);
    }

    private void assertMovesPlayed(String expected) {
        String result = movePrinter.printMovesPlayed();
        assertEquals(expected, result);
    }

    @Test
    public void test_print_puzzleMatein4(){
        setupFromFEN("6rk/7p/pp3b2/2pbqP2/5Q2/5R1P/P6P/2B2R1K");

        Move move1 = factory.createMove("e5","e2");
        board.playMove(move1);

        Move move2 = factory.createMove("f1","g1");
        board.playMove(move2);

        Move move3 = factory.createMove("d5","f3");
        move3.isCheck = true;
        board.playMove(move3);

        Move move4 = factory.createMove("f4","f3");
        board.playMove(move4);

        Move move5 = factory.createMove("e2","f3");
        move5.isCheck = true;
        board.playMove(move5);

        Move move6 = factory.createMove("g1", "g2");
        board.playMove(move6);

        Move move7 = factory.createMove("f3","g2");
        move7.isMate = true;
        board.playMove(move7);

        String expected = "1... Qe2 2. Rg1 Bxf3+ 3. Qxf3 Qxf3+ 4. Rg2 Qxg2#";
        assertMovesPlayed(expected);
    }


}
