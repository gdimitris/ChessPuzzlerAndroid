package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static com.envious.chesscoach.UserInterface.Pieces.Piece.PieceColor.White;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.WhiteKing_BlackLayer;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.WhiteKing_WhiteLayer;


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
