package dimitris.chessboardutils.tests;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dimitris.chessboardutils.GameInfo;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by dimitris on 9/24/15.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class GameInfoTests {

    private GameInfo gameInfo;

    @Before
    public void setup(){
    }

    @Test
    public void testInitialState(){
        gameInfo = new GameInfo("w KQkq - 0 1");
        assertTrue(gameInfo.whitePlays);
        assertTrue(gameInfo.whiteCanCastleKingSide);
        assertTrue(gameInfo.whiteCanCastleQueenSide);
        assertNull(gameInfo.enPassantSquare);
        assertEquals(0, gameInfo.halfMoveClock);
        assertEquals(1, gameInfo.fullMoveClock);
    }

    @Test
    public void testWhiteCantCastleKingSide(){
        gameInfo = new GameInfo("w Qkq - 0 1");
        assertFalse(gameInfo.whiteCanCastleKingSide);
        assertTrue(gameInfo.whiteCanCastleQueenSide);
    }

    @Test
    public void testWhiteCantCastleQueenSide(){
        gameInfo = new GameInfo("w Kkq - 0 1");
        assertFalse(gameInfo.whiteCanCastleQueenSide);
        assertTrue(gameInfo.whiteCanCastleKingSide);
    }

    @Test
    public void testBlackCantCastleKingSide(){
        gameInfo = new GameInfo("w KQq - 0 1");
        assertFalse(gameInfo.blackCanCastleKingSide);
        assertTrue(gameInfo.blackCanCastleQueenSide);
    }

    @Test
    public void testBlackCantCastleQueenSide(){
        gameInfo = new GameInfo("w KQk - 0 1");
        assertFalse(gameInfo.blackCanCastleQueenSide);
        assertTrue(gameInfo.blackCanCastleKingSide);
    }

}
