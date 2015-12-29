package dimitris.chess.core;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by dimitris on 12/29/15.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
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
