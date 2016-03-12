package dimitris.chess.core;

/**
 * Created by enviouscreep on 1/16/16.
 */
public interface PuzzleGameEventsListener {
    void onMoveDo(Move move);
    void onMoveUndo(Move move);
    void onPuzzleGameStart(ChessPuzzle puzzle);
    void onPuzzleGameSolved();
    void onPuzzleGameQuit();
}
