package dimitris.chess.core;

/**
 * Created by dimitris on 12/29/15.
 */
abstract class Piece {

    public static final String White_King_Unicode = "\u2654";
    public static final String White_Queen_Unicode = "\u2655";
    public static final String White_Rook_Unicode = "\u2656";
    public static final String White_Bishop_Unicode = "\u2657";
    public static final String White_Knight_Unicode = "\u2658";
    public static final String White_Pawn_Unicode = "\u2659";

    public static final String Black_King_Unicode = "\u265A";
    public static final String Black_Queen_Unicode = "\u265B";
    public static final String Black_Rook_Unicode = "\u265C";
    public static final String Black_Bishop_Unicode = "\u265D";
    public static final String Black_Knight_Unicode = "\u265E";
    public static final String Black_Pawn_Unicode = "\u265F";

    PieceType type;
    PieceColor color;

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

    public boolean isActualPiece(){
        return true;
    }

    public abstract String getFANString();
    public abstract String getSANString();

}
