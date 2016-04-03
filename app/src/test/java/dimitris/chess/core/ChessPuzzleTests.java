package dimitris.chess.core;

import com.dimitris.chesspuzzler.BuildConfig;

import junit.framework.Assert;

import org.apache.maven.model.Build;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by dimitris on 4/3/16.
 */

public class ChessPuzzleTests {

    private ChessPuzzle whitePuzzle;
    private ChessPuzzle blackPuzzle;

    @Before
    public void setUp(){
        whitePuzzle = new ChessPuzzle("1","test","r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0","1. Nf6+ gxf6 2. Bxf7# ");
        blackPuzzle = new ChessPuzzle("2","test","7r/p3ppk1/3p4/2p1P1Kp/2Pb4/3P1QPq/PP5P/R6R b - - 0 1","1... Be3+ 2. Qxe3 Qg4# ");
    }

    @Test
    public void testWhitePuzzle(){
        Assert.assertTrue(whitePuzzle.isWhitePuzzle());
    }

    @Test
    public void testBlackPuzzle(){
        Assert.assertFalse(blackPuzzle.isWhitePuzzle());
    }

}
