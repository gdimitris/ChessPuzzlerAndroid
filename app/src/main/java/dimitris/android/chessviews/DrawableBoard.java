package dimitris.android.chessviews;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

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
    private Square[][] board;
    private SquareHighlighter squareHighlighter;
    private List<UIMove> playedMoves;

    public DrawableBoard(BoardContainerView parentView) {
        super();
        this.parentView = parentView;
        squareSize = 80;
        boardPaint = BoardPaintCreator.createPaintWithSquareSize(squareSize);
        lastSelectedSquare = null;
        squareHighlighter = new SquareHighlighter(getRectForSquareAt(0,0));
        alivePieces = new ArrayList<>();
        playedMoves = new ArrayList<>();
        board = new Square[8][8];

        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                board[i][j] = new Square(i,j);
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

        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                board[i][j].setSize(size);
    }

    @Override
    public void draw(Canvas canvas) {
        if (noPiecesExist())
            return;

        drawBoard(canvas);
        drawSelectedSquare(canvas);
        drawAlivePieces(canvas);
    }

    public void doMove(UIMove move) {
        move.execute();
        playedMoves.add(move);
    }


    public void undoMove(UIMove move) {

    }

    public void squareClickedAt(int row, int col) {
        Square selectedSquare = board[row][col];

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
        parentView.moveDetected(src.getName(),dest.getName());

        UIMove move = new UIMove(src,dest);
        doMove(move);
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
        //Log.e("drawBoard", "Drawing Board with left:"+bounds.left+" top:"+bounds.top+" right:"+bounds.right+" bottom:"+bounds.bottom);
    }

    private void selectSquareIfNotEmpty(int row, int col) {
        Square selectedSquare = board[row][col];
        if (!squareIsEmpty(selectedSquare)) {
            lastSelectedSquare = selectedSquare;
            squareHighlighter.setSquare(lastSelectedSquare);
        }
    }

    private void clearSelection(){
        lastSelectedSquare = null;
    }

    private boolean squareIsEmpty(Square squareToCheck) {
        return squareToCheck.getPiece() == null;
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
