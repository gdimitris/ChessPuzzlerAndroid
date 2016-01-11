package dimitris.chess.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dimitris on 1/1/16.
 */
public class GameTests {

    private Game game;
    private PuzzleProvider puzzleProvider;
    private ChessPuzzle puzzle;

    @Before
    public void setup(){
        puzzle = new ChessPuzzle("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0","1. Nf6+ gxf6 2. Bxf7# ");
        puzzleProvider = new FakePuzzleProvider(puzzle);
        game = new Game(puzzleProvider);
    }

    @Test
    public void testPuzzleIsSolved(){
        Move move1 = new Move("d5", "f6");
        Move move2 = new Move("g7", "f6");
        Move move3 = new Move("c4", "f7");

        game.playMove(move1);
        game.playMove(move2);
        game.playMove(move3);

        assertTrue(game.puzzleIsSolved());
    }
}
