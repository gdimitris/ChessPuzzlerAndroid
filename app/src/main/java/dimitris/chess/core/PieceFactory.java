package dimitris.chess.core;

/**
 * Created by enviouscreep on 1/2/16.
 */
public class PieceFactory {
    public static Piece createNullPiece(){
        return new NullPiece();
    }

    public static Piece createPiece(Piece.PieceType type, Piece.PieceColor color){
        switch (type){
            case King:
                return new King(color);
            case Queen:
                return new Queen(color);
            case Rook:
                return new Rook(color);
            case Bishop:
                return new Bishop(color);
            case Knight:
                return new Knight(color);
            default:
                return new Pawn(color);
        }
    }
}
