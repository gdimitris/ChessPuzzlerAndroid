package dimitris.android.chessviews;

import dimitris.android.chessviews.Pieces.Piece;


public class Move {
    private SquareView source;
    private SquareView destination;
    private Piece captured;
    private boolean executed;


    public Move(SquareView source, SquareView destination) {
        this.source = source;
        this.destination = destination;
        executed = false;
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

    public Piece.PieceColor getMoveColor() {
        SquareView pieceContainer = executed ? destination : source;

        return pieceContainer.getPiece().getColor();
    }

    public boolean isCapture() {
        return (captured != null);
    }

}
