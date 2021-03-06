package dimitris.chess.core;

/**
 * Created by dimitris on 4/3/15.
 */
class NullPiece extends Piece {

    public NullPiece() {
        this.type = PieceType.NullPiece;
        this.color = PieceColor.NullColor;
    }

    @Override
    public String getFANString() {
        return ".";
    }

    @Override
    public String getSANString() {
        return ".";
    }

    @Override
    public boolean isActualPiece() {
        return false;
    }
}
