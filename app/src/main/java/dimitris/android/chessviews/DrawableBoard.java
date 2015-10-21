package dimitris.android.chessviews;

import android.graphics.Canvas;
import android.graphics.Typeface;

import dimitris.android.chessviews.Pieces.FenParser;


public class DrawableBoard extends MoveSubject{

    private int squareSize;
    private SquareView lastSelectedSquareView;
    private SquareView[][] squareViews;
//    private MoveExecutor moveExecutor;
//    private MoveFilterer moveFilterer;

    public DrawableBoard(int squareSize) {
        super();
        this.squareSize = squareSize;
//        moveExecutor = new MoveExecutor(this);
//

//        TurnArbiter arbiter = new TurnArbiter(this);
//        moveFilterer = new MoveFilterer();
//        moveFilterer.addFilter(arbiter);
//        moveFilterer.addFilter(new KingCaptureFilter());
//        moveFilterer.addFilter(new SelfCaptureFilter());
    }

    public void draw(Canvas canvas) {
        if (squareViews == null)
            return;

        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                squareViews[row][col].draw(canvas);
    }

    protected void squareSelectedAt(int row, int col) {
        System.out.println("Clicked at square "+ squareViews[row][col].getName() );
        if (lastSelectedSquareView == null) {
            selectSquareIfNotEmpty(row, col);
        } else if (lastSelectedSquareView == squareViews[row][col]) {
            clearSelection();
        } else {
            dispatchNewMoveIfPassesFilters(row, col);
            clearSelection();
        }
    }

    private void dispatchNewMoveIfPassesFilters(int row, int col) {
        Move toMake = new Move(lastSelectedSquareView, squareViews[row][col]);

        //if (moveFilterer.moveIsLegal(toMake))
        doMove(toMake);
    }

    private void selectSquareIfNotEmpty(int row, int col) {
        if (!squareIsEmpty(row, col)) {
            lastSelectedSquareView = squareViews[row][col];
            lastSelectedSquareView.setSelected(true);

        }
    }

    private boolean squareIsEmpty(int row, int col) {
        return squareViews[row][col].isEmpty();
    }

    private void clearSelection() {
        lastSelectedSquareView.setSelected(false);
        lastSelectedSquareView = null;
    }

    public void setUpBoard(Typeface font) {
        try {
            squareViews = new BoardViewFactory(squareSize).createInitialBoard(font);
        } catch (FenParser.BadFenException e) {
            e.printStackTrace();
        }
    }

    public void doMove(Move toDo) {
        toDo.execute();
        broadcastNewMoveToObservers(toDo);
    }

    @Override
    public void broadcastUndoToObservers() {

    }

    @Override
    public void broadcastRedoToObservers() {

    }

    @Override
    public void broadcastNewMoveToObservers(Move move) {

    }
//
//    public void undoMove() {
//        broadcastUndoToObservers();
//    }
//
//    public void redoMove() {
//        broadcastRedoToObservers();
//    }
//
//    public Square getSquare(String squareName) {
//        return getSquare(getSquareCoords(squareName));
//    }
//
//    public Square getSquare(BoardCoords coords) {
//        return squareViews[coords.row][coords.column];
//    }
//
//    public BoardCoords getBoardCoordsOfSquare(String squareName) {
//        return getSquareCoords(squareName);
//    }
//
//    public String printMoves() {
//        return moveExecutor.printMoves();
//    }
//
//    @Override
//    public void broadcastUndoToObservers() {
//        for (MoveObserver o : moveObservers)
//            o.undoMove();
//    }
//
//    @Override
//    public void broadcastRedoToObservers() {
//        for (MoveObserver o : moveObservers)
//            o.redoMove();
//    }
//
//    @Override
//    public void broadcastNewMoveToObservers(Move move) {
//        for (MoveObserver o : moveObservers)
//            o.doMove(move);
//    }
}
