package dimitris.android.chessviews;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;

import com.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import dimitris.android.chessviews.Pieces.Piece;
import dimitris.android.chessviews.Pieces.WhitePieceFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by dimitris on 10/02/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class SquareTests {

    private Piece whiteKnight;
    private Square square;
    private static int squareRow = 1;
    private static int squareCol = 2;

    @Before
    public void setup(){
        Context context = RuntimeEnvironment.application.getApplicationContext();
        Typeface typeface = FontLoader.loadDefaultFont(context);
        WhitePieceFactory factory = new WhitePieceFactory(typeface,30);
        whiteKnight = factory.createKnight();
        square = new Square(squareRow,squareCol);
    }

    @Test
    public void testCorrectName(){
        square.getName();
        assertEquals("c7",square.getName());
    }

    @Test
    public void testClear(){
        square.setPiece(whiteKnight);
        assertNotNull(square.getPiece());
        square.clear();
        assertNull(square.getPiece());
    }


    @Test
    public void testSquareRect(){
        int size = 20;
        square.setSize(size);
        Rect expected = new Rect(squareCol * size, squareRow * size, (squareCol + 1) * size, (squareRow + 1) * size);
        Rect actual = square.getRect();

        assertEquals(expected,actual);
    }



}
