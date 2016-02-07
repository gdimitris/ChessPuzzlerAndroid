package dimitris.android.chessviews;

import dimitris.android.chessviews.Pieces.Piece;

/**
 * Created by dimitris on 2/2/16.
 */
public class UIMove {
    private Square source;
    private Square destination;
    private Piece movedPiece;
    private Piece capturedPiece;
    private boolean isExecuted;

    public UIMove(Square source,Square destination){
        this.source = source;
        this.destination = destination;
        isExecuted = false;
    }

    public void execute(){
        movedPiece = source.getPiece();
        capturedPiece = destination.getPiece();
    }
}
