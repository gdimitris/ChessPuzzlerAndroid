package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static dimitris.android.chessviews.Pieces.Piece.PieceColor.*;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.*;


public class BlackKnight extends Piece {

    public BlackKnight(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = BlackKnight_WhiteLayer;
        this.blackLayerLetter = BlackKnight_BlackLayer;
        this.color = Black;
    }

    @Override
    public String toString() {
        return "N";
    }

}
