package dimitris.chess.core;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dimitris on 4/14/15.
 */

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