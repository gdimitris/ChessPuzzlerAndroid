package dimitris.chess.core;

/**
 * Created by dimitris on 12/29/15.
 */
class Move {
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
        piece = (piece.equals("") && isCapture) ? source.substring(0,1) : piece ;
        String capture = isCapture ? "x" : "";

        return String.format(MOVE_FORMAT,piece,capture,destination);
    }

    public boolean isWhiteMove(){
        return movingPiece.color == Piece.PieceColor.White;
    }
}
