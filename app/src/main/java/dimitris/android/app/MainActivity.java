package dimitris.android.app;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.dimitris.chesspuzzler.R;

import dimitris.android.chessviews.BoardContainerView;
import dimitris.chess.core.Game;
import dimitris.chess.core.GameEventsListener;
import dimitris.chess.core.PuzzleProvider;

/**
 * Created by dimitris on 1/15/16.
 */
public class MainActivity extends Activity implements GameEventsListener, BoardFragment.MovesCallBack, ButtonsFragment.NewPuzzleCallback {
    private Game game;
    private PuzzleProvider puzzleProvider;
    private BoardFragment boardFragment;
    private ButtonsFragment buttonsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();
        setContentView(R.layout.board_layout);

        this.puzzleProvider = new TemporaryPuzzleProvider();
        this.game = new Game(puzzleProvider);
        game.registerGameEventsListener(this);
        boardFragment = (BoardFragment) getFragmentManager().findFragmentById(R.id.board_fragment);
        buttonsFragment = (ButtonsFragment) getFragmentManager().findFragmentById(R.id.buttons_fragment);
//        drawableBoard.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                initGame();
//            }
//        });
    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        initNewGame();
    }

    @Override
    public void onMoveDetected(String source, String dest){
        game.doMove(source,dest);
    }

    @Override
    public void onMoveDo(dimitris.chess.core.Move move) {
        //Toast.makeText(this,game.printPlayedMoves(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoveUndo(dimitris.chess.core.Move move) {
        Toast.makeText(this,"Move is not correct", Toast.LENGTH_SHORT).show();
        //drawableBoard.undoMove();
        boardFragment.undoMove();
    }

    @Override
    public void onGameStart() {
        //Toast.makeText(this, "Game Started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGameEnd() {
        Toast.makeText(this, "Congrats! you solved it!", Toast.LENGTH_SHORT).show();
        //nextPosButton.setEnabled(true);
        buttonsFragment.enableNextPosButton();
    }

    @Override
    public void initNewGame() {
        game.start();
        //drawableBoard.setCurrentPuzzle(game.getCurrentPuzzle());
        //nextPosButton.setEnabled(false);
        boardFragment.setCurrentPuzzle(game.getCurrentPuzzle());
        buttonsFragment.disableNextPosButton();
    }
}
