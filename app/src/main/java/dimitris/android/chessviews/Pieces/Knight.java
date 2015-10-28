package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

public class Knight extends Piece {

    public Knight(Paint whitePaint, Paint blackPaint, PieceColor color) {
        super(whitePaint, blackPaint, color);
    }

    @Override
    public String toString() {
        return "N";
    }
}
