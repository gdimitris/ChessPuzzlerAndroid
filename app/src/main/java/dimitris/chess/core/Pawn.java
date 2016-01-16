package dimitris.chess.core;

/**
 * Created by dimitris on 4/3/15.
 */
class Pawn extends Piece {

    public Pawn(PieceColor color) {
        this.color = color;
        this.type = PieceType.Pawn;
    }

    public String getFANString(){
        return (this.color == PieceColor.White) ? Piece.White_Pawn_Unicode : Piece.Black_Pawn_Unicode;
    }

    public String getSANString(){
        return "";
    }
}
