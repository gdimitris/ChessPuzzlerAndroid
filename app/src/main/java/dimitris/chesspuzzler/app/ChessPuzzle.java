package dimitris.chesspuzzler.app;

/**
 * Created by dimitris on 10/24/15.
 */
public class ChessPuzzle {
    String description;
    String fen;
    String solution;
    String id;

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
