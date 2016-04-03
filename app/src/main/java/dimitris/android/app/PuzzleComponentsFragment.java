package dimitris.android.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dimitris.chesspuzzler.R;

import dimitris.chess.core.ChessPuzzle;

/**
 * Created by dimitris on 2/21/16.
 */
public class PuzzleComponentsFragment extends Fragment implements View.OnClickListener{

    private Button toggleAnnotationsButton;
    private Button flipBoardButton;
    private Button nextPosButton;
    private Button showSolutionButton;
    private TextView solutionTextView;
    private TextView moveIndicatorTextView;
    private GameEventsHandler gameEventsHandler;
    private String puzzleSolution;

    public interface GameEventsHandler {
        void initNewGame();
        void quitGame();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.components_fragment_layout,container,false);
        initializeComponents(view);
        setListenersForButtons();
        initialise();
        return view;
    }

    private void initializeComponents(View view) {
        toggleAnnotationsButton = (Button) view.findViewById(R.id.toggleAnnotations);
        nextPosButton = (Button) view.findViewById(R.id.nextPuzzleButton);
        flipBoardButton = (Button) view.findViewById(R.id.flipBoard);
        showSolutionButton = (Button) view.findViewById(R.id.showSolution);
        solutionTextView = (TextView) view.findViewById(R.id.solutionText);
        moveIndicatorTextView = (TextView) view.findViewById(R.id.moveIndicator);
    }

    private void setListenersForButtons() {
        nextPosButton.setOnClickListener(this);
        toggleAnnotationsButton.setOnClickListener(this);
        flipBoardButton.setOnClickListener(this);
        showSolutionButton.setOnClickListener(this);
    }

    public void setCurrentPuzzle(ChessPuzzle puzzle){
        puzzleSolution = puzzle.solution;
        String indicatorText = puzzle.isWhitePuzzle() ? "White to play" : "Black to play";
        moveIndicatorTextView.setText(indicatorText);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            gameEventsHandler = (GameEventsHandler) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.getApplicationContext().toString() + " must implement NewPuzzleCallback interface");
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.nextPuzzleButton)
            gameEventsHandler.initNewGame();
        else if (v.getId() == R.id.toggleAnnotations)
            Toast.makeText(getActivity(),"Not Supported yet!",Toast.LENGTH_SHORT).show();
        else if (v.getId() == R.id.showSolution)
            gameEventsHandler.quitGame();
        else
            Toast.makeText(getActivity(),"Not Supported yet!",Toast.LENGTH_SHORT).show();
    }

    public void showSolutionForPuzzle(){
        solutionTextView.setText("Puzzle Solution: "+puzzleSolution);
    }

    public void clearSolutionText(){
        solutionTextView.setText("");
    }

    public void enableNextPosButton() {
        nextPosButton.setEnabled(true);
    }

    public void disableNextPosButton(){
        nextPosButton.setEnabled(false);
    }

    public void initialise() {
        clearSolutionText();
        disableNextPosButton();
    }

}
