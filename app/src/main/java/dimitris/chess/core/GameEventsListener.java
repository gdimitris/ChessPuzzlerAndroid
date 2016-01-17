package dimitris.chess.core;

/**
 * Created by enviouscreep on 1/16/16.
 */
public interface GameEventsListener {
    void onMoveDo(Move move);
    void onMoveUndo(Move move);
    void onGameStart();
    void onGameEnd();
}
