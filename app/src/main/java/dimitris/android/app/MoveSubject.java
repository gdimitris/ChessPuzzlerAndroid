package dimitris.android.app;

import java.util.ArrayList;

public abstract class MoveSubject {

    protected ArrayList<MoveObserver> moveObservers;

    public void registerMoveObserver(MoveObserver observer) {
        if (moveObservers != null && observerDoesntExist(observer))
            moveObservers.add(observer);
    }

    public void unregisterMoveObserver(MoveObserver observer) {
        if (moveObservers != null)
            moveObservers.remove(observer);
    }

    public abstract void broadcastUndoToObservers();

    public abstract void broadcastRedoToObservers();

    public abstract void broadcastNewMoveToObservers(Move move);

    public boolean observerDoesntExist(MoveObserver o) {
        return (moveObservers.indexOf(o) == -1);
    }
}
