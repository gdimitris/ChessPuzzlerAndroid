package dimitris.chessboardutils;

/**
 * Created by dimitris on 4/3/15.
 */
public class NullPiece extends Piece {

    public NullPiece() {
        this.type = PieceType.None;
        this.color = PieceColor.None;
    }

    @Override
    public String toString() {
        return ".";
    }
}
