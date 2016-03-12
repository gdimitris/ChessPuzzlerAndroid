package dimitris.android.app;

import android.os.Build;

import com.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by dimitris on 07/03/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class PuzzleGatewayTests {

    @Before
    public void setup(){

    }

    @Test
    public void testAddsPuzzleInDB(){

    }

}
