package dimitris.chess.core;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by dimitris on 4/3/16.
 */

public class ChessPuzzleTests {

    private ChessPuzzle whitePuzzle;
    private ChessPuzzle blackPuzzle;

    @Before
    public void setUp(){
        whitePuzzle = new ChessPuzzle.ChessPuzzleBuilder("1","r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0","1. Nf6+ gxf6 2. Bxf7# ").build();
        blackPuzzle = new ChessPuzzle.ChessPuzzleBuilder("2","7r/p3ppk1/3p4/2p1P1Kp/2Pb4/3P1QPq/PP5P/R6R b - - 0 1","1... Be3+ 2. Qxe3 Qg4# ").build();
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
