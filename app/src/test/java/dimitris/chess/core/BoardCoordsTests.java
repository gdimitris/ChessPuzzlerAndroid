package dimitris.chess.core;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by dimitris on 12/29/15.
 */
public class BoardCoordsTests {

    @Test
    public void testGetSquareCoords(){
        BoardCoords coords = BoardCoords.getSquareCoords("a8");
    }

    @Test
    public void testEquals(){
        BoardCoords coords1 = BoardCoords.getSquareCoords("d5");
        BoardCoords coords2 = BoardCoords.getSquareCoords("d5");

        Assert.assertTrue(coords1.equals(coords2));
    }
}
