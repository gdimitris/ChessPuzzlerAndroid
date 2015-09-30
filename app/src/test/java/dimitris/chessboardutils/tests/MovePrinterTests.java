package dimitris.chessboardutils.tests;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dimitris.chessboardutils.Board;
import dimitris.chessboardutils.BoardFactory;
import dimitris.chessboardutils.Move;
import dimitris.chessboardutils.Piece;
import dimitris.chessboardutils.Square;

import static junit.framework.Assert.assertEquals;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MovePrinterTests {

    private MovePrinter movePrinter;
    private Board board;

    @Before
    public void setup(){

    }

    private void setupInitialBoard() {
        board = BoardFactory.createInitialBoard();
        movePrinter = new MovePrinter(board);
    }

    private void setupEmptyBoard(){
        board = BoardFactory.createEmptyBoard();
        movePrinter = new MovePrinter(board);
    }

    @Test
    public void test_print_printsWhitePawnMove(){
        setupInitialBoard();

        Move toPlay = new Move("d2", "d4");

        String expected = "1. d4";
        assertExpectedSingleMove(expected, toPlay);
    }

    @Test
    public void test_print_printsBlackPawnMove(){
        setupInitialBoard();

        Move toPlay = new Move("d7", "d5");
        toPlay.whiteMove = false;

        String expected = "1... d5";
        assertExpectedSingleMove(expected, toPlay);
    }

    @Ignore
    @Test
    public void test_print_printsWhitePawnCapture(){
        setupEmptyBoard();
        board.setPieceAtSquare(Piece.create('P'), "e4");
        board.setPieceAtSquare(Piece.create('p'), "d5");

        Move toPlay = new Move("e4","d5");
        String expected = "1. exd5";

        assertExpectedSingleMove(expected, toPlay);
    }

    @Test
    public void test_print_printsWhiteKnightCapture(){
        setupEmptyBoard();
        board.setPieceAtSquare(Piece.create('N'),"f3");
        board.setPieceAtSquare(Piece.create('p'),"g5");
        Move toPlay = new Move("f3","g5");
        toPlay.whiteMove = true;
        toPlay.isCapture = true;

        String expected = "1. Nxg5";
        assertExpectedSingleMove(expected, toPlay);
    }


    private void assertExpectedSingleMove(String expected, Move toPlay) {
        String result = movePrinter.print(toPlay);

        assertEquals(expected, result);
    }


    private class MovePrinter {

        public static final String PERIOD = ".";
        public static final String ELLIPSIS = "...";
        public static final String MOVE_FORMAT = "%d%s %s%s%s";
        private Board board;
        private int fullMoveCounter;

        public MovePrinter(Board board){
            this.board = board;
            this.fullMoveCounter = 1;
        }

        public String print(Move moveToPrint){
            Square src = board.getSquareAt(moveToPrint.sourceSquare);
            Square dest = board.getSquareAt(moveToPrint.destinationSquare);
            String moveColor = moveToPrint.whiteMove ? PERIOD : ELLIPSIS;
            String capture = moveToPrint.isCapture ? "x" : "";

            String printFormat = String.format(MOVE_FORMAT,fullMoveCounter,moveColor, src.piece.getSANString(),capture, dest.toString());

            return String.format(printFormat, fullMoveCounter,src.piece.getSANString(),dest.toString());
        }
    }
}
