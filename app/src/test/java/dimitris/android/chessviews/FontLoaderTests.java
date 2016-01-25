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
 * Created by dimitris on 1/25/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants= BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class FontLoaderTests {
    @Before
    public void setup(){

    }

    @Test
    public void testItWorks(){
        Assert.assertTrue(true);
    }
}
