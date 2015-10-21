package dimitris.android.chessviews;

public interface MoveObserver {
    public void doMove(Move move);

    public void undoMove();

    public void redoMove();
}
