package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static com.envious.chesscoach.UserInterface.Pieces.Piece.PieceColor.Black;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.BlackRook_BlackLayer;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.BlackRook_WhiteLayer;


public class BlackRook extends Piece {

    public BlackRook(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = BlackRook_WhiteLayer;
        this.blackLayerLetter = BlackRook_BlackLayer;
        this.color = Black;
    }

    @Override
    public String toString() {
        return "R";
    }
}
