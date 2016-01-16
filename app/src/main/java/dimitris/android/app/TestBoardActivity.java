package dimitris.android.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.dimitris.chesspuzzler.R;

import dimitris.android.chessviews.BoardContainerView;
import dimitris.chess.core.Game;
import dimitris.chess.core.PuzzleProvider;

/**
 * Created by dimitris on 1/15/16.
 */
public class TestBoardActivity extends Activity implements View.OnClickListener {

    private BoardContainerView drawableBoard;
    private Button changePosButton;
    private PuzzleProvider puzzleProvider;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();

        setContentView(R.layout.board_layout);
        changePosButton = (Button) findViewById(R.id.changePositionButton);
        changePosButton.setOnClickListener(this);
        drawableBoard = (BoardContainerView) findViewById(R.id.chessboard);
        puzzleProvider = new TemporaryPuzzleProvider();
        game = new Game(puzzleProvider);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.changePositionButton){
            game.start();
            drawableBoard.setCurrentPuzzle(game.getCurrentPuzzle());
        }
    }
}
