package dimitris.android.app;

import android.app.Activity;
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
    private NewPuzzleCallback callback;

    public interface NewPuzzleCallback{
        public void initNewGame();
    }

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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            callback = (NewPuzzleCallback) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.getApplicationContext().toString() + " must implement NewPuzzleCallback interface");
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.nextPuzzleButton)
            callback.initNewGame();
    }

    public void enableNextPosButton() {
        nextPosButton.setEnabled(true);
    }

    public void disableNextPosButton(){
        nextPosButton.setEnabled(false);
    }
}
