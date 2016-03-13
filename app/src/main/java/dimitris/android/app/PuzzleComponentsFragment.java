package dimitris.android.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dimitris.chesspuzzler.R;

import static dimitris.android.app.DBPuzzleProviderContract.PuzzleTableColumns.COLUMN_DESCRIPTION;
import static dimitris.android.app.DBPuzzleProviderContract.PuzzleTableColumns.COLUMN_FEN;
import static dimitris.android.app.DBPuzzleProviderContract.PuzzleTableColumns.COLUMN_SOLUTION;
import static dimitris.android.app.DBPuzzleProviderContract.PuzzleTableColumns.PUZZLE_DESCRIPTION_COLUMN_NUM;
import static dimitris.android.app.DBPuzzleProviderContract.PuzzleTableColumns.PUZZLE_FEN_COLUMN_NUM;
import static dimitris.android.app.DBPuzzleProviderContract.PuzzleTableColumns.PUZZLE_ID_COLUMN_NUM;
import static dimitris.android.app.DBPuzzleProviderContract.PuzzleTableColumns.PUZZLE_SOLUTION_COLUMN_NUM;

/**
 * Created by dimitris on 2/21/16.
 */
public class PuzzleComponentsFragment extends Fragment implements View.OnClickListener{

    private Button toggleAnnotationsButton;
    private Button flibBoardButton;
    private Button nextPosButton;
    private Button showSolutionButton;
    private TextView solutionTextView;
    private GameEventsHandler gameEventsHandler;
    private String puzzleSolution;

    public interface GameEventsHandler {
        void initNewGame();
        void quitGame();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buttons_fragment_layout,container,false);

        initializeComponents(view);
        setListenersForButtons();
        initialise();
        return view;
    }

    private void initializeComponents(View view) {
        toggleAnnotationsButton = (Button) view.findViewById(R.id.toggleAnnotations);
        nextPosButton = (Button) view.findViewById(R.id.nextPuzzleButton);
        flibBoardButton = (Button) view.findViewById(R.id.flipBoard);
        showSolutionButton = (Button) view.findViewById(R.id.showSolution);
        solutionTextView = (TextView) view.findViewById(R.id.solutionText);
    }

    private void setListenersForButtons() {
        nextPosButton.setOnClickListener(this);
        toggleAnnotationsButton.setOnClickListener(this);
        flibBoardButton.setOnClickListener(this);
        showSolutionButton.setOnClickListener(this);
    }

    public void setPuzzleSolution(String solution){
        this.puzzleSolution = solution;
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
            queryForExistingPuzzles();
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

    private void insertTestEntry(){
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, "Henry Buckle vs NN, London, 1840");
        values.put(COLUMN_FEN,"r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0");
        values.put(COLUMN_SOLUTION,"1. Nf6+ gxf6 2. Bxf7#");

        ContentResolver resolver = getActivity().getContentResolver();
        resolver.insert(PuzzleContentProvider.CONTENT_URI,values);
    }

    private void queryForExistingPuzzles(){
        ContentResolver resolver = getActivity().getContentResolver();
        Cursor c = resolver.query(PuzzleContentProvider.CONTENT_URI,null,null,null,null);
        printPuzzlesFromCursor(c);
    }

    private void queryForPuzzleWithID(){
        ContentResolver resolver = getActivity().getContentResolver();
        Uri uri = Uri.withAppendedPath(PuzzleContentProvider.CONTENT_URI,"1");

        Cursor c = resolver.query(uri,null,null,null,null);
        printPuzzlesFromCursor(c);
    }

    private void printPuzzlesFromCursor(Cursor c) {
        if (c.moveToFirst()){
            do{
                String id = c.getString(PUZZLE_ID_COLUMN_NUM);
                String description = c.getString(PUZZLE_DESCRIPTION_COLUMN_NUM);
                String fen = c.getString(PUZZLE_FEN_COLUMN_NUM);
                String solution = c.getString(PUZZLE_SOLUTION_COLUMN_NUM);

                Log.e("Read Query", "Puzzle id: " + id);
                Log.e("Read Query", "Description: " + description);
                Log.e("Read Query", "Fen: "+fen);
                Log.e("Read Query", "Solution: "+solution);

            } while (c.moveToNext());
        }
        c.close();
    }
}
