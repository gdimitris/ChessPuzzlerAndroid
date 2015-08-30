package dimitris.chessboardutils.tests;

import org.junit.Before;
import org.junit.Test;

import dimitris.chessboardutils.Board;

/**
 * Created by dimitris on 8/30/15.
 */
public class BoardFactoryTests {

    @Before
    public void setup(){

    }

    @Test
    public void testCreatesInitialPositionFromFEN(){
        Board board = BoardFactory.create("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR");

    }

    private static class BoardFactory {
        public static Board create(String FEN) {

            return null;
        }
    }
}
