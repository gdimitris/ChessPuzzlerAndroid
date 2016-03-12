package dimitris.android.chessviews;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;

import com.dimitris.chesspuzzler.BuildConfig;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * Created by dimitris on 1/25/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants= BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class FontLoaderTests {

    private Context context;

    @Before
    public void setup(){
        context = RuntimeEnvironment.application.getApplicationContext();
    }

    @Test
    public void testDefaultFontNotNull(){
        Typeface typeface = FontLoader.loadDefaultFont(context);
        Assert.assertNotNull(typeface);
    }

    @Test
    public void testChessCasesFontLoads(){
        Typeface typeface = FontLoader.loadFont(context,"ChessCases.ttf");
        Assert.assertNotNull(typeface);
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidFontNameThrows(){
        Typeface typeface = FontLoader.loadFont(context, "invalid.file");
    }

}
