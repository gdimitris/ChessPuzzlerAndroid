package dimitris.chessboardutils;

public class MoveFactory {

    private Board board;

    public MoveFactory(Board board){
        this.board = board;
    }

    public Move createMove(String sourceSquare, String destSquare){
        Square src = board.getSquareAt(sourceSquare);
        Square dest = board.getSquareAt(destSquare);
        Move toReturn = new Move(src, dest);
        toReturn.isCapture = !(dest.piece instanceof NullPiece);
        toReturn.whiteMove = (src.piece.color == Piece.PieceColor.White) ? true : false;

        return toReturn;
    }
}
