package dimitris.chess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimitris on 1/1/16.
 */
public class Game {

    private PuzzleProvider puzzleProvider;
    private List<Move> playedMoves;
    private ChessPuzzle currentPuzzle;

    public Game(PuzzleProvider puzzleProvider) {
        this.puzzleProvider = puzzleProvider;
        playedMoves = new ArrayList<>();
    }

    public void start(){
        currentPuzzle = puzzleProvider.getNextPuzzle();
    }

    public void playMove(Move move) {
        playedMoves.add(move);
    }

    public boolean puzzleIsSolved() {
        String currentMoves = MovePrinter.printMoveList(playedMoves);
        String simplifiedSolution = currentPuzzle.solution.replaceAll("#","");
        simplifiedSolution = simplifiedSolution.replaceAll("\\+","");
        return currentMoves.equals(simplifiedSolution.trim());
    }
}
