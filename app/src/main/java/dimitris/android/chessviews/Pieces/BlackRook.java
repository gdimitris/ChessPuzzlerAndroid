package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static dimitris.android.chessviews.Pieces.Piece.PieceColor.*;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.*;


public class BlackRook extends Piece {

    public BlackRook(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = BlackRook_WhiteLayer;
        this.blackLayerLetter = BlackRook_BlackLayer;
        this.color = Black;
    }

    @Override
    public String toString() {
        return "R";
    }
}
