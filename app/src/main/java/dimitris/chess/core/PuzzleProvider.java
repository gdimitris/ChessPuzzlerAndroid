package dimitris.chess.core;

/**
 * Created by enviouscreep on 1/1/16.
 */
public interface PuzzleProvider {

    void setPuzzleReceiver(PuzzleReceiver receiver);
    void requestNextPuzzle();
}
