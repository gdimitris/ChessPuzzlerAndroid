package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static dimitris.android.chessviews.Pieces.Piece.PieceColor.*;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.*;


public class WhiteRook extends Piece {
    public WhiteRook(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = WhiteRook_WhiteLayer;
        this.blackLayerLetter = WhiteRook_BlackLayer;
        this.color = White;
    }

    @Override
    public String toString() {
        return "R";
    }
}
