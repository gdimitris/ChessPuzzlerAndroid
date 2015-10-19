package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static com.envious.chesscoach.UserInterface.Pieces.Piece.PieceColor.Black;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.BlackQueen_BlackLayer;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.BlackQueen_WhiteLayer;


public class BlackQueen extends Piece {
    public BlackQueen(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = BlackQueen_WhiteLayer;
        this.blackLayerLetter = BlackQueen_BlackLayer;
        this.color = Black;
    }

    @Override
    public String toString() {
        return "Q";
    }
}
