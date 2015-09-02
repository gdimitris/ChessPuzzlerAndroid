package dimitris.chessboardutils.tests;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dimitris.chessboardutils.Move;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by dimitris on 9/2/15.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MoveTests {

    @Before
    public void setup() {

    }

    @Test
    public void testEqualMoves() {
        Move move1 = new Move("e2", "e4");
        Move move2 = new Move("e2", "e4");

        assertTrue(move1.equals(move2));
    }

    @Test
    public void testUnequalMoves() {
        Move move1 = new Move("e2", "e4");
        Move move2 = new Move("d7", "d5");

        assertFalse(move1.equals(move2));
    }

}
