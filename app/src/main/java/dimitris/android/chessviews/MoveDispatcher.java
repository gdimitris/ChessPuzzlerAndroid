package dimitris.android.chessviews;

/**
 * Created by dimitris on 10/02/16.
 */
public class MoveDispatcher {

    private Square lastSelectedSquare;
    private DrawableBoard board;
    private BoardContainerView parentView;

    public MoveDispatcher(DrawableBoard board, BoardContainerView parentView){
        lastSelectedSquare = null;
        this.board = board;
        this.parentView = parentView;
    }

    public void squareClickedAt(int row, int col) {
        Square selectedSquare = board.getSquareAt(row,col);

        if (lastSelectedSquare == null) {
            selectSquareIfNotEmpty(row,col);
        } else if (selectedSquare.equals(lastSelectedSquare)) {
            clearSelection();
        } else {
            dispatchMove(lastSelectedSquare, selectedSquare);
            clearSelection();
        }
    }

    private void dispatchMove(Square src, Square dest) {
        UIMove move = new UIMove(src,dest);
        board.doMove(move);

        parentView.moveDetected(src.getName(),dest.getName());
    }

    public void selectSquareIfNotEmpty(int row, int col) {
        Square selectedSquare = board.getSquareAt(row,col);
        if (!board.squareIsEmpty(selectedSquare)) {
            lastSelectedSquare = selectedSquare;
            board.selectSquare(row,col);
        }
    }

    public void clearSelection(){
        lastSelectedSquare = null;
        board.clearLastSelectedSquare();
    }
}
