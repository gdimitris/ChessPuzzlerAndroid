package dimitris.android.chessviews.Pieces;

import android.graphics.Paint;

import static com.envious.chesscoach.UserInterface.Pieces.Piece.PieceColor.Black;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.BlackPawn_BlackLayer;
import static com.envious.chesscoach.UserInterface.Pieces.PieceLayersHelper.BlackPawn_WhiteLayer;


public class BlackPawn extends Piece {
    public BlackPawn(Paint whitePaint, Paint blackPaint) {
        super(whitePaint, blackPaint);
        this.whiteLayerLetter = BlackPawn_WhiteLayer;
        this.blackLayerLetter = BlackPawn_BlackLayer;
        this.color = Black;
    }

    @Override
    public String toString() {
        return "p";
    }
}
