package dimitris.android.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dimitris.chesspuzzler.R;

import dimitris.android.chessviews.BoardContainerView;
import dimitris.chess.core.ChessPuzzle;

/**
 * Created by dimitris on 2/21/16.
 */
public class BoardFragment extends Fragment {
    private BoardContainerView drawableBoard;
    private MovesCallBack callBack;

    public interface MovesCallBack {
        void onMoveDetected(String source, String dest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.board_fragment_layout,container,false);
        //setCorrectMargins(view);
        drawableBoard = (BoardContainerView) view.findViewById(R.id.chessboard);
        return view;
    }
//
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"

//    private void setCorrectMargins(View view){
//        LinearLayout layout = (LinearLayout) view.findViewById(R.id.board_layout);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.setMargins(50, 50, 50, 50);
//        layout.setLayoutParams(params);
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            callBack = (MovesCallBack) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.getApplicationContext().toString() + " must implement MovesCallback interface");
        }
    }

    public void undoMove() {
        drawableBoard.undoMove();
    }

    public void setCurrentPuzzle(ChessPuzzle currentPuzzle) {
        drawableBoard.setCurrentPuzzle(currentPuzzle);
    }

}
