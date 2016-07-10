package dimitris.android.app;

import dimitris.chess.core.ChessPuzzle;
import dimitris.chess.core.PuzzleProvider;

/**
 * Created by dimitris on 4/10/16.
 */
public class PuzzleScheduler {

    public static final float MINIMUM_RECALL = 0.2f;
    public static final float RECALL_PENALTY = 0.1f;
    private PuzzleProvider provider;
    private ChessPuzzle currentPuzzle;
    private float recall;

    public PuzzleScheduler(PuzzleProvider provider){
        this.provider = provider;
    }


    public void startGameWithPuzzle(ChessPuzzle puzzle) {
        this.currentPuzzle = puzzle;
        recall = 1.0f;
    }

    public void puzzleSolved(long timeElapsedForSolution) {

    }

    public void quitPuzzle() {

    }

    public void wrongMovePlayed() {
        if (recall > MINIMUM_RECALL){
            recall-= RECALL_PENALTY;
        }
    }
}
