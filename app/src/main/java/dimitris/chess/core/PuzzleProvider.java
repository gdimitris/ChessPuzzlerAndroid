package dimitris.chess.core;

/**
 * Created by dimitris on 1/1/16.
 */
public interface PuzzleProvider {

    void setPuzzleReceiver(PuzzleReceiver receiver);
    void requestNextPuzzle();
}
