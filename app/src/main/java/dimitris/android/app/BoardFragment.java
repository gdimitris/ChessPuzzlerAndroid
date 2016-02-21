package dimitris.android.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dimitris.chesspuzzler.R;

import dimitris.android.chessviews.BoardContainerView;

/**
 * Created by dimitris on 2/21/16.
 */
public class BoardFragment extends Fragment {
    private BoardContainerView drawableBoard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.board_fragment_layout,container,false);
        drawableBoard = (BoardContainerView) view.findViewById(R.id.chessboard);
        return view;
    }


}
