package dimitris.chess.core;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by dimitris on 1/1/16.
 */
public class GameTests {

    private Game game;
    private PuzzleProvider puzzleProvider;
    private ChessPuzzle puzzle;
    private FakeGameEventsListener fakeListener;

    @Before
    public void setup(){
        String testFen = "r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0";
        String testSolution = "1. Nf6+ gxf6 2. Bxf7# ";
        puzzle = new ChessPuzzle(testFen, testSolution);
        puzzleProvider = new FakePuzzleProvider(puzzle);
        game = new Game(puzzleProvider);
        fakeListener = new FakeGameEventsListener(game);
    }

    @Test
    public void testPuzzleIsSolved(){
        game.start();
        game.doMove("d5", "f6");
        game.doMove("g7", "f6");
        game.doMove("c4", "f7");

        assertTrue(game.puzzleIsSolved());
        assertTrue(fakeListener.onPuzzleGameSolvedCalled);
    }

    @Test
    public void testPuzzleIsNotSolved(){
        game.start();
        game.doMove("d5","f6");
        game.doMove("g7","f6");

        assertFalse(game.puzzleIsSolved());
        assertFalse(fakeListener.onPuzzleGameSolvedCalled);
    }

    @Test
    public void testPlayedMovesAreCorrect(){
        game.start();
        game.doMove("d5","f6");
        game.doMove("g7","f6");

        assertTrue(game.playedMovesAreCorrect());
    }

    @Test
    public void testMoveUndo(){
        game.start();
        game.doMove("d5","f6");
        assertTrue(fakeListener.onMoveDoCalled);

        assertFalse(fakeListener.onMoveUndoCalled);
        game.undoMove();
        assertTrue(fakeListener.onMoveUndoCalled);
    }

    @Test
    public void testAfterQuitDoMoveShouldNotBeCalled(){
        game.start();
        assertTrue(fakeListener.onPuzzleGameStartCalled);
        game.quit();
        assertTrue(fakeListener.onPuzzleGameQuitCalled);
        game.doMove("d5","f6");
        assertFalse(fakeListener.onMoveDoCalled);

    }

    @Test
    public void testIncorrectMovesShouldBeUndone(){
        game.start();
        assertFalse(fakeListener.onMoveUndoCalled);
        game.doMove("a3","a4");
        assertTrue(fakeListener.onMoveDoCalled);
        assertTrue(fakeListener.onMoveUndoCalled);
    }

    @Test
    public void testRemoveFromEvents(){
        game.start();
        assertTrue(fakeListener.onPuzzleGameStartCalled);
        fakeListener.removeSelfFromEvents();
        game.doMove("a3","a4");
        assertFalse(fakeListener.onMoveDoCalled);
        assertFalse(fakeListener.onMoveUndoCalled);
    }

    @Test
    public void testPrint(){
        game.start();
        game.doMove("d5","f6");

        String expected = "1. Nf6";
        String actual = game.printPlayedMoves();

        assertEquals(expected,actual);
    }

    @Test
    public void testGetsCurrentPuzzle(){
        game.start();
        ChessPuzzle expected = puzzle;
        ChessPuzzle actual = game.getCurrentPuzzle();

        assertEquals(expected, actual);
    }
}
