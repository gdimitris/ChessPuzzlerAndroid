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
        source.setPiece(null);
        destination.setPiece(movedPiece);
        isExecuted = true;
    }

    public boolean isCaptureMove(){
        if (isExecuted)
            return capturedPiece != null;
        else
            return destination.getPiece() != null;
    }

    public Piece getCapturedPiece(){
        if(isExecuted)
            return capturedPiece;
        else
            return destination.getPiece();
    }

    public Piece getMovedPiece(){
        return movedPiece;
    }

    public Square getDestinationSquare(){
        return destination;
    }

    public void undo() {
        movedPiece = destination.getPiece();
        source.setPiece(movedPiece);
        destination.setPiece(capturedPiece);
        isExecuted = false;
    }
}
