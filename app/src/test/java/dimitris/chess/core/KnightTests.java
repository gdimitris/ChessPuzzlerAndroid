package dimitris.chess.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by dimitris on 4/14/15.
 */
public class KnightTests {

    private Knight blackKnight;
    private Knight whiteKnight;

    @Before
    public void setUp() throws Exception {
        blackKnight = new Knight(Piece.PieceColor.Black);
        whiteKnight = new Knight(Piece.PieceColor.White);
    }

    @Test
    public void testFANString() throws Exception {
        assertEquals("♘", whiteKnight.getFANString());
        assertEquals("♞", blackKnight.getFANString());
    }

    @Test
    public void testSANString() throws Exception {
        assertEquals("N", whiteKnight.getSANString());
    }
}