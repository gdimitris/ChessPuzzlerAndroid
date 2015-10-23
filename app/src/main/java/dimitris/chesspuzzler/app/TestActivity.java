package dimitris.chesspuzzler.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.dimitris.chesspuzzler.R;

import dimitris.android.chessviews.BoardContainerView;

/**
 * Created by dimitris on 10/18/15.
 */
public class TestActivity extends Activity implements View.OnClickListener{

    private Button changePosButton;
    private BoardContainerView drawableBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();

        setContentView(R.layout.board_layout);
        changePosButton = (Button) findViewById(R.id.changePositionButton);
        changePosButton.setOnClickListener(this);
        drawableBoard = (BoardContainerView) findViewById(R.id.chessboard);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.changePositionButton){
//            drawableBoard.setBoardPosition("r1b1kb1r/pppp1ppp/5q2/4n3/3KP3/2N3PN/PPP4P/R1BQ1B1R b kq - 0 1");
//            drawableBoard.invalidate();
            new HttpRequestTask().execute("http://www.android.com");
        }
    }
}
