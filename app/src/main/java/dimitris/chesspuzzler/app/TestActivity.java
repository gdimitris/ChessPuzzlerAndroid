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
            new HttpRequestTask(drawableBoard.getChessBoard()).execute("https://mitsos90.pythonanywhere.com/mate2");
        }
    }
}
