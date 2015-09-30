package dimitris.chessboardutils.tests;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dimitris.chessboardutils.Piece;
import dimitris.chessboardutils.Rook;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dimitris on 4/14/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)

public class RookTests {

    private Rook whiteRook;
    private Rook blackRook;

    @Before
    public void setUp() throws Exception {
        whiteRook = new Rook(Piece.PieceColor.White);
        blackRook = new Rook(Piece.PieceColor.Black);
    }

    @Test
    public void testFANString() throws Exception {
        assertEquals("♖", whiteRook.getFANString());
        assertEquals("♜", blackRook.getFANString());
    }

    @Test
    public void testSANString() throws Exception {
        assertEquals("R", whiteRook.getSANString());
    }
}