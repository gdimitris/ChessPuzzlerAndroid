package dimitris.chess.core;

/**
 * Created by dimitris on 1/11/16.
 */
public class MoveFactory {

    private Board board;

    public MoveFactory(Board board) {
        this.board = board;
    }

    public Move createMove(String source, String destination) {
        return new Move(source,destination);
    }
}
