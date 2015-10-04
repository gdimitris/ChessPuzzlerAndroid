package dimitris.chessboardutils.tests;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dimitris.chessboardutils.Board;
import dimitris.chessboardutils.BoardFactory;
import dimitris.chessboardutils.Move;
import dimitris.chessboardutils.MoveFactory;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by dimitris on 9/2/15.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MoveTests {

    private MoveFactory factory;

    @Before
    public void setup() {
        Board board = BoardFactory.createEmptyBoard();
        factory = new MoveFactory(board);
    }

    @Test
    public void testEqualMoves() {
        Move move1 = factory.createMove("e2", "e4");
        Move move2 = factory.createMove("e2", "e4");

        assertTrue(move1.equals(move2));
    }

    @Test
    public void testUnequalMoves() {
        Move move1 = factory.createMove("e2", "e4");
        Move move2 = factory.createMove("d7", "d5");

        assertFalse(move1.equals(move2));
    }

}
