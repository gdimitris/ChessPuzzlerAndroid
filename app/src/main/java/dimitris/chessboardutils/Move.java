package dimitris.chessboardutils;

/**
 * Created by dimitris on 4/7/15.
 */
public class Move {

    public String sourceSquare;
    public String destinationSquare;
    public boolean whiteMove = true;
    public boolean isCapture = false;

    public Move(String sourceSquare, String destinationSquare) {
        this.sourceSquare = sourceSquare;
        this.destinationSquare = destinationSquare;
    }

    @Override
    public boolean equals(Object o) {
        Move toCheck = (Move) o;
        return (this.sourceSquare == toCheck.sourceSquare) &&
                (this.destinationSquare == toCheck.destinationSquare);
    }
}
