package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

public class King extends Piece {


    public King(Paint whitePaint, Paint blackPaint, PieceColor color) {
        super(whitePaint, blackPaint, color);
    }

    @Override
    public String toString() {
        return "K";
    }
}
