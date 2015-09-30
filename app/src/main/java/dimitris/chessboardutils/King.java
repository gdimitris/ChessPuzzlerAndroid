package dimitris.chessboardutils;

/**
 * Created by dimitris on 4/3/15.
 */
public class King extends Piece {
    public King(PieceColor color) {
        this.color = color;
        this.type = PieceType.King;
    }

    @Override
    public String getFANString() {
        return (this.color == PieceColor.White) ? Piece.White_King_Unicode : Piece.Black_King_Unicode;
    }

    @Override
    public String getSANString() {
        return "K";
    }
}
