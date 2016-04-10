package dimitris.chess.core;

/**
 * Created by dimitris on 1/1/16.
 */
public class FakePuzzleProvider implements PuzzleProvider {
    private ChessPuzzle puzzle;
    private PuzzleReceiver receiver;

    public FakePuzzleProvider(ChessPuzzle puzzle){
        this.puzzle = puzzle;
    }

    @Override
    public void insertPuzzleBackInList(ChessPuzzle puzzle) {

    }

    @Override
    public void setPuzzleReceiver(PuzzleReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void requestNextPuzzle() {
        receiver.onPuzzleReady(puzzle);
    }
}
