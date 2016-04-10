package dimitris.android.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.dimitris.chesspuzzler.R;

import dimitris.chess.core.ChessPuzzle;
import dimitris.chess.core.Game;
import dimitris.chess.core.Move;
import dimitris.chess.core.PuzzleGameEventsListener;
import dimitris.chess.core.PuzzleProvider;

/**
 * Created by dimitris on 1/15/16.
 */
public class PlayPuzzleActivity extends Activity implements PuzzleGameEventsListener, BoardFragment.MovesCallBack, PuzzleComponentsFragment.GameEventsHandler{
    private Game game;
    private PuzzleProvider puzzleProvider;
    private BoardFragment boardFragment;
    private PuzzleScheduler scheduler;
    private PuzzleComponentsFragment puzzleComponentsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeUIComponents();
        processIntentExtras(getIntent().getExtras());
        scheduler = new PuzzleScheduler(puzzleProvider);
    }

    private void processIntentExtras(Bundle extras) {
        if(extras!=null){
            int requestedId = extras.getInt("requestedId");
            PuzzleLoadingTask task = new PuzzleLoadingTask(this,String.valueOf(requestedId));
            task.execute();
        }
    }

    private void initializeUIComponents() {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();
        setContentView(R.layout.board_layout);
        boardFragment = (BoardFragment) getFragmentManager().findFragmentById(R.id.board_fragment);
        puzzleComponentsFragment = (PuzzleComponentsFragment) getFragmentManager().findFragmentById(R.id.buttons_fragment);
    }

    public void initializeGameWithProvider(PuzzleProvider puzzleProvider){
        this.puzzleProvider = puzzleProvider;
        this.game = new Game(this.puzzleProvider);
        game.registerGameEventsListener(this);
        initNewGame();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onMoveDetected(String source, String dest){
        game.doMove(source,dest);
    }

    @Override
    public void onMoveDo(Move move) {
        Toast.makeText(this,game.printPlayedMoves(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoveUndo(Move move) {
        Toast.makeText(this,"Move is not correct", Toast.LENGTH_SHORT).show();
        boardFragment.undoMove();
        scheduler.wrongMovePlayed();
    }

    @Override
    public void onPuzzleGameStart(ChessPuzzle puzzle) {
        puzzleComponentsFragment.initialise();
        puzzleComponentsFragment.setCurrentPuzzle(puzzle);
        scheduler.startGameWithPuzzle(puzzle);
    }

    @Override
    public void onPuzzleGameSolved() {
        Toast.makeText(this, "Congrats! you solved it!", Toast.LENGTH_SHORT).show();
        puzzleComponentsFragment.enableNextPosButton();
        scheduler.puzzleSolved();
    }

    @Override
    public void onPuzzleGameQuit() {
        puzzleComponentsFragment.showSolutionForPuzzle();
        puzzleComponentsFragment.enableNextPosButton();
        scheduler.quitPuzzle();
    }

    @Override
    public void initNewGame() {
        game.start();
        boardFragment.setCurrentPuzzle(game.getCurrentPuzzle());
        puzzleComponentsFragment.disableNextPosButton();
    }

    @Override
    public void quitGame() {
        game.quit();
    }
}
