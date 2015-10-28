package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

public class Rook extends Piece {

    public Rook(Paint whitePaint, Paint blackPaint, PieceColor color) {
        super(whitePaint, blackPaint, color);
    }

    @Override
    public String toString() {
        return "R";
    }
}
