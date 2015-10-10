package dimitris.chessboardutils;

/**
 * Created by dimitris on 9/2/15.
 */
public interface MoveObserver {
    void onMovePlayed(Move movePlayed);
    void onMoveRejected(Move moveRejected);
}
