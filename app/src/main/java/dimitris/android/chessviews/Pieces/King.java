package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

public class King extends Piece {


    public King(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
    }

    @Override
    public String toString() {
        return "K";
    }
}
