package dimitris.android.chessviews;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by dimitris on 10/02/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class SquareTests {

    private Square square;

    @Before
    public void setup(){
        square = new Square(1,2);
    }

    @Test
    public void testCorrectName(){
        square.getName();
        Assert.assertEquals("c7",square.getName());
    }


}
