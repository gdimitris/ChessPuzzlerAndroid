package dimitris.android.chessviews;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dimitris.chesspuzzler.R;

public class ChessBoardControlsFragment extends Fragment implements View.OnTouchListener {

    private BoardContainerView boardContainerView;
    private Button undoButton;
    private Button redoButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.board_controls, container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return false;

//        if (v.equals(undoButton)) {
//            boardView.getChessBoard().onMoveUndo();
//            boardView.invalidate();
//            return true;
//        } else if (v.equals(redoButton)) {
//            boardView.getChessBoard().onMoveRedo();
//            boardView.invalidate();
//            return true;
//        }
        return false;
    }

    private void init() {

        undoButton = (Button) getActivity().findViewById(R.id.undoButton);
        undoButton.setOnTouchListener(this);
        redoButton = (Button) getActivity().findViewById(R.id.redoButton);
        redoButton.setOnTouchListener(this);
        boardContainerView = (BoardContainerView) getActivity().findViewById(R.id.chessboard);
//        MovesTextView movesTextView = (MovesTextView) getActivity().findViewById(R.id.movesTextView);
//        movesTextView.setBoardViewRef(boardView);
    }
}
