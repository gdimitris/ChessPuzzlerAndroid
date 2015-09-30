package dimitris.chessboardutils;

/**
 * Created by dimitris on 4/3/15.
 */
public class Bishop extends Piece {

    public Bishop(PieceColor color) {
        this.color = color;
        this.type = PieceType.Bishop;
    }

    @Override
    public String getFANString() {
        return (this.color == PieceColor.White) ? Piece.White_Bishop_Unicode : Piece.Black_Bishop_Unicode;
    }

    @Override
    public String getSANString() {
        return "B";
    }
}
