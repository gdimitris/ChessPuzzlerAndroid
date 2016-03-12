package dimitris.chess.core;

/**
 * Created by dimitris on 4/3/15.
 */
class Rook extends Piece {

    public Rook(PieceColor color) {
        this.color = color;
        this.type = PieceType.Rook;
    }

    @Override
    public String getFANString() {
        return (this.color == PieceColor.White) ? Piece.White_Rook_Unicode : Piece.Black_Rook_Unicode;
    }

    @Override
    public String getSANString() {
        return "R";
    }
}
