package dimitris.chess.core;

/**
 * Created by dimitris on 1/1/16.
 */
public class Game {

    private PuzzleProvider puzzleProvider;


    public Game(PuzzleProvider puzzleProvider) {
        this.puzzleProvider = puzzleProvider;
    }

    public void playMove(Move move) {

    }

    public boolean puzzleIsSolved() {
        return true;
    }
}
