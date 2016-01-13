package dimitris.chess.core;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

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
    private MoveFactory moveFactory;

    @Before
    public void setup(){
        String testFen = "r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0";
        String testSolution = "1. Nf6+ gxf6 2. Bxf7# ";

        puzzle = new ChessPuzzle(testFen, testSolution);
        puzzleProvider = new FakePuzzleProvider(puzzle);
        game = new Game(puzzleProvider);

        Bitboard board = new Bitboard();
        board.setUpFenPosition(testFen);
        moveFactory = new MoveFactory(board);
    }

    @Test
    public void testPuzzleIsSolved(){
        Move move1 = moveFactory.createMove("d5", "f6");
        Move move2 = moveFactory.createMove("g7", "f6");
        Move move3 = moveFactory.createMove("c4", "f7");

        game.start();
        game.playMove(move1);
        game.playMove(move2);
        game.playMove(move3);

        assertTrue(game.puzzleIsSolved());
    }
}
