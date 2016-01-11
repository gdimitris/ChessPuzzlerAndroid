package dimitris.chess.core;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;


import static junit.framework.Assert.assertEquals;

/**
 * Created by dimitris on 4/14/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)

public class QueenTests {

    private Queen whiteQueen;
    private Queen blackQueen;

    @Before
    public void setUp() throws Exception {
        whiteQueen = new Queen(Piece.PieceColor.White);
        blackQueen = new Queen(Piece.PieceColor.Black);
    }

    @Test
    public void testFANString() throws Exception {
        assertEquals("♕", whiteQueen.getFANString());
        assertEquals("♛", blackQueen.getFANString());
    }

    @Test
    public void testSANString() throws Exception {
        assertEquals("Q", whiteQueen.getSANString());
    }
}