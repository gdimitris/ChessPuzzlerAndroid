package dimitris.android.app;

public interface MoveObserver {
    public void onMoveDo(Move move);

    public void onMoveUndo();

    public void onMoveRedo();
}
