package dimitris.android.app.db;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static dimitris.android.app.db.PuzzleCollectionDBTable.*;
import static dimitris.android.app.db.PuzzleDBTable.*;

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
    private Context context;

    public PopulateDbTask(Context context){
        this.context = context;
        this.assetManager = context.getAssets();
        progressDialog = new ProgressDialog(context);
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

            readFile(currentFilename, collectionId);
        }

        return null;
    }

    private Uri createDbCollection(String collectionDescription) {
        Log.e(tag, "Creating collection "+collectionDescription);
        ContentValues contentValues = new ContentValues();
        contentValues.put(PuzzleCollectionColumns.COLUMN_DESCRIPTION,collectionDescription);
        return insertInDB(PuzzleCollectionColumns.CONTENT_URI,contentValues);
    }

    private Uri insertInDB(Uri uri, ContentValues contentValues){
        ContentResolver contentResolver = context.getContentResolver();
        return contentResolver.insert(uri,contentValues);
    }

    private void readFile(String collectionFilename, String collectionId) {
        BufferedReader reader;
        InputStream inputStream;

        try {
            inputStream = assetManager.open(collectionFilename);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            createEntriesFromFile(reader, collectionId);
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createEntriesFromFile(BufferedReader reader, String collectionId) throws IOException {
        String line = reader.readLine();
        while(line !=null){
            if (!lineIsEmpty(line)) {
                String description = line;
                String fen = reader.readLine();
                String solution = reader.readLine();

                insertPuzzleInDb(description,fen,solution,collectionId);
            }
            line = reader.readLine();
        }
    }

    private void insertPuzzleInDb(String description, String fen, String solution, String collectionId) {
        ContentValues values = new ContentValues();
        values.put(PuzzleColumns.COLUMN_DESCRIPTION,description);
        values.put(PuzzleColumns.COLUMN_FEN, fen);
        values.put(PuzzleColumns.COLUMN_SOLUTION, solution);
        values.put(PuzzleColumns.COLUMN_COLLECTION_ID, collectionId);

        insertInDB(PuzzleColumns.CONTENT_URI,values);
    }

    private boolean lineIsEmpty(String line){
        return line.trim().length() == 0;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
