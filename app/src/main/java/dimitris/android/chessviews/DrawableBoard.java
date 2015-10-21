package dimitris.android.chessviews;

import android.graphics.Canvas;
import android.graphics.Typeface;

import dimitris.android.chessviews.Pieces.FenParser;


public class DrawableBoard{

    private SquareView lastSelectedSquareView;
    private SquareView[][] squareViews;
    private MoveManager moveManager;

    public DrawableBoard() {
        super();
        this.moveManager = new MoveManager();
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

    public void setUpBoard(Typeface font, int squareSize) {
        try {
            squareViews = new DrawableBoardFactory(squareSize).createInitialBoard(font);
        } catch (FenParser.BadFenException e) {
            e.printStackTrace();
        }
    }

    public void doMove(Move toDo) {
        moveManager.executeMove(toDo);
    }
}
