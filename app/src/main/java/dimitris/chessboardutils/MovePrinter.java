package dimitris.chessboardutils;

import java.util.ArrayList;

import static dimitris.chessboardutils.Piece.PieceType.Pawn;

public class MovePrinter implements MoveObserver {

    public static final String PERIOD = ".";
    public static final String ELLIPSIS = "...";
    public static final String MOVE_FORMAT = "%d%s %s";
    private int fullMoveCounter;
    private Board board;
    private ArrayList<Move> movesPlayed;

    public MovePrinter(Board board){
        this.fullMoveCounter = 1;
        this.board = board;
        this.board.registerMoveObserver(this);
        movesPlayed = new ArrayList<>();
    }


    public String printMovesPlayed(){
        String toReturn = "";

        for (int i=0; i< movesPlayed.size(); i++){
            Move currentMove = movesPlayed.get(i);
            String moveDecorator = currentMove.whiteMove ? PERIOD : ELLIPSIS;

            if(currentMove.whiteMove || i== 0){
                String moveStr = String.format(MOVE_FORMAT, fullMoveCounter,
                        moveDecorator, printIndividualMove(currentMove));
                toReturn+= moveStr + " ";
                fullMoveCounter++;
            } else {
                toReturn+= printIndividualMove(currentMove) + " ";
            }
        }

        return toReturn.trim();
    }

    private String printIndividualMove(Move moveToPrint){
        Square src = moveToPrint.sourceSquare;
        Square dest = moveToPrint.destinationSquare;
        String capture = moveToPrint.isCapture ? "x" : "";
        String status = moveToPrint.isMate ? "#" : moveToPrint.isCheck ? "+" : "";
        String movePrefix = src.piece.type == Pawn && moveToPrint.isCapture ? src.getColumn() : src.piece.getSANString();

        return movePrefix + capture + dest.toString() + status;
    }

    @Override
    public void onMovePlayed(Move movePlayed) {
        movesPlayed.add(movePlayed);
    }

    @Override
    public void onMoveRejected(Move moveRejected) {
        movesPlayed.remove(movesPlayed.size()-1);
    }
}