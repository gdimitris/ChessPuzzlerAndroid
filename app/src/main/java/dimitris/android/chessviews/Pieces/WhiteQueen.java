package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static com.envious.chesscoach.UserInterface.Pieces.Piece.PieceColor.White;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.WhiteQueen_BlackLayer;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.WhiteQueen_WhiteLayer;


public class WhiteQueen extends Piece {
    public WhiteQueen(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = WhiteQueen_WhiteLayer;
        this.blackLayerLetter = WhiteQueen_BlackLayer;
        this.color = White;
    }

    @Override
    public String toString() {
        return "Q";
    }
}
