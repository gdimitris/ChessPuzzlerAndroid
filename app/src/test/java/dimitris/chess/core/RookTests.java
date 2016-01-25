package dimitris.chess.core;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dimitris on 4/14/15.
 */

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