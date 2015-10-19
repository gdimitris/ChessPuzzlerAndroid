package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static dimitris.android.chessviews.Pieces.Piece.PieceColor.*;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.*;


public class BlackQueen extends Piece {
    public BlackQueen(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = BlackQueen_WhiteLayer;
        this.blackLayerLetter = BlackQueen_BlackLayer;
        this.color = Black;
    }

    @Override
    public String toString() {
        return "Q";
    }
}
