package dimitris.chess.core;

/**
 * Created by dimitris on 12/29/15.
 */
public class Move {
    String source;
    String destination;
    public boolean isCapture;

    public Move(String source, String destination){
        this.source = source;
        this.destination = destination;
        this.isCapture = false;
    }

}
