package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static dimitris.android.chessviews.Pieces.Piece.PieceColor.*;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.*;


public class WhiteKnight extends Piece {
    public WhiteKnight(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = WhiteKnight_WhiteLayer;
        this.blackLayerLetter = WhiteKnight_BlackLayer;
        this.color = White;
    }

    @Override
    public String toString() {
        return "N";
    }
}
