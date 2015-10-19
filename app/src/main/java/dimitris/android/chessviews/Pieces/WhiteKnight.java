package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static com.envious.chesscoach.UserInterface.Pieces.Piece.PieceColor.White;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.WhiteKnight_BlackLayer;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.WhiteKnight_WhiteLayer;


public class WhiteKnight extends Piece {
    public WhiteKnight(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = WhiteKnight_WhiteLayer;
        this.blackLayerLetter = WhiteKnight_BlackLayer;
        this.color = White;
    }

    @Override
    public String toString() {
        return "N";
    }
}
