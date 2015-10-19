package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static dimitris.android.chessviews.Pieces.Piece.PieceColor.*;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.*;


public class WhiteQueen extends Piece {
    public WhiteQueen(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = WhiteQueen_WhiteLayer;
        this.blackLayerLetter = WhiteQueen_BlackLayer;
        this.color = White;
    }

    @Override
    public String toString() {
        return "Q";
    }
}
