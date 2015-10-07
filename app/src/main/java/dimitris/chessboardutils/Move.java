package dimitris.chessboardutils;

/**
 * Created by dimitris on 4/7/15.
 */
public class Move {

    public Square sourceSquare;
    public Square destinationSquare;
    public boolean whiteMove = true;
    public boolean isCapture = false;
    public boolean isCheck = false;
    public boolean isMate = false;

    public Move(Square sourceSquare, Square destinationSquare) {
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
