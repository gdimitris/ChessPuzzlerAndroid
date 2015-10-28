package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

public class Queen extends Piece {

    public Queen(Paint whitePaint, Paint blackPaint, PieceColor color) {
        super(whitePaint, blackPaint, color);
    }

    @Override
    public String toString() {
        return "Q";
    }
}
