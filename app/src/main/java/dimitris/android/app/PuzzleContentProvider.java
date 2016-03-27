package dimitris.android.app;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by dimitris on 09/03/16.
 */
public class PuzzleContentProvider extends ContentProvider {

    private static final UriMatcher uriMatcher = buildUriMatcher();
    private static final int ALL_PUZZLES = 100;
    private static final int PUZZLE_WITH_ID = 200;
    private PuzzleDBHelper puzzleDBHelper;
    private static SQLiteQueryBuilder queryBuilder;

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = PuzzleDBTable.CONTENT_AUTHORITY;

        matcher.addURI(authority, PuzzleDBTable.PUZZLE_PATH, ALL_PUZZLES);
        matcher.addURI(authority, PuzzleDBTable.PUZZLE_PATH + "/#", PUZZLE_WITH_ID);

        return matcher;
    }

    static {
        queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(PuzzleDBTable.PuzzleColumns.TABLE_NAME);
    }

    private static final String PUZZLE_ID_SELECTION =
            PuzzleDBTable.PuzzleColumns.TABLE_NAME + "."
                    + PuzzleDBTable.PuzzleColumns.COLUMN_PUZZLE_ID + " = ?";

    @Override
    public boolean onCreate() {
        puzzleDBHelper = new PuzzleDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor;
        int matchedInt = uriMatcher.match(uri);

        switch (matchedInt){
            case PUZZLE_WITH_ID:
                cursor = getPuzzleWithId(uri,strings,s1);
                Log.e("Content Provider", "Specific Puzzle Requested! Uri: " + uri);
                break;
            case ALL_PUZZLES:
                cursor = getAllPuzzles();
                Log.e("Content Provider", "All Puzzles were requested! Uri: " + uri);
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri : " +uri);
        }

        return cursor;
    }

    private Cursor getPuzzleWithId(Uri uri, String[] projection, String sortOrder){
        String puzzleId = PuzzleDBTable.PuzzleColumns.getPuzzleIdFromUri(uri);
        SQLiteDatabase db = puzzleDBHelper.getReadableDatabase();
        String[] selectionArgs = new String[]{puzzleId};

        return queryBuilder.query(db, projection, PUZZLE_ID_SELECTION, selectionArgs,null,null,sortOrder);
    }

    private Cursor getAllPuzzles(){
        SQLiteDatabase db = puzzleDBHelper.getReadableDatabase();
        return queryBuilder.query(db, null, null,null,null,null,null);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int matchInt = uriMatcher.match(uri);
        Uri resultPath= Uri.EMPTY;
        switch (matchInt){
            case ALL_PUZZLES:
                resultPath = insertPuzzleInDb(contentValues);
                Log.e("ContentProvider", "Successfully inserted puzzle at: "+ resultPath);
                break;
        }
        return resultPath;
    }

    private Uri insertPuzzleInDb(ContentValues contentValues){
        final SQLiteDatabase db = puzzleDBHelper.getWritableDatabase();
        Uri resultUri = Uri.EMPTY;
        long id = db.insert(PuzzleDBTable.PuzzleColumns.TABLE_NAME, null, contentValues);

        if(id >0)
            resultUri = PuzzleDBTable.PuzzleColumns.CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();
        else
            throw new SQLException("Failed to insert Entry");

        return resultUri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
