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

import dimitris.android.app.db.DBHelper;
import dimitris.android.app.db.PuzzleDBTable;

import static dimitris.android.app.db.PuzzleCollectionDBTable.*;
import static dimitris.android.app.db.PuzzleDBTable.*;

/**
 * Created by dimitris on 09/03/16.
 */
public class PuzzleContentProvider extends ContentProvider {

    private static final UriMatcher uriMatcher = buildUriMatcher();
    private static final int ALL_PUZZLES = 100;
    private static final int PUZZLE_WITH_ID = 200;
    private static final int COLLECTIONS_WITH_PUZZLE_COUNT = 300;
    private static final int COLLECTIONS_WITH_ID = 301;
    private static final int ALL_COLLECTIONS = 400;
    private final String tag = "Content Provider";
    private DBHelper dbHelper;
    private static SQLiteQueryBuilder queryBuilder;

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = PuzzleDBTable.CONTENT_AUTHORITY;

        matcher.addURI(authority, PUZZLE_PATH, ALL_PUZZLES);
        matcher.addURI(authority, PUZZLE_PATH + "/#", PUZZLE_WITH_ID);

        matcher.addURI(authority, COLLECTION_PATH, ALL_COLLECTIONS);
        matcher.addURI(authority, COLLECTION_PATH + "/count", COLLECTIONS_WITH_PUZZLE_COUNT);
        matcher.addURI(authority, COLLECTION_PATH + "/#", COLLECTIONS_WITH_ID);

        return matcher;
    }

    static {
        String join = PuzzleColumns.TABLE_NAME + " INNER JOIN " +
                PuzzleCollectionColumns.TABLE_NAME + " ON " +
                PuzzleColumns.TABLE_NAME + "." + PuzzleColumns.COLUMN_COLLECTION_ID +
                " = " + PuzzleCollectionColumns.TABLE_NAME  +
                "." + PuzzleCollectionColumns._ID ;

        queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(join);
    }

    private static final String PUZZLE_ID_SELECTION =
            PuzzleColumns.TABLE_NAME + "."
                    + PuzzleColumns.COLUMN_PUZZLE_ID + " = ?";

    private static final String COLLECTION_ID_SELECTION =
            PuzzleCollectionColumns.TABLE_NAME + "." +
                    PuzzleCollectionColumns.COLUMN_COLLECTION_ID + " = ?";

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
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
                Log.e(tag, "Specific Puzzle Requested! Uri: " + uri);
                break;
            case ALL_PUZZLES:
                cursor = getAllPuzzles();
                Log.e(tag, "All Puzzles were requested! Uri: " + uri);
                break;
            case COLLECTIONS_WITH_PUZZLE_COUNT:
                cursor = getCollectionsWithPuzzleCount();
                Log.e(tag, "All collections with count were requested! Uri: "+ uri);
                break;
            case COLLECTIONS_WITH_ID:
                cursor = getCollectionsWithId(uri);
                Log.e(tag, "Puzzles from collection with id requested! uri: "+ uri);
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri : " +uri);
        }
        return cursor;
    }

    private Cursor getCollectionsWithPuzzleCount() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = new String[] {"Collections._id", "Collections.description",
                "count(Puzzles.collection_id) as count"};
        String group = "Puzzles.collection_id";

        return queryBuilder.query(db,projection,null,null,group,null,null);
    }

    private Cursor getPuzzleWithId(Uri uri, String[] projection, String sortOrder){
        String puzzleId = PuzzleColumns.getPuzzleIdFromUri(uri);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] selectionArgs = new String[]{puzzleId};

        return queryBuilder.query(db, projection, PUZZLE_ID_SELECTION, selectionArgs,null,null,sortOrder);
    }

    private Cursor getAllPuzzles(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        return queryBuilder.query(db, null, null,null,null,null,null);
    }

    private Cursor getCollectionsWithId(Uri uri){
        String collectionId = PuzzleCollectionColumns.getCollectionIdFromUri(uri);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = PuzzleColumns.ALL_COLUMNS;
        String[] selectionArgs = new String[]{collectionId};

        return queryBuilder.query(db,projection, COLLECTION_ID_SELECTION, selectionArgs, null,null,null);
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
            case ALL_COLLECTIONS:
                resultPath = insertCollectionInDb(contentValues);
                Log.e("ContentProvider", "Successfully created new collection " + resultPath);
                break;
        }
        return resultPath;
    }

    private Uri insertCollectionInDb(ContentValues contentValues) {
        final  SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri resultUri = Uri.EMPTY;
        long id = db.insert(PuzzleCollectionColumns.TABLE_NAME, null, contentValues);

        if (id > 0)
            resultUri = PuzzleCollectionColumns.CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();
        else
            throw new SQLException("Failed to insert Entry");

        return resultUri;
    }

    private Uri insertPuzzleInDb(ContentValues contentValues){
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri resultUri = Uri.EMPTY;
        long id = db.insert(PuzzleColumns.TABLE_NAME, null, contentValues);

        if(id >0)
            resultUri = PuzzleColumns.CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();
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
