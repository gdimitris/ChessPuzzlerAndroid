package dimitris.chessboardutils;

/**
 * Created by dimitris on 4/3/15.
 */
public class Rook extends Piece {

    public Rook(PieceColor color) {
        this.color = color;
        this.type = PieceType.Rook;
    }

    @Override
    public String toString() {
        return (this.color == PieceColor.White) ? Piece.White_Rook_Unicode : Piece.Black_Rook_Unicode;
    }
}
