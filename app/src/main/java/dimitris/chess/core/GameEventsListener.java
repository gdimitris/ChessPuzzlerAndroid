package dimitris.chess.core;

/**
 * Created by enviouscreep on 1/16/16.
 */
public interface GameEventsListener {
    Move onMoveDo(Move move);
    void onMoveUndo();
    void onGameStart();
    void onGameEnd();
}
