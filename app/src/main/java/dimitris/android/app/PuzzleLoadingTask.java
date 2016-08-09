package dimitris.android.app;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import dimitris.chess.core.ChessPuzzle;
import dimitris.chess.core.PuzzleProvider;

import static dimitris.android.app.db.PuzzleDBTable.PuzzleColumns.CONTENT_URI;
import static dimitris.android.app.db.PuzzleDBTable.PuzzleColumns.PUZZLE_DESCRIPTION_COLUMN_NUM;
import static dimitris.android.app.db.PuzzleDBTable.PuzzleColumns.PUZZLE_FEN_COLUMN_NUM;
import static dimitris.android.app.db.PuzzleDBTable.PuzzleColumns.PUZZLE_ID_COLUMN_NUM;
import static dimitris.android.app.db.PuzzleDBTable.PuzzleColumns.PUZZLE_REVIEW_ID_COLUMN_NUM;
import static dimitris.android.app.db.PuzzleDBTable.PuzzleColumns.PUZZLE_SOLUTION_COLUMN_NUM;

/**
 * Created by dimitris on 30/03/16.
 */

public class PuzzleLoadingTask extends AsyncTask<Void, Void, PuzzleProvider> {

    private ProgressDialog dialog;
    private PlayPuzzleActivity activity;
    private String requestedId;

    public PuzzleLoadingTask(PlayPuzzleActivity parent, String requestedId) {
        this.activity = parent;
        this.requestedId = requestedId;
        dialog = new ProgressDialog(activity);
    }

    protected void onPreExecute() {
        dialog.setMessage("Preparing your puzzles...");
        dialog.show();
    }

    @Override
    protected void onPostExecute(final PuzzleProvider provider) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        activity.initializeGameWithProvider(provider);
    }

    protected PuzzleProvider doInBackground(Void ...args) {
        List<ChessPuzzle> puzzles = new ArrayList<>();
        Cursor cursor = getRequestedPuzzles();

        if (cursor.moveToFirst()) {
            do {
                puzzles.add(createPuzzleFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return new ListPuzzleProvider(puzzles);
    }

    private ChessPuzzle createPuzzleFromCursor(Cursor c){
        String id = c.getString(PUZZLE_ID_COLUMN_NUM);
        String description = c.getString(PUZZLE_DESCRIPTION_COLUMN_NUM);
        String fen = c.getString(PUZZLE_FEN_COLUMN_NUM);
        String solution = c.getString(PUZZLE_SOLUTION_COLUMN_NUM);
        String reviewId = c.getString(PUZZLE_REVIEW_ID_COLUMN_NUM);

        return new ChessPuzzle.ChessPuzzleBuilder(id,fen,solution)
                                .setDescription(description)
                                .setReviewId(reviewId)
                                .build();
    }

    private Cursor getRequestedPuzzles() {
        ContentResolver contentResolver = activity.getContentResolver();
        Uri uri = CONTENT_URI.buildUpon().appendPath("collection").appendPath(requestedId).build();

        return contentResolver.query(uri,null,null,null,null);
    }
}