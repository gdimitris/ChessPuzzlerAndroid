package dimitris.chess.core;

import java.util.List;

public class MovePrinter{

    public static final String PERIOD = ".";
    public static final String ELLIPSIS = "...";

    public static String printMoveList(List<Move> moveList) {
        String result = "";
        int fullMoveCounter = 0;

        if(moveList.isEmpty())
            return result;

        if( moveList.get(0).isWhiteMove() ){
            result = printMovesStartingWithWhite(moveList, fullMoveCounter);
        } else {
            result = decorateFirstBlackMove(moveList, result, ++fullMoveCounter);
            result+= printMovesStartingWithWhite(moveList.subList(1,moveList.size()), fullMoveCounter);
        }

        return result.trim();
    }

    private static String decorateFirstBlackMove(List<Move> moveList, String result, int fullMoveCounter) {
        result+= " "+fullMoveCounter + ELLIPSIS;
        result+= " "+ moveList.get(0).printSAN();
        return result;
    }

    private static String printMovesStartingWithWhite(List<Move> moveList, int currentMoveIndex){
        String result = "";
        int fullMoveCounter = currentMoveIndex;

        for(int i=0; i<moveList.size(); i++){
            if( i % 2 == 0 ){
                fullMoveCounter++;
                result += " "+fullMoveCounter+ PERIOD;
            }

            result += " "+ moveList.get(i).printSAN();
        }
        return result;
    }
}