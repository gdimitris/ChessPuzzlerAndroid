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
        gameStarted();
    }

    public ChessPuzzle getCurrentPuzzle(){
        return currentPuzzle;
    }

    public void doMove(String sourceSquare, String destinationSquare){
        Move toPlay = moveFactory.createMove(sourceSquare,destinationSquare);
        playedMoves.add(toPlay);
        board.doMove(toPlay);
        moveDone(toPlay);
        checkCorrectMovesPlayed();
        checkGameStatus();
    }

    private void checkCorrectMovesPlayed() {
        if(!playedMovesAreCorrect())
            undoMove();
    }

    public void undoMove(){
        Move toUndo = playedMoves.remove(playedMoves.size()-1);
        board.undoMove(toUndo);
        moveUndone(toUndo);
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

    private void checkGameStatus(){
        if(puzzleIsSolved())
            puzzleSolved();
    }

    private void puzzleSolved(){
        for(GameEventsListener listener : eventsListeners)
            listener.onGameEnd();
    }

    @Override
    public void registerGameEventsListener(GameEventsListener toRegister) {
        eventsListeners.add(toRegister);
    }

    @Override
    public void removeGameEventsListener(GameEventsListener toRemove) {
        eventsListeners.remove(toRemove);
    }

    private void gameStarted() {
        for(GameEventsListener listener : eventsListeners)
            listener.onGameStart();
    }

    private void moveDone(Move toPlay) {
        for(GameEventsListener listener : eventsListeners)
            listener.onMoveDo(toPlay);
    }

    private void moveUndone(Move toUndo) {
        for(GameEventsListener listener : eventsListeners)
            listener.onMoveUndo(toUndo);
    }

}
