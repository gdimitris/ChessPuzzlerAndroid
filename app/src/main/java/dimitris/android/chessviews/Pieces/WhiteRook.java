package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static com.envious.chesscoach.UserInterface.Pieces.Piece.PieceColor.White;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.WhiteRook_BlackLayer;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.WhiteRook_WhiteLayer;


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
