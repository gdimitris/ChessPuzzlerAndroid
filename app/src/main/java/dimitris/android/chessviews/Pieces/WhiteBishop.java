package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static dimitris.android.chessviews.Pieces.Piece.PieceColor.*;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.*;


public class WhiteBishop extends Piece {

    public WhiteBishop(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = WhiteBishop_WhiteLayer;
        this.blackLayerLetter = WhiteBishop_BlackLayer;
        this.color = White;
    }

    @Override
    public String toString() {
        return "B";
    }
}
