package dimitris.android.app;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

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
            if(isNetworkAvailable())
                new HttpRequestTask(drawableBoard).execute("https://mitsos90.pythonanywhere.com/mate2");
            else
                Toast.makeText(getApplicationContext(), "Please make sure you have an active Internet connection",Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo!=null && activeNetworkInfo.isConnected();
    }
}
