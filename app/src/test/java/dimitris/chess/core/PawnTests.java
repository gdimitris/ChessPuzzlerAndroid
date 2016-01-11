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

public class PawnTests {

    private Pawn whitePawn;
    private Pawn blackPawn;

    @Before
    public void setUp() throws Exception {
        whitePawn = new Pawn(Piece.PieceColor.White);
        blackPawn = new Pawn(Piece.PieceColor.Black);
    }

    @Test
    public void testFANString() throws Exception {
        assertEquals("♙", whitePawn.getFANString());
        assertEquals("♟", blackPawn.getFANString());
    }

    @Test
    public void testSANString() throws Exception {
        assertEquals("", whitePawn.getSANString());
    }
}