package dimitris.chesspuzzler.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.dimitris.chesspuzzler.R;

/**
 * Created by dimitris on 10/18/15.
 */
public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();

        setContentView(R.layout.board_layout);

    }
}
