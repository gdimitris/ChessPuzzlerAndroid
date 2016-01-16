package dimitris.chess.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimitris on 1/1/16.
 */
public class Game implements GameEventsDispatcher {

    private PuzzleProvider puzzleProvider;
    private List<Move> playedMoves;
    private ChessPuzzle currentPuzzle;
    private Board board;
    private MoveFactory moveFactory;
    private List<GameEventsListener> eventsListeners;

    public Game(PuzzleProvider puzzleProvider) {
        this.puzzleProvider = puzzleProvider;
        eventsListeners = new ArrayList<>();
        playedMoves = new ArrayList<>();
        board = new Bitboard();
        moveFactory = new MoveFactory(board);
    }

    public void start(){
        currentPuzzle = puzzleProvider.getNextPuzzle();
        board.setPosition(currentPuzzle.fen);
    }

    public ChessPuzzle getCurrentPuzzle(){
        return currentPuzzle;
    }

    public void playMove(String sourceSquare, String destinationSquare){
        Move toPlay = moveFactory.createMove(sourceSquare,destinationSquare);
        playedMoves.add(toPlay);
        board.doMove(toPlay);
    }

    public boolean puzzleIsSolved() {
        String currentMoves = MovePrinter.printMoveList(playedMoves);
        String simplifiedSolution = currentPuzzle.solution.replaceAll("#","").replaceAll("\\+","");

        return currentMoves.equals(simplifiedSolution.trim());
    }

    public boolean playedMovesAreCorrect() {
        String currentMoves = MovePrinter.printMoveList(playedMoves);
        String simplifiedSolution = currentPuzzle.solution.replaceAll("#","").replaceAll("\\+", "");

        return simplifiedSolution.startsWith(currentMoves);
    }

    @Override
    public void registerGameEventsListener(GameEventsListener toRegister) {
        eventsListeners.add(toRegister);
    }

    @Override
    public void removeGameEventsListener(GameEventsListener toRemove) {
        eventsListeners.remove(toRemove);
    }
}
