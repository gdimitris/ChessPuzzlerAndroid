package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static com.envious.chesscoach.UserInterface.Pieces.Piece.PieceColor.White;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.WhitePawn_BlackLayer;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.WhitePawn_WhiteLayer;


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
