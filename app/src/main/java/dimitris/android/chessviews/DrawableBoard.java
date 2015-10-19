package dimitris.android.chessviews;

import android.graphics.Canvas;


public class DrawableBoard {

    private int squareSize;
    private SquareView lastSelectedSquareView;
    private SquareView[][] board;
//    private MoveExecutor moveExecutor;
//    private MoveFilterer moveFilterer;

    public DrawableBoard(int squareSize) {
        this.squareSize = squareSize;
//        moveObservers = new ArrayList<MoveObserver>();
//        moveExecutor = new MoveExecutor(this);
        board = new BoardViewFactory(squareSize).createEmptyBoard();

//        TurnArbiter arbiter = new TurnArbiter(this);
//        moveFilterer = new MoveFilterer();
//        moveFilterer.addFilter(arbiter);
//        moveFilterer.addFilter(new KingCaptureFilter());
//        moveFilterer.addFilter(new SelfCaptureFilter());
    }

    public void draw(Canvas canvas) {
        if (board == null)
            return;

        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                board[row][col].draw(canvas);
    }

    protected void squareSelectedAt(int row, int col) {

        if (lastSelectedSquareView == null) {
            //selectSquareIfNotEmpty(row, col);
        } else if (lastSelectedSquareView == board[row][col]) {
            clearSelection();
        } else {
            //dispatchNewMoveIfPassesFilters(row, col);
            clearSelection();
        }

    }

//    private void dispatchNewMoveIfPassesFilters(int row, int col) {
//        Move toMake = new Move(lastSelectedSquare, board[row][col]);
//
//        if (moveFilterer.moveIsLegal(toMake))
//            doMove(toMake);
//    }

//    private void selectSquareIfNotEmpty(int row, int col) {
//        if (!squareIsEmpty(row, col)) {
//            lastSelectedSquare = board[row][col];
//            lastSelectedSquare.setSelected(true);
//        }
//    }

//    private boolean squareIsEmpty(int row, int col) {
//        return board[row][col].getPiece() == null;
//    }

    private void clearSelection() {
        lastSelectedSquareView.setSelected(false);
        lastSelectedSquareView = null;
    }

//    public void setUpPosition(String FEN, Typeface font) {
//        FenParser parser = new FenParser(board, new WhitePieceFactory(font, squareSize), new BlackPieceFactory(font, squareSize));
//        try {
//            parser.parse(FEN);
//        } catch (FenParser.BadFenException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void doMove(Move toDo) {
//        broadcastNewMoveToObservers(toDo);
//    }
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
//        return board[coords.row][coords.column];
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
