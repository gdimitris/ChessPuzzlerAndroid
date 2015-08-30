package dimitris.chessboardutils.tests;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dimitris.chessboardutils.King;
import dimitris.chessboardutils.Piece;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dimitris on 4/14/15.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class KingTests{

    private King whiteKing;
    private King blackKing;

    @Before
    public void setUp() throws Exception {
        whiteKing = new King(Piece.PieceColor.White);
        blackKing = new King(Piece.PieceColor.Black);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("♔",whiteKing.toString());
        assertEquals("♚",blackKing.toString());
    }
}