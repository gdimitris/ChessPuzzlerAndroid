package dimitris.chess.core;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dimitris on 4/14/15.
 */
public class NullPieceTests {

    private Piece nullPiece;

    @Before
    public void setUp() throws Exception {
        nullPiece = PieceFactory.createNullPiece();
    }

    @Test
    public void testFANString() throws Exception {
        assertEquals(".", nullPiece.getFANString());
    }

    @Test
    public void testSANString() throws Exception {
        assertEquals(".", nullPiece.getSANString());
    }
}