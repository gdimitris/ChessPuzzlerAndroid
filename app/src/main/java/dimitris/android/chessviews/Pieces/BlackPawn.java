package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static dimitris.android.chessviews.Pieces.Piece.PieceColor.*;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.*;

public class BlackPawn extends Piece {
    public BlackPawn(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = BlackPawn_WhiteLayer;
        this.blackLayerLetter = BlackPawn_BlackLayer;
        this.color = Black;
    }

    @Override
    public String toString() {
        return "";
    }
}
