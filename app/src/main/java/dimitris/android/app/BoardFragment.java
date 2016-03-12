package dimitris.android.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        drawableBoard = (BoardContainerView) view.findViewById(R.id.chessboard);
        return view;
    }

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
