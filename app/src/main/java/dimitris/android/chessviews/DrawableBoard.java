package dimitris.android.chessviews;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dimitris.android.chessviews.Pieces.BlackPieceFactory;
import dimitris.android.chessviews.Pieces.FenParser;
import dimitris.android.chessviews.Pieces.Piece;
import dimitris.android.chessviews.Pieces.WhitePieceFactory;


public class DrawableBoard extends Drawable{

    private BoardContainerView parentView;
    private int squareSize;
    private Paint boardPaint;
    private List<Piece> alivePieces;
    private Square lastSelectedSquare;
    private SquareHighlighter squareHighlighter;

    public DrawableBoard(BoardContainerView parentView) {
        super();
        this.parentView = parentView;
        squareSize = 80;
        boardPaint = BoardPaintCreator.createPaintWithSquareSize(squareSize);
        lastSelectedSquare = null;
        squareHighlighter = new SquareHighlighter(getRectForSquareAt(0,0));
        alivePieces = new ArrayList<>();
    }

    public void setPosition(String FEN){
        try {
            clearBoard();
            Typeface typeface = FontLoader.loadDefaultFont(parentView.getContext());
            WhitePieceFactory whiteFactory = new WhitePieceFactory(typeface, squareSize);
            BlackPieceFactory blackPieceFactory = new BlackPieceFactory(typeface, squareSize);
            FenParser parser = new FenParser(whiteFactory, blackPieceFactory,squareSize);
            alivePieces = parser.parse(FEN);
        } catch (FenParser.BadFenException e) {
            e.printStackTrace();
        }
    }

    public void setSquareSize(int size){
        if(squareSize == size)
            return;

        this.squareSize = size;
        this.boardPaint = BoardPaintCreator.createPaintWithSquareSize(squareSize);

        for(Piece p : alivePieces)
            p.setSize(size);
    }

    @Override
    public void draw(Canvas canvas) {
        if (noPiecesExist())
            return;

        drawBoard(canvas);
        drawSelectedSquare(canvas);
        drawAlivePieces(canvas);
    }

    public void squareClickedAt(int row, int col) {
        Rect clicked = getRectForSquareAt(row,col);

        if (lastSelectedSquare == null) {
            selectSquareIfNotEmpty(clicked,row,col);
        } else if (clicked.equals(lastSelectedSquare)) {
            clearSelection();
        } else {
            dispatchNewMoveIfPassesFilters(row, col);
            clearSelection();
        }
    }

    private void dispatchNewMoveIfPassesFilters(int row, int col) {
        String source = lastSelectedSquare.getName();
        //String dest = squareViews[row][col].getName();
        //parentView.moveDetected(source,dest);
        //doMove(toMake);
    }

    public int getSquareSize(){
        return squareSize;
    }

    private boolean noPiecesExist() {
        return alivePieces == null || alivePieces.isEmpty();
    }

    private void drawSelectedSquare(Canvas canvas){
        if (lastSelectedSquare != null){
            squareHighlighter.draw(canvas);
        }
    }

    private void drawAlivePieces(Canvas canvas) {
        for(Piece p: alivePieces)
            p.draw(canvas);
    }

    private void drawBoard(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.drawRect(bounds.left,bounds.top,bounds.right,bounds.bottom,boardPaint);
        Log.e("drawBoard", "Drawing Board with left:"+bounds.left+" top:"+bounds.top+" right:"+bounds.right+" bottom:"+bounds.bottom);
    }

    private void selectSquareIfNotEmpty(Rect selectedRect, int row, int col) {
        if (!squareIsEmpty(selectedRect)) {
            lastSelectedSquare = new Square(selectedRect, row, col);
            squareHighlighter.setSquare(lastSelectedSquare);
        }
    }

    private void clearSelection(){
        lastSelectedSquare = null;
    }

    private boolean squareIsEmpty(Rect squareToCheck) {
        for(Piece p: alivePieces){
            if(p.getPositionRect().contains(squareToCheck))
                return false;
        }
        return true;
    }

    private void clearBoard(){
        if(alivePieces!= null)
            alivePieces.clear();
        clearSelection();
    }

    private Rect getRectForSquareAt(int row, int col) {
        return new Rect(col * squareSize, row * squareSize, (col + 1) * squareSize, (row + 1) * squareSize);
    }

    @Override
    public void setAlpha(int alpha) {}

    @Override
    public void setColorFilter(ColorFilter cf) {}

    @Override
    public int getOpacity() { return 0; }

}
