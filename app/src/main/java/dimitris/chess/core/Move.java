package dimitris.chess.core;

/**
 * Created by dimitris on 12/29/15.
 */
public class Move {
    String source;
    String destination;
    public boolean isCapture;
    public Piece movingPiece;
    public Piece capturedPiece;
    private final String MOVE_FORMAT = "%s%s%s";

    public Move(String source, String destination){
        this.source = source;
        this.destination = destination;
        this.isCapture = false;
    }

    public String printSAN(){
        String piece = movingPiece.getSANString();
        String capture = isCapture ? "x" : "";

        return String.format(MOVE_FORMAT,piece,capture,destination);
    }

    public boolean isWhiteMove(){
        return movingPiece.color == Piece.PieceColor.White;
    }
}
