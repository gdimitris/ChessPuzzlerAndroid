package dimitris.android.app;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import dimitris.chess.core.PuzzleProvider;

/**
 * Created by dimitris on 30/03/16.
 */

public class PuzzleLoadingTask extends AsyncTask<Void, Void, PuzzleProvider> {

    private ProgressDialog dialog;

    public PuzzleLoadingTask(MainActivity activity) {
        dialog = new ProgressDialog(activity);
    }

    protected void onPreExecute() {
        dialog.setMessage("Loading your puzzles...");
        dialog.show();
    }

    @Override
    protected void onPostExecute(final PuzzleProvider success) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }

    }

    protected PuzzleProvider doInBackground(Void ...args) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       return new TemporaryPuzzleProvider();
    }
}