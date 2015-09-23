package com.example.dimitris.chesspuzzler.tests;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;
import com.example.dimitris.chesspuzzler.ChessGame;
import com.example.dimitris.chesspuzzler.ChessGameFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by dimitris on 8/30/15.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class ChessGameFactoryTests {

    @Before
    public void setup() {

    }

    @Test
    public void testCreatesInitialGame() {
        ChessGame game = ChessGameFactory.createInitialPosition();
        assertValidChessGame(game);
    }

    @Test
    public void testCreatesRandomFENPosition(){
        ChessGame game = ChessGameFactory.createFromFEN("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2");
        assertValidChessGame(game);
    }

    private void assertValidChessGame(ChessGame game) {
        assertNotNull(game.board);
        assertNotNull(game.gameInfo);
    }
}
