package dimitris.android.chessviews;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dimitris.chesspuzzler.R;


public class ChessBoardFragment extends Fragment {

    private BoardContainerView chessBoardRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.board_layout, container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        chessBoardRef = (BoardContainerView) getActivity().findViewById(R.id.chessboard);
    }
}
