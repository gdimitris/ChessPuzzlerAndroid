package dimitris.chess.core;

/**
 * Created by dimitris on 1/1/16.
 */
public interface PuzzleProvider {

    void insertPuzzleBackInList(ChessPuzzle puzzle);
    void setPuzzleReceiver(PuzzleReceiver receiver);
    void requestNextPuzzle();
}
