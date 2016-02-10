package dimitris.android.chessviews;

import android.graphics.Canvas;
import android.graphics.Rect;

import dimitris.android.chessviews.Pieces.Piece;

/**
 * Created by dimitris on 1/31/16.
 */
public class Square {

    private Rect itsRect;
    private int row;
    private int col;
    public static final String ROWS = "12345678";
    public static final String COLUMNS = "hgfedcba";
    private Piece piece;

    public Square(int row, int col){
        this.row = row;
        this.col = col;
        itsRect = new Rect(0,0,0,0);
    }

    public Square(Rect itsRect, int row, int col){
        this.itsRect = itsRect;
        this.row = row;
        this.col = col;
    }

    public Rect getRect() {
        return itsRect;
    }

    public String getName(){
        return "" + COLUMNS.charAt(7 - col) + ROWS.charAt(7 - row);
    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece piece){
        if(piece!= null) {
            piece.setPositionCoords(row, col);
            this.piece = piece;
        }
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public void setSize(int size){
        itsRect.set(col * size, row * size, (col + 1) * size, (row + 1) * size);
    }

    public void draw(Canvas canvas){
        if(piece != null)
            piece.draw(canvas);
    }

    public void clear(){
        this.piece = null;
    }
}
