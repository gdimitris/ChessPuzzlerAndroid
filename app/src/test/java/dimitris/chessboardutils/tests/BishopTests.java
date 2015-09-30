package dimitris.chessboardutils.tests;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dimitris.chessboardutils.Bishop;
import dimitris.chessboardutils.Piece;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dimitris on 4/14/15.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class BishopTests {

    private Bishop blackBishop;
    private Bishop whiteBishop;

    @Before
    public void setUp() throws Exception {
        blackBishop = new Bishop(Piece.PieceColor.Black);
        whiteBishop = new Bishop(Piece.PieceColor.White);
    }

    @Test
    public void testFANString() throws Exception {
        assertEquals("♝", blackBishop.getFANString());
        assertEquals("♗", whiteBishop.getFANString());
    }

    @Test
    public void testSANString() throws Exception {
        assertEquals("B", whiteBishop.getSANString());
    }
}