package dimitris.chesspuzzler.app;

import java.util.ArrayList;

public class MoveManager {

    private ArrayList<Move> moveList;

    public MoveManager(){
        this.moveList = new ArrayList<>();

    }

    public void executeMove(Move toExecute){
        moveList.add(toExecute);
        toExecute.execute();
    }

    public String printMovesPlayed(){
        return "";
    }
}
