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
import dimitris.chessboardutils.Piece;
import dimitris.chessboardutils.Square;

import static dimitris.chessboardutils.Piece.PieceType.*;
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
        movePrinter = new MovePrinter();
        factory = new MoveFactory(board);
    }

    private void setupEmptyBoard(){
        board = BoardFactory.createEmptyBoard();
        movePrinter = new MovePrinter();
        factory = new MoveFactory(board);
    }

    @Test
    public void test_print_printsWhitePawnMove(){
        setupInitialBoard();

        Move toPlay = factory.createMove("d2", "d4");

        String expected = "1. d4";
        assertExpectedSingleMove(expected, toPlay);
    }

    @Test
    public void test_print_printsBlackPawnMove(){
        setupInitialBoard();
        Move toPlay = factory.createMove("d7", "d5");

        String expected = "1... d5";
        assertExpectedSingleMove(expected, toPlay);
    }

    @Test
    public void test_print_printsWhitePawnCapture(){
        setupEmptyBoard();
        board.setPieceAtSquare(Piece.create('P'), "e4");
        board.setPieceAtSquare(Piece.create('p'), "d5");

        Move toPlay = factory.createMove("e4","d5");
        String expected = "1. exd5";

        assertExpectedSingleMove(expected, toPlay);
    }

    @Test
    public void test_print_printsWhiteKnightCapture(){
        setupEmptyBoard();
        board.setPieceAtSquare(Piece.create('N'),"f3");
        board.setPieceAtSquare(Piece.create('p'),"g5");
        Move toPlay = factory.createMove("f3","g5");

        String expected = "1. Nxg5";
        assertExpectedSingleMove(expected, toPlay);
    }

    @Test
    public void test_print_printsCheckMove(){
        setupEmptyBoard();
        board.setPieceAtSquare(Piece.create('k'), "e8");
        board.setPieceAtSquare(Piece.create('Q'), "d1");
        Move toPlay = factory.createMove("d1","e1");
        toPlay.isCheck = true;

        String expected = "1. Qe1+";
        assertExpectedSingleMove(expected, toPlay);
    }


    private void assertExpectedSingleMove(String expected, Move toPlay) {
        String result = movePrinter.print(toPlay);

        assertEquals(expected, result);
    }


    private class MovePrinter {

        public static final String PERIOD = ".";
        public static final String ELLIPSIS = "...";
        public static final String MOVE_FORMAT = "%d%s %s%s%s%s";
        private int fullMoveCounter;

        public MovePrinter(){
            this.fullMoveCounter = 1;
        }

        public String print(Move moveToPrint){
            Square src = moveToPrint.sourceSquare;
            Square dest = moveToPrint.destinationSquare;
            String moveDecorator = moveToPrint.whiteMove ? PERIOD : ELLIPSIS;
            String capture = moveToPrint.isCapture ? "x" : "";
            String check = moveToPrint.isCheck ? "+" : "";
            String movePrefix = src.piece.type == Pawn && moveToPrint.isCapture ? src.getColumn() : src.piece.getSANString();

            String printFormat = String.format(MOVE_FORMAT,fullMoveCounter,moveDecorator, movePrefix,capture, dest.toString(), check);

            return String.format(printFormat, fullMoveCounter,src.piece.getSANString(),dest.toString());
        }
    }
}
