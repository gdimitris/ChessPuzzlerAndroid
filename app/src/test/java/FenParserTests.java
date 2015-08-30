import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;

/**
 * Created by dimitris on 8/30/15.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class FenParserTests {

    @Before
    public void setup(){

    }

    @Test
    public void testThatItWorks(){
        assertTrue(true);
    }
}
