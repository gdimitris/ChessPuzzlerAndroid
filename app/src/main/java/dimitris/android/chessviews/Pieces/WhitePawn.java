package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static dimitris.android.chessviews.Pieces.Piece.PieceColor.*;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.*;

public class WhitePawn extends Piece {
    public WhitePawn(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = WhitePawn_WhiteLayer;
        this.blackLayerLetter = WhitePawn_BlackLayer;
        this.color = White;
    }

    @Override
    public String toString() {
        return "p";
    }
}
