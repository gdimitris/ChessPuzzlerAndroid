package dimitris.chess.core;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dimitris on 4/14/15.
 */
public class KingTests {

    private King whiteKing;
    private King blackKing;

    @Before
    public void setUp() throws Exception {
        whiteKing = new King(Piece.PieceColor.White);
        blackKing = new King(Piece.PieceColor.Black);
    }

    @Test
    public void testFANString() throws Exception {
        assertEquals("♔", whiteKing.getFANString());
        assertEquals("♚", blackKing.getFANString());
    }

    @Test
    public void testSANString() throws Exception {
        assertEquals("K", whiteKing.getSANString());
    }
}