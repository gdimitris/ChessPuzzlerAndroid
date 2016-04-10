package dimitris.android.app.db;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import dimitris.android.app.MainActivity;

import static dimitris.android.app.db.PuzzleCollectionDBTable.PuzzleCollectionColumns;
import static dimitris.android.app.db.PuzzleDBTable.PuzzleColumns;
import static dimitris.android.app.db.ReviewDBTable.ReviewColumns;

/**
 * Created by dimitris on 4/3/16.
 */
public class PopulateDbTask extends AsyncTask<Void,Void,Void> {

    private static String ASSET_DIR = "Puzzles/";
    private static String[] COLLECTION_FILENAMES = {"m8n2.txt","m8n3.txt","m8n4.txt"};
    private static String[] COLLECTION_DESCRIPTIONS = {"Mate in 2","Mate in 3","Mate in 4"};
    private final String tag = "PopulateDBTask";

    private AssetManager assetManager;
    private ProgressDialog progressDialog;
    private MainActivity activity;

    public PopulateDbTask(MainActivity activity){
        this.activity = activity;
        this.assetManager = this.activity.getAssets();
        progressDialog = new ProgressDialog(this.activity);
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Populating Database...");
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {

        for(int collection = 0; collection < COLLECTION_FILENAMES.length; collection++){
            String currentFilename = ASSET_DIR + COLLECTION_FILENAMES[collection];
            Uri collectionUri = createDbCollection(COLLECTION_DESCRIPTIONS[collection]);
            String collectionId = PuzzleCollectionColumns.getCollectionIdFromUri(collectionUri);
            createPuzzlesForCollection(currentFilename, collectionId);
        }

        return null;
    }

    private Uri createDbCollection(String collectionDescription) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PuzzleCollectionColumns.COLUMN_DESCRIPTION,collectionDescription);
        return insertInDB(PuzzleCollectionColumns.CONTENT_URI,contentValues);
    }

    private Uri insertInDB(Uri uri, ContentValues contentValues){
        ContentResolver contentResolver = activity.getContentResolver();
        return contentResolver.insert(uri,contentValues);
    }

    private void createPuzzlesForCollection(String collectionFilename, String collectionId) {
        BufferedReader reader;
        InputStream inputStream;

        try {
            inputStream = assetManager.open(collectionFilename);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            createPuzzlesFromFile(reader, collectionId);
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createPuzzlesFromFile(BufferedReader reader, String collectionId) throws IOException {
        String line = reader.readLine();
        while(line !=null){
            if (!lineIsEmpty(line)) {
                String description = line;
                String fen = reader.readLine();
                String solution = reader.readLine();

                Uri reviewUri = createDefaultReviewEntry();
                String reviewId = ReviewColumns.getReviewIdFromUri(reviewUri);
                ContentValues values = createContentValuesForPuzzle(description,fen,solution, reviewId,collectionId);
                insertInDB(PuzzleColumns.CONTENT_URI,values);
            }
            line = reader.readLine();
        }
    }

    private Uri createDefaultReviewEntry(){
        ContentValues values = createContentValuesForDefaultReview();
        return insertInDB(ReviewColumns.CONTENT_URI, values);
    }

    private ContentValues createContentValuesForDefaultReview(){
        ContentValues values = new ContentValues();
        values.put(ReviewColumns.COLUMN_EASINESS_FACTOR,0.6f);
        values.put(ReviewColumns.COLUMN_REVIEW_INTERVAL,1);

        return values;
    }

    private ContentValues createContentValuesForPuzzle(String description, String fen, String solution,String reviewId ,String collectionId) {
        ContentValues values = new ContentValues();
        values.put(PuzzleColumns.COLUMN_DESCRIPTION,description);
        values.put(PuzzleColumns.COLUMN_FEN, fen);
        values.put(PuzzleColumns.COLUMN_SOLUTION, solution);
        values.put(PuzzleColumns.COLUMN_REVIEW_ID, reviewId);
        values.put(PuzzleColumns.COLUMN_COLLECTION_ID, collectionId);

        return values;
    }

    private boolean lineIsEmpty(String line){
        return line.trim().length() == 0;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        activity.refreshLoader();
    }
}
