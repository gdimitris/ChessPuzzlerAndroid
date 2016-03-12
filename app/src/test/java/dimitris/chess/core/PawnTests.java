package dimitris.chess.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by dimitris on 4/14/15.
 */

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