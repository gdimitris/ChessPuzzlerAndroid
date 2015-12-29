package dimitris.chess.core;

/**
 * Created by dimitris on 12/29/15.
 */
public class Piece {
    PieceType type;
    PieceColor color;

    public Piece(PieceType type, PieceColor color){
        this.type = type;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        Piece other = (Piece) o;
        return this.type == other.type && this.color==other.color;
    }

    public enum PieceType{
        King,
        Queen,
        Rook,
        Knight,
        Bishop,
        Pawn,
        NullPiece
    }

    public enum PieceColor{
        White,
        Black,
        NullColor
    }
}
