package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static dimitris.android.chessviews.Pieces.Piece.PieceColor.Black;

public class BlackKing extends Piece {

    public BlackKing(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = PieceLayersHelper.BlackKing_WhiteLayer;
        this.blackLayerLetter = PieceLayersHelper.BlackKing_BlackLayer;
        this.color = Black;
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
