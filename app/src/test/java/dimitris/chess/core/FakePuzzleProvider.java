package dimitris.chess.core;

/**
 * Created by enviouscreep on 1/1/16.
 */
public class FakePuzzleProvider implements PuzzleProvider {
    private ChessPuzzle puzzle;

    public FakePuzzleProvider(ChessPuzzle puzzle){
        this.puzzle = puzzle;
    }

    @Override
    public ChessPuzzle getNextPuzzle() {
        return puzzle;
    }
}
