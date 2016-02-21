package dimitris.android.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dimitris.chesspuzzler.R;

/**
 * Created by enviouscreep on 2/21/16.
 */
public class ButtonsFragment extends Fragment implements View.OnClickListener{

    private Button changePosButton;
    private Button nextPosButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buttons_fragment_layout,container,false);
        changePosButton = (Button) view.findViewById(R.id.changePositionButton);
        changePosButton.setOnClickListener(this);
        nextPosButton = (Button) view.findViewById(R.id.nextPuzzleButton);
        nextPosButton.setOnClickListener(this);
        nextPosButton.setEnabled(false);
        return view;
    }


    @Override
    public void onClick(View v) {
//        if(v.getId()==R.id.nextPuzzleButton)
//            initGame();
    }
}
