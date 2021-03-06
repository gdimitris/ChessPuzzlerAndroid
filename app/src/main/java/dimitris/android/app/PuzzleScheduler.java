package dimitris.android.app;

import android.content.Context;

import dimitris.android.app.db.ReviewScheduleFetchTask;
import dimitris.chess.core.ChessPuzzle;

/**
 * Created by dimitris on 4/10/16.
 */
public class PuzzleScheduler {
    private static final float MINIMUM_RECALL = 0.2f;
    private static final float RECALL_PENALTY = 0.1f;
    private static final float QUIT_PENALTY = 0.5f;

    private ChessPuzzle currentPuzzle;
    private Context context;
    private float recall;

    public PuzzleScheduler(Context context){
        this.context = context;
    }


    public void startGameWithPuzzle(ChessPuzzle puzzle) {
        this.currentPuzzle = puzzle;
        recall = 1.0f;
    }

    public void puzzleSolved(long timeElapsedForSolution) {
        ReviewScheduleFetchTask task = new ReviewScheduleFetchTask(context);
        task.execute(currentPuzzle.getReviewId());
    }

    public void puzzleQuit() {
        recall = QUIT_PENALTY;
    }

    public void wrongMovePlayed() {
        if (recall > MINIMUM_RECALL){
            recall-= RECALL_PENALTY;
        }
    }

    public float getRecall(){
        return recall;
    }
}
