package dimitris.chess.core;

import java.util.ArrayList;

import dimitris.android.app.*;
import dimitris.android.chessviews.DrawableBoard;
import dimitris.android.chessviews.Pieces.Pawn;
import dimitris.android.chessviews.SquareView;

public class MovePrinter{

    public static final String PERIOD = ".";
    public static final String ELLIPSIS = "...";
    public static final String MOVE_FORMAT = "%d%s %s";
    private ArrayList<String> movesPlayed;
    private Board board;
    int fullMoveCounter = 1;

    public MovePrinter(Board board){
        movesPlayed = new ArrayList<>();
    }


    public String printMovesPlayed(){
        String result = "";

        for(String move : movesPlayed){
            result += move;
        }

        return result.trim();
    }

//    private String printIndividualMove(Move moveToPrint){
////        SquareView src = moveToPrint.getSourceSquare();
////        SquareView dest = moveToPrint.getDestinationSquare();
//        String capture = moveToPrint.isCapture ? "x" : "";
////        String status = moveToPrint.isMate() ? "#" : moveToPrint.isCheck() ? "+" : "";
//        String movePrefix = ((dest.getPiece() instanceof Pawn) && moveToPrint.isCapture) ? src.getColumn() : dest.getPiece().toString();
//
//        return movePrefix + capture + dest.toString();
//    }
//
//    public void onMovePlayed(Move movePlayed) {
//        String toReturn = "";
//        String moveDecorator = movePlayed.isWhiteMove() ? PERIOD : ELLIPSIS;
//
//        if(movePlayed.isWhiteMove() || movesPlayed.size()== 0){
//            String moveStr = String.format(MOVE_FORMAT, fullMoveCounter, moveDecorator,
//                    printIndividualMove(movePlayed));
//            toReturn+= moveStr + " ";
//            fullMoveCounter++;
//        } else {
//            toReturn+= printIndividualMove(movePlayed) + " ";
//        }
//        movesPlayed.add(toReturn);
//    }

}