package dimitris.chess.core;

/**
 * Created by dimitris on 12/29/15.
 */
public interface Board {

    void doMove(Move move);
    void undoMove(Move move);
    Piece getPieceAtSquare(String square);
}
