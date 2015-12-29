package dimitris.chess.core;

/**
 * Created by dimitris on 10/24/15.
 */
public class ChessPuzzle {
    public String description;
    public String fen;
    public String solution;
    public String id;

    @Override
    public String toString() {
        String result = "";
        result += "Description: " + description + "\n";
        result += "Id: " + id + "\n";
        result += "FEN: " + fen + "\n";
        result += "Solution: " + solution;
        return result;
    }
}
