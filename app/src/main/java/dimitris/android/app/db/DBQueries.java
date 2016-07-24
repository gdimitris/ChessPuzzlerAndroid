package dimitris.android.app.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by dimitris on 5/7/16.
 */
public class DBQueries {

    public static final String PUZZLE_ID_SELECTION =
            PuzzleDBTable.PuzzleColumns.TABLE_NAME + "."
                    + PuzzleDBTable.PuzzleColumns.COLUMN_PUZZLE_ID + " = ?";

    public static final String COLLECTION_ID_SELECTION =
            PuzzleCollectionDBTable.PuzzleCollectionColumns.TABLE_NAME + "." +
                    PuzzleCollectionDBTable.PuzzleCollectionColumns.COLUMN_COLLECTION_ID + " = ?";

    public static final String PUZZLE_WITH_COLLECTION_ID_SELECTION =
            PuzzleDBTable.PuzzleColumns.TABLE_NAME + "." +
                    PuzzleDBTable.PuzzleColumns.COLUMN_COLLECTION_ID + " = ?";

    public static final String REVIEW_ENTRY_WITH_ID =
            ReviewDBTable.ReviewColumns.TABLE_NAME + "."
                    + ReviewDBTable.ReviewColumns.COLUMN_REVIEW_ID + " = ?";

    public static final String[] COLLECTIONS_WITH_COUNT = new String[]{"Collections._id", "Collections.description",
            "count(Puzzles.collection_id) as count"};

    private SQLiteQueryBuilder queryBuilder;

    public DBQueries(String tableName) {
        queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(tableName);
    }

    public DBQueries(SQLiteQueryBuilder queryBuilder){
        this.queryBuilder = queryBuilder;
    }

    public Cursor getAllPuzzles(SQLiteDatabase readableDb) {
        String[] projection = PuzzleDBTable.PuzzleColumns.ALL_COLUMNS;

        return queryBuilder.query(readableDb, projection, null, null, null, null, null);
    }

    public Cursor getPuzzleWithId(SQLiteDatabase readableDb, Uri uri) {
        String puzzleId = PuzzleDBTable.PuzzleColumns.getPuzzleIdFromUri(uri);
        String[] selectionArgs = new String[]{puzzleId};

        return queryBuilder.query(readableDb, null, PUZZLE_ID_SELECTION, selectionArgs, null, null, null);
    }

    public Cursor getPuzzlesWithCollectionId(SQLiteDatabase readableDb, Uri uri) {
        String[] projection = PuzzleDBTable.PuzzleColumns.ALL_COLUMNS;
        String[] selectionArgs = new String[]{PuzzleDBTable.PuzzleColumns.getCollectionIdFromUri(uri)};

        return queryBuilder.query(readableDb, projection, PUZZLE_WITH_COLLECTION_ID_SELECTION, selectionArgs, null, null, null);
    }

    public Cursor getCollectionsWithId(SQLiteDatabase readableDb, Uri uri) {
        String collectionId = PuzzleCollectionDBTable.PuzzleCollectionColumns.getCollectionIdFromUri(uri);
        String[] projection = PuzzleCollectionDBTable.PuzzleCollectionColumns.ALL_COLUMNS;
        String[] selectionArgs = new String[]{collectionId};

        return queryBuilder.query(readableDb, projection, COLLECTION_ID_SELECTION, selectionArgs, null, null, null);
    }

    public Cursor getCollectionsWithPuzzleCount(SQLiteDatabase readableDb) {
        String group = "Puzzles.collection_id";

        return queryBuilder.query(readableDb, COLLECTIONS_WITH_COUNT, null, null, group, null, null);
    }

    public Cursor getReviewEntryWithId(SQLiteDatabase db, Uri uri) {
        return null;
    }
}
