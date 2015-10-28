package dimitris.android.chessviews;

import dimitris.android.chessviews.Pieces.Piece;
import dimitris.android.chessviews.Pieces.Piece.PieceColor;

import static dimitris.android.chessviews.Pieces.Piece.PieceColor.*;


public class Move {
    private SquareView source;
    private SquareView destination;
    private Piece captured;
    private boolean executed;
    private boolean isMate;
    private boolean isCheck;


    public Move(SquareView source, SquareView destination) {
        this.source = source;
        this.destination = destination;
        executed = false;
        isMate = false;
        isCheck = false;
    }

    public void execute() {
        captured = destination.getPiece();
        destination.setPiece(source.getPiece());
        source.setPiece(null);
        executed = true;
    }

    public void undo() {
        source.setPiece(destination.getPiece());
        destination.setPiece(captured);
        captured = null;
    }

    @Override
    public boolean equals(Object o) {
        Move toCheck = (Move) o;

        if (source.equals(toCheck.source) && destination.equals(toCheck.destination))
            return true;
        return false;
    }

    public PieceColor getMoveColor() {
        SquareView pieceContainer = executed ? destination : source;

        return pieceContainer.getPiece().getColor();
    }

    public boolean isCapture() {
        return (captured != null);
    }

    public SquareView getSourceSquare(){
        return source;
    }

    public SquareView getDestinationSquare(){
        return destination;
    }

    public void setIsMate(boolean isMate){
        this.isMate = isMate;
    }

    public boolean isMate(){
        return isMate;
    }

    public boolean isWhiteMove(){
        return getMoveColor()== White;
    }

    public boolean isCheck(){
        return isCheck;
    }

    public void setIsCheck(boolean isCheck){
        this.isCheck = isCheck;
    }
}
