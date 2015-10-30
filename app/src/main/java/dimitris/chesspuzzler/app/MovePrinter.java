package dimitris.chesspuzzler.app;

import java.util.ArrayList;

import dimitris.android.chessviews.DrawableBoard;
import dimitris.android.chessviews.Pieces.Pawn;
import dimitris.android.chessviews.SquareView;

public class MovePrinter implements MoveObserver {

    public static final String PERIOD = ".";
    public static final String ELLIPSIS = "...";
    public static final String MOVE_FORMAT = "%d%s %s";
    private DrawableBoard board;
    private ArrayList<String> movesPlayed;
    int fullMoveCounter = 1;

    public MovePrinter(DrawableBoard board){
        this.board = board;
        this.board.registerMoveObserver(this);
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
        SquareView src = moveToPrint.getSourceSquare();
        SquareView dest = moveToPrint.getDestinationSquare();
        String capture = moveToPrint.isCapture() ? "x" : "";
        String status = moveToPrint.isMate() ? "#" : moveToPrint.isCheck() ? "+" : "";
        String movePrefix = ((dest.getPiece() instanceof Pawn) && moveToPrint.isCapture()) ? src.getColumn() : dest.getPiece().toString();

        return movePrefix + capture + dest.toString() + status;
    }

    public void onMovePlayed(Move movePlayed) {
        String toReturn = "";
        String moveDecorator = movePlayed.isWhiteMove() ? PERIOD : ELLIPSIS;

        if(movePlayed.isWhiteMove() || movesPlayed.size()== 0){
            String moveStr = String.format(MOVE_FORMAT, fullMoveCounter, moveDecorator,
                    printIndividualMove(movePlayed));
            toReturn+= moveStr + " ";
            fullMoveCounter++;
        } else {
            toReturn+= printIndividualMove(movePlayed) + " ";
        }
        movesPlayed.add(toReturn);
    }

    public void onMoveRejected(Move moveRejected) {
        movesPlayed.remove(movesPlayed.size()-1);
    }

    @Override
    public void onMoveDo(Move move) {
        onMovePlayed(move);
    }

    @Override
    public void onMoveUndo() {

    }

    @Override
    public void onMoveRedo() {

    }
}