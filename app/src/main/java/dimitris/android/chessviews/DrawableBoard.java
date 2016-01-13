package dimitris.android.chessviews;

import android.graphics.Canvas;
import android.graphics.Typeface;

import java.util.ArrayList;

import dimitris.android.app.Move;
import dimitris.android.app.MoveManager;
import dimitris.android.app.MoveObserver;
import dimitris.android.app.MoveSubject;
import dimitris.android.app.MoveValidator;
import dimitris.android.chessviews.Pieces.BlackPieceFactory;
import dimitris.android.chessviews.Pieces.FenParser;
import dimitris.android.chessviews.Pieces.WhitePieceFactory;
import dimitris.chess.core.MovePrinter;


public class DrawableBoard extends MoveSubject {

    private SquareView lastSelectedSquareView;
    private SquareView[][] squareViews;
    private MoveManager moveManager;
    private MoveValidator moveChecker;
    private MovePrinter movePrinter;
    private BoardContainerView parentView;
    private int squareSize=1;

    public DrawableBoard(BoardContainerView parentView) {
        super();
        this.moveObservers = new ArrayList<>();
        this.moveManager = new MoveManager();
        this.moveChecker = new MoveValidator(this);
        //this.movePrinter = new MovePrinter(this);
        this.parentView = parentView;
    }

    public void draw(Canvas canvas) {
        if (squareViews == null)
            return;

        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                squareViews[row][col].draw(canvas);
    }

    protected void squareSelectedAt(int row, int col) {
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

        if (moveChecker.isValid(toMake))
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

    public void createInitialBoard() {
        try {
            Typeface font = FontLoader.loadDefaultFont(parentView.getContext());
            squareViews = new DrawableBoardFactory(squareSize).createInitialBoard(font);
        } catch (FenParser.BadFenException e) {
            e.printStackTrace();
        }
    }

    public void createEmptyBoard(){
            squareViews = new DrawableBoardFactory(squareSize).createEmptyBoard();
    }

    public void setSquareSize(int size){
        this.squareSize = size;
    }

    public void setPosition(String FEN){
        try {
            clearBoard();
            Typeface typeface = FontLoader.loadDefaultFont(parentView.getContext());
            WhitePieceFactory whiteFactory = new WhitePieceFactory(typeface, squareSize);
            BlackPieceFactory blackPieceFactory = new BlackPieceFactory(typeface, squareSize);
            FenParser parser = new FenParser(squareViews , whiteFactory, blackPieceFactory);
            parser.parse(FEN);
        } catch (FenParser.BadFenException e) {
            e.printStackTrace();
        }
        parentView.invalidate();
    }

    public void doMove(Move toDo) {
        moveManager.executeMove(toDo);
        broadcastNewMoveToObservers(toDo);
    }

    private void clearBoard(){
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                squareViews[row][col].setPiece(null);
    }
    @Override
    public void broadcastUndoToObservers() {

    }

    @Override
    public void broadcastRedoToObservers() {

    }

    @Override
    public void broadcastNewMoveToObservers(Move move) {
        for(MoveObserver observer : moveObservers)
            observer.onMoveDo(move);
        //Log.e("MovePrinter", "Current Moves: "+ movePrinter.printMovesPlayed());
    }
}
