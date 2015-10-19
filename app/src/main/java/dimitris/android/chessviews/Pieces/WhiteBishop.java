package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static com.envious.chesscoach.UserInterface.Pieces.Piece.PieceColor.White;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.WhiteBishop_BlackLayer;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.WhiteBishop_WhiteLayer;


public class WhiteBishop extends Piece {

    public WhiteBishop(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = WhiteBishop_WhiteLayer;
        this.blackLayerLetter = WhiteBishop_BlackLayer;
        this.color = White;
    }

    @Override
    public String toString() {
        return "B";
    }
}
