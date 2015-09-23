package com.example.dimitris.chesspuzzler.tests;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dimitris.chessboardutils.Board;
import dimitris.chessboardutils.BoardFactory;
import dimitris.chessboardutils.GameInfo;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by dimitris on 8/30/15.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class FenParserTests {

    @Before
    public void setup() {

    }

    @Test
    public void testCreatesInitialGame() {
        FenParser parser = new FenParser();
        ChessGame game = parser.create("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        assertNotNull(game.board);
        assertNotNull(game.gameInfo);
    }

    private class ChessGame {
        public Board board;
        public GameInfo gameInfo;
    }



    private class FenParser {
        public ChessGame create(String FEN) {
            String[] tokens = FEN.split(" ",2);
            String boardRepresentation = tokens[0];
            String gameInfo = tokens[1];

            ChessGame game = new ChessGame();
            game.board = BoardFactory.create(boardRepresentation);
            game.gameInfo = new GameInfo(gameInfo);
            return game;
        }
    }
}
