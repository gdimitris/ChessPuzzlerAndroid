package dimitris.chess.core;

/**
 * Created by dimitris on 4/3/15.
 */
public class Knight extends Piece {

    public Knight(PieceColor color) {
        this.color = color;
        this.type = PieceType.Knight;
    }

    @Override
    public String getFANString() {
        return (this.color == PieceColor.White) ? Piece.White_Knight_Unicode : Piece.Black_Knight_Unicode;
    }

    @Override
    public String getSANString() {
        return "N";
    }
}
