package dimitris.chessboardutils;

import java.util.ArrayList;

import static dimitris.chessboardutils.Piece.PieceType.Pawn;

public class MovePrinter implements MoveObserver {

    public static final String PERIOD = ".";
    public static final String ELLIPSIS = "...";
    public static final String MOVE_FORMAT = "%d%s %s";
    private Board board;
    private ArrayList<String> movesPlayed;
    int fullMoveCounter = 1;

    public MovePrinter(Board board){
        this.board = board;
        this.board.registerObserver(this);
        movesPlayed = new ArrayList<>();
    }


    public String printMovesPlayed(){
        String result = "";

        for(String move : movesPlayed){
            result += move;
        }

        return result.trim();
    }

    private String printIndividualMove(Move moveToPrint){
        Square src = moveToPrint.sourceSquare;
        Square dest = moveToPrint.destinationSquare;
        String capture = moveToPrint.isCapture ? "x" : "";
        String status = moveToPrint.isMate ? "#" : moveToPrint.isCheck ? "+" : "";
        String movePrefix = (dest.piece.type == Pawn && moveToPrint.isCapture) ? src.getColumn() : dest.piece.getSANString();

        return movePrefix + capture + dest.toString() + status;
    }

    @Override
    public void onMovePlayed(Move movePlayed) {
        String toReturn = "";
        String moveDecorator = movePlayed.whiteMove ? PERIOD : ELLIPSIS;

        if(movePlayed.whiteMove || movesPlayed.size()== 0){
            String moveStr = String.format(MOVE_FORMAT, fullMoveCounter, moveDecorator,
                    printIndividualMove(movePlayed));
            toReturn+= moveStr + " ";
            fullMoveCounter++;
        } else {
            toReturn+= printIndividualMove(movePlayed) + " ";
        }
        movesPlayed.add(toReturn);
    }

    @Override
    public void onMoveRejected(Move moveRejected) {
        movesPlayed.remove(movesPlayed.size()-1);
    }
}