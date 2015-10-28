package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

public class Bishop extends Piece {

    public Bishop(Paint whitePaint, Paint blackPaint, PieceColor color) {
        super(whitePaint, blackPaint, color);
    }

    @Override
    public String toString() {
        return "B";
    }
}
