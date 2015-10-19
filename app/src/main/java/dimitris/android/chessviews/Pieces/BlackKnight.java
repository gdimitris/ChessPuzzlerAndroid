package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static com.envious.chesscoach.UserInterface.Pieces.Piece.PieceColor.Black;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.BlackKnight_BlackLayer;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.BlackKnight_WhiteLayer;


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
