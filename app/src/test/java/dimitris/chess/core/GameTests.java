package dimitris.chess.core;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by dimitris on 1/1/16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class GameTests {

    private Game game;
    private PuzzleProvider puzzleProvider;
    private ChessPuzzle puzzle;

    @Before
    public void setup(){
        String testFen = "r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0";
        String testSolution = "1. Nf6+ gxf6 2. Bxf7# ";
        puzzle = new ChessPuzzle(testFen, testSolution);
        puzzleProvider = new FakePuzzleProvider(puzzle);
        game = new Game(puzzleProvider);
    }

    @Test
    public void testPuzzleIsSolved(){
        game.start();
        game.playMove("d5", "f6");
        game.playMove("g7", "f6");
        game.playMove("c4", "f7");

        assertTrue(game.puzzleIsSolved());
    }

    @Test
    public void testPuzzleIsNotSolved(){
        game.start();
        game.playMove("d5","f6");
        game.playMove("g7","f6");

        assertFalse(game.puzzleIsSolved());
    }

    @Test
    public void testPlayedMovesAreCorrect(){
        game.start();
        game.playMove("d5","f6");
        game.playMove("g7","f6");

        assertTrue(game.playedMovesAreCorrect());
    }
}
