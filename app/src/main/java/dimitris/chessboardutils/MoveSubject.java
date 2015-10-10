package dimitris.chessboardutils;

public interface MoveSubject {
    void registerObserver(MoveObserver observer);
    void removeObserver(MoveObserver observer);
    void propagateMoveToObservers(Move movePlayed);

}
