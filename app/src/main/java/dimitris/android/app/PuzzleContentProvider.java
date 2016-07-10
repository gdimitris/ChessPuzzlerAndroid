package dimitris.android.app;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;

import dimitris.android.app.db.DBHelper;
import dimitris.android.app.db.DBQueries;
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

    private static final int ALL_PUZZLES = 100;
    private static final int PUZZLE_WITH_ID = 200;
    private static final int PUZZLES_WITH_COLLECTION_ID = 201;
    private static final int COLLECTIONS_WITH_PUZZLE_COUNT = 300;
    private static final int COLLECTIONS_WITH_ID = 301;
    private static final int ALL_COLLECTIONS = 400;
    private static final int ALL_REVIEWS = 500;
    private static final int REVIEW_WITH_ID = 501;

    public static final String ALL_PUZZLES_PATH = PUZZLE_PATH;
    public static final String PUZZLE_WITH_ID_PATH = PUZZLE_PATH + "#";
    public static final String PUZZLES_WITH_COLLECTION_ID_PATH = PUZZLE_PATH + "/collection/#";
    public static final String ALL_COLLECTIONS_PATH = COLLECTION_PATH;
    public static final String COLLECTIONS_WITH_PUZZLE_COUNT_PATH = COLLECTION_PATH + "/count";
    public static final String COLLECTIONS_WITH_ID_PATH = COLLECTION_PATH + "/#";

    public static final String REVIEW_ENTRY_WITH_ID = REVIEW_PATH + "#";


    private static final UriMatcher uriMatcher = buildUriMatcher();

    private DBHelper dbHelper;
    private DBQueries dbQueries;

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = PuzzleDBTable.CONTENT_AUTHORITY;

        matcher.addURI(authority, ALL_PUZZLES_PATH, ALL_PUZZLES);
        matcher.addURI(authority, PUZZLE_WITH_ID_PATH, PUZZLE_WITH_ID);
        matcher.addURI(authority, PUZZLES_WITH_COLLECTION_ID_PATH, PUZZLES_WITH_COLLECTION_ID);

        matcher.addURI(authority, ALL_COLLECTIONS_PATH, ALL_COLLECTIONS);
        matcher.addURI(authority, COLLECTIONS_WITH_PUZZLE_COUNT_PATH, COLLECTIONS_WITH_PUZZLE_COUNT);
        matcher.addURI(authority, COLLECTIONS_WITH_ID_PATH, COLLECTIONS_WITH_ID);

        matcher.addURI(authority, REVIEW_PATH, ALL_REVIEWS);
        matcher.addURI(authority, REVIEW_ENTRY_WITH_ID,REVIEW_WITH_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        dbQueries = new DBQueries(DBHelper.PUZZLES_COLLECTIONS_TABLES_INNER_JOIN);
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor;
        int matchedInt = uriMatcher.match(uri);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        switch (matchedInt){
            case PUZZLE_WITH_ID:
                cursor = dbQueries.getPuzzleWithId(db, uri);
                break;
            case ALL_PUZZLES:
                cursor = dbQueries.getAllPuzzles(db);
                break;
            case COLLECTIONS_WITH_PUZZLE_COUNT:
                cursor = dbQueries.getCollectionsWithPuzzleCount(db);
                break;
            case COLLECTIONS_WITH_ID:
                cursor = dbQueries.getCollectionsWithId(db, uri);
                break;
            case PUZZLES_WITH_COLLECTION_ID:
                cursor = dbQueries.getPuzzlesWithCollectionId(db, uri);
                break;
            default:
                throw new SQLException("Unknown Uri : " +uri);
        }
        return cursor;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
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

    private int updateValuesInDb(String tableName,Uri uri, ContentValues values, String whereClause, String[] whereArgs){
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsAffected = db.update(tableName,values,whereClause, whereArgs);

        if (rowsAffected < 0 )
            throw new SQLException("Failed to update Entry for Uri: " + uri);

        return rowsAffected;
    }

    @Override
    public int delete(@NonNull Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        final int matchNumber = uriMatcher.match(uri);
        int rowsAffected;
        switch (matchNumber){
            case REVIEW_WITH_ID:
                rowsAffected = updateValuesInDb(ReviewColumns.TABLE_NAME, uri, contentValues, s, strings);
                break;
            default:
                throw new SQLException("Uknown update for DB for Uri: " + uri);
        }

        return rowsAffected;
    }

}
