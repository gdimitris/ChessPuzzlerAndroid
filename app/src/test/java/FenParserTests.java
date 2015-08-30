import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
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
    public void testCreatesInitialGame(){
        FenParser parser = new FenParser();
        ChessGame game = parser.create("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        assertTrue(game.whiteCanCastle);
        assertTrue(game.blackCanCastle);
        assertTrue(game.whitePlays);
        assertEquals(0, game.halfMoveClock);
        assertEquals(1, game.fullMoveClock);

    }

    private class ChessGame {
        public boolean whiteCanCastle = true;
        public boolean blackCanCastle = true;
        public boolean whitePlays = true;
        public int halfMoveClock = 0;
        public int fullMoveClock = 1;
    }

    private class FenParser {
        public ChessGame create(String FEN) {
            return new ChessGame();
        }
    }
}
