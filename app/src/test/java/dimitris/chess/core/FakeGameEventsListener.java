package dimitris.chess.core;

/**
 * Created by dimitris on 25/02/16.
 */
public class FakeGameEventsListener implements PuzzleGameEventsListener {

    public boolean onMoveDoCalled;
    public boolean onMoveUndoCalled;
    public boolean onPuzzleGameStartCalled;
    public boolean onPuzzleGameSolvedCalled;
    public boolean onPuzzleGameQuitCalled;
    private Game game;

    public FakeGameEventsListener(Game game){
        this.game = game;
        this.game.registerGameEventsListener(this);
        onMoveDoCalled = false;
        onMoveUndoCalled = false;
        onPuzzleGameQuitCalled = false;
        onPuzzleGameSolvedCalled = false;
        onPuzzleGameStartCalled = false;
    }


    public void removeSelfFromEvents(){
        game.removeGameEventsListener(this);
    }

    @Override
    public void onMoveDo(Move move) {
        onMoveDoCalled = true;
    }

    @Override
    public void onMoveUndo(Move move) {
        onMoveUndoCalled = true;
    }

    @Override
    public void onPuzzleGameStart(ChessPuzzle puzzle) {
        onPuzzleGameStartCalled = true;
    }

    @Override
    public void onPuzzleGameSolved() {
        onPuzzleGameSolvedCalled = true;
    }

    @Override
    public void onPuzzleGameQuit() {
        onPuzzleGameQuitCalled = true;
    }
}
