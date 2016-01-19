package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

public class Knight extends Piece {

    public Knight(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
    }

    @Override
    public String toString() {
        return "N";
    }
}
