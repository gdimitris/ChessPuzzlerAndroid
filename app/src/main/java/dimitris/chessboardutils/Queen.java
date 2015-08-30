package dimitris.chessboardutils;

/**
 * Created by dimitris on 4/3/15.
 */
public class Queen extends Piece {

    public Queen(PieceColor color) {
        this.color = color;
        this.type = PieceType.Queen;
    }

    @Override
    public String toString() {
        return (this.color == PieceColor.White) ? Piece.White_Queen_Unicode : Piece.Black_Queen_Unicode;
    }
}
