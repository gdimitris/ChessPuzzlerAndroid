package dimitris.chessboardutils.tests;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dimitris.chessboardutils.Knight;
import dimitris.chessboardutils.Piece;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dimitris on 4/14/15.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class KnightTests {

    private Knight blackKnight;
    private Knight whiteKnight;

    @Before
    public void setUp() throws Exception {
        blackKnight = new Knight(Piece.PieceColor.Black);
        whiteKnight = new Knight(Piece.PieceColor.White);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("♘",whiteKnight.toString());
        assertEquals("♞",blackKnight.toString());
    }
}