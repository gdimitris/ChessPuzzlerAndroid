package dimitris.android.chessviews;

public interface MoveObserver {
    public void onMoveDo(Move move);

    public void onMoveUndo();

    public void onMoveRedo();
}
