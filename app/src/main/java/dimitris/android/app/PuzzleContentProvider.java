package dimitris.android.app;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import dimitris.android.app.db.DBHelper;
import dimitris.android.app.db.PuzzleDBTable;

import static dimitris.android.app.db.PuzzleCollectionDBTable.COLLECTION_PATH;
import static dimitris.android.app.db.PuzzleCollectionDBTable.PuzzleCollectionColumns;
import static dimitris.android.app.db.PuzzleDBTable.PUZZLE_PATH;
import static dimitris.android.app.db.PuzzleDBTable.PuzzleColumns;
import static dimitris.android.app.db.ReviewDBTable.REVIEW_PATH;
import static dimitris.android.app.db.ReviewDBTable.ReviewColumns;

/**
 * Created by dimitris on 09/03/16.
 */
public class PuzzleContentProvider extends ContentProvider {

    private static final UriMatcher uriMatcher = buildUriMatcher();
    private static final int ALL_PUZZLES = 100;
    private static final int PUZZLE_WITH_ID = 200;
    private static final int PUZZLES_WITH_COLLECTION_ID = 201;
    private static final int COLLECTIONS_WITH_PUZZLE_COUNT = 300;
    private static final int COLLECTIONS_WITH_ID = 301;
    private static final int ALL_COLLECTIONS = 400;
    private static final int ALL_REVIEWS = 500;

    private DBHelper dbHelper;
    private static SQLiteQueryBuilder queryBuilder;

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = PuzzleDBTable.CONTENT_AUTHORITY;

        matcher.addURI(authority, PUZZLE_PATH, ALL_PUZZLES);
        matcher.addURI(authority, PUZZLE_PATH + "/#", PUZZLE_WITH_ID);
        matcher.addURI(authority, PUZZLE_PATH + "/collection/#", PUZZLES_WITH_COLLECTION_ID);

        matcher.addURI(authority, COLLECTION_PATH, ALL_COLLECTIONS);
        matcher.addURI(authority, COLLECTION_PATH + "/count", COLLECTIONS_WITH_PUZZLE_COUNT);
        matcher.addURI(authority, COLLECTION_PATH + "/#", COLLECTIONS_WITH_ID);

        matcher.addURI(authority, REVIEW_PATH, ALL_REVIEWS);

        return matcher;
    }

    static {
        String join = PuzzleColumns.TABLE_NAME +
                " INNER JOIN " + PuzzleCollectionColumns.TABLE_NAME +
                " ON " + PuzzleColumns.TABLE_NAME + "." + PuzzleColumns.COLUMN_COLLECTION_ID + " = "
                + PuzzleCollectionColumns.TABLE_NAME  + "." + PuzzleCollectionColumns._ID +
                " INNER JOIN " + ReviewColumns.TABLE_NAME +
                " ON " + PuzzleColumns.TABLE_NAME + "." + PuzzleColumns.COLUMN_REVIEW_ID + " = "
                + ReviewColumns.TABLE_NAME + "." + ReviewColumns._ID;

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

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor;
        int matchedInt = uriMatcher.match(uri);

        switch (matchedInt){
            case PUZZLE_WITH_ID:
                cursor = getPuzzleWithId(uri,strings,s1);
                break;
            case ALL_PUZZLES:
                cursor = getAllPuzzles();
                break;
            case COLLECTIONS_WITH_PUZZLE_COUNT:
                cursor = getCollectionsWithPuzzleCount();
                break;
            case COLLECTIONS_WITH_ID:
                cursor = getCollectionsWithId(uri);
                break;
            case PUZZLES_WITH_COLLECTION_ID:
                cursor = getPuzzlesWithCollectionId(uri);
                break;
            default:
                throw new SQLException("Unknown Uri : " +uri);
        }
        return cursor;
    }

    private Cursor getPuzzlesWithCollectionId(Uri uri) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = PuzzleColumns.ALL_COLUMNS;
        String selection = PuzzleColumns.TABLE_NAME + "." + PuzzleColumns.COLUMN_COLLECTION_ID + " = ?";
        String[] selectionArgs = new String[] {PuzzleColumns.getCollectionIdFromUri(uri)};

        return queryBuilder.query(db,projection,selection,selectionArgs,null,null,null);
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

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int matchNumber = uriMatcher.match(uri);
        Uri resultPath;
        switch (matchNumber){
            case ALL_PUZZLES:
                resultPath = insertValuesInDb(PuzzleColumns.TABLE_NAME,PuzzleColumns.CONTENT_URI,contentValues);
                break;
            case ALL_COLLECTIONS:
                resultPath = insertValuesInDb(PuzzleCollectionColumns.TABLE_NAME, PuzzleCollectionColumns.CONTENT_URI, contentValues);
                break;
            case ALL_REVIEWS:
                resultPath = insertValuesInDb(ReviewColumns.TABLE_NAME, ReviewColumns.CONTENT_URI, contentValues);
                break;
            default:
                throw new SQLException("Unknown insert in DB for Uri: " + uri);
        }
        return resultPath;
    }

    private Uri insertValuesInDb(String tableName, Uri uri, ContentValues values){
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = db.insert(tableName, null, values);

        if (id < 0)
            throw  new SQLException("Failed to insert Entry for Uri: " + uri);

        return uri.buildUpon().appendPath(String.valueOf(id)).build();
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
