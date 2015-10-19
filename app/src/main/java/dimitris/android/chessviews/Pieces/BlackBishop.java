package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

public class BlackBishop extends Piece {

    public BlackBishop(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = PieceLayersHelper.BlackBishop_WhiteLayer;
        this.blackLayerLetter = PieceLayersHelper.BlackBishop_BlackLayer;
        this.color = PieceColor.Black;
    }

    @Override
    public String toString() {
        return "B";
    }

}
