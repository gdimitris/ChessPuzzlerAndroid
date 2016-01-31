package dimitris.android.chessviews;

import android.graphics.Rect;

/**
 * Created by dimitris on 1/31/16.
 */
public class Square {

    private Rect itsRect;
    private int row;
    private int col;
    public static final String ROWS = "12345678";
    public static final String COLUMNS = "hgfedcba";

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
}
