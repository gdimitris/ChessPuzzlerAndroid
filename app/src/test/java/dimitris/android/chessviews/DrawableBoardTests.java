package dimitris.android.chessviews;

import android.content.Context;
import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import dimitris.android.chessviews.Pieces.King;
import dimitris.android.chessviews.Pieces.Rook;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by dimitris on 25/02/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class DrawableBoardTests {

    public static final String EXAMPLE_FEN_POSITION = "4kb1r/p2n1ppp/4q3/4p1B1/4P3/1Q6/PPP2PPP/2KR4";
    private DrawableBoard drawableBoard;

    @Before
    public void setup(){
        Context context = RuntimeEnvironment.application.getApplicationContext();
        drawableBoard = new DrawableBoard(context);
    }

    @Test
    public void testEmptyBoard(){
        assertTrue(drawableBoard.boardIsEmpty());
    }

    @Test
    public void testEmptyBoardHasEmptySquares(){
        Square randomSquare = drawableBoard.getSquareAt(2,3);

        assertTrue(drawableBoard.squareIsEmpty(randomSquare));
    }

    @Test
    public void testGetSquare(){
        Square square = drawableBoard.getSquareAt(0,0);

        assertEquals("a8", square.getName());
    }

    @Test
    public void testSetupsFenCorrectly(){
        drawableBoard.setPosition(EXAMPLE_FEN_POSITION);

        Square square = drawableBoard.getSquareAt(0,4);
        assertPieceAtSquare(King.class,square);

        square = drawableBoard.getSquareAt(0,7);
        assertPieceAtSquare(Rook.class, square);
    }

    private void assertPieceAtSquare(Class expectedClass, Square square) {
        assertEquals(expectedClass,square.getPiece().getClass());
    }

    @Test
    public void testSquareSizeIsCorrectlyPropagatedToSquaresAndPieces(){
        drawableBoard.setPosition(EXAMPLE_FEN_POSITION);

        int expectedSize = 12;
        drawableBoard.setSquareSize(expectedSize);
        assertEquals(expectedSize, drawableBoard.getSquareSize());

        Square square = drawableBoard.getSquareAt(0,4);
        assertEquals(expectedSize,square.getRect().height());
        assertEquals(expectedSize, square.getPiece().getDrawSize());
    }

    @Test
    public void testDoMoveUpdatesPlayedMovesList(){
        drawableBoard.setPosition(EXAMPLE_FEN_POSITION);

        UIMove move = createMoveKxe8();
        drawableBoard.doMove(move);

        assertEquals(1, drawableBoard.getPlayedMoves().size());
    }

    private UIMove createMoveKxe8() {
        Square src = drawableBoard.getSquareAt(0,4);
        Square dest = drawableBoard.getSquareAt(0,5);

        return new UIMove(src,dest);
    }

    @Test
    public void testUndoneMoveIsRemovedFromPlayedMoves(){
        drawableBoard.setPosition(EXAMPLE_FEN_POSITION);

        UIMove move = createMoveKxe8();
        drawableBoard.doMove(move);
        drawableBoard.undoMove();

        assertEquals(0, drawableBoard.getPlayedMoves().size());
    }

}