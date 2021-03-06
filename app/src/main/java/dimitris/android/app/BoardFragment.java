package dimitris.android.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dimitris.chesspuzzler.R;

import dimitris.android.chessviews.BoardContainerView;
import dimitris.chess.core.ChessPuzzle;

/**
 * Created by dimitris on 2/21/16.
 */
public class BoardFragment extends Fragment {
    private BoardContainerView drawableBoard;
    private TextView titleTextView;

    public interface MovesCallBack {
        void onMoveDetected(String source, String dest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.board_fragment_layout,container,false);
        drawableBoard = (BoardContainerView) view.findViewById(R.id.chessboard);
        titleTextView = (TextView) view.findViewById(R.id.title);
        return view;
    }

    public void undoMove() {
        drawableBoard.undoMove();
    }

    public void setCurrentPuzzle(ChessPuzzle currentPuzzle) {
        drawableBoard.setCurrentPuzzle(currentPuzzle);
        String displayString = "(" + currentPuzzle.getId() +") "  +currentPuzzle.getDescription();
        titleTextView.setText(displayString);
    }

}
