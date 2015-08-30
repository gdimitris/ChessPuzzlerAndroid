package dimitris.chessboardutils;

/**
 * Created by dimitris on 3/30/15.
 */
public class Piece {

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

    public PieceType type;
    public PieceColor color;

    public enum PieceType {
        None, Pawn, Knight, Bishop, Rook, Queen, King
    }

    public enum PieceColor {
        None, White, Black
    }

    public static Piece create(char pieceChar){
        PieceColor color = (Character.isUpperCase(pieceChar)) ? PieceColor.White : PieceColor.Black;
        pieceChar = Character.toLowerCase(pieceChar);

        switch (pieceChar) {
            case 'r':
                return new Rook(color);
            case 'b':
                return new Bishop(color);
            case 'n':
                return new Knight(color);
            case 'q':
                return new Queen(color);
            case 'k':
                return new King(color);
            case 'p':
                return new Pawn(color);
            default:
                return new NullPiece();
        }
    }

}
