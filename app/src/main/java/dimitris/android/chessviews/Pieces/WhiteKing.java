package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static dimitris.android.chessviews.Pieces.Piece.PieceColor.*;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.*;

public class WhiteKing extends Piece {
    public WhiteKing(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = WhiteKing_WhiteLayer;
        this.blackLayerLetter = WhiteKing_BlackLayer;
        this.color = White;
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean canBeCaptured() {
        return false;
    }
}
