package dimitris.chess.core;

/**
 * Created by dimitris on 10/24/15.
 */
public class ChessPuzzle {
    public String description;
    public String fen;
    public String solution;
    public String id;
    public String reviewId;

    public ChessPuzzle(String fen, String solution){
        this.id = "";
        this.description = "";
        this.fen = fen;
        this.solution = solution;
    }

    public ChessPuzzle(String id, String description, String fen, String solution, String reviewId){
        this.id = id;
        this.description = description;
        this.fen = fen;
        this.solution = solution;
        this.reviewId = reviewId;
    }

    public boolean isWhitePuzzle(){
        String[] tokens = fen.split(" ");
        return tokens[1].equals("w");
    }
}
