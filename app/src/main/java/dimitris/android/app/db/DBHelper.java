package dimitris.android.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static dimitris.android.app.db.PuzzleCollectionDBTable.PuzzleCollectionColumns;
import static dimitris.android.app.db.PuzzleDBTable.PuzzleColumns;
import static dimitris.android.app.db.ReviewDBTable.ReviewColumns;

/**
 * Created by dimitris on 07/03/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 8;
    public static final String DATABASE_NAME = "Puzzles.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String DATE_TYPE = " DATE";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_PUZZLES_TABLE =
            "CREATE TABLE " + PuzzleColumns.TABLE_NAME +
                    " (" + PuzzleColumns._ID + INTEGER_TYPE + " PRIMARY KEY," +
                    PuzzleColumns.COLUMN_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    PuzzleColumns.COLUMN_FEN + TEXT_TYPE + COMMA_SEP +
                    PuzzleColumns.COLUMN_SOLUTION + TEXT_TYPE + COMMA_SEP +
                    PuzzleColumns.COLUMN_COLLECTION_ID + INTEGER_TYPE + " NOT NULL" + COMMA_SEP +
                    PuzzleColumns.COLUMN_REVIEW_ID + INTEGER_TYPE + " NOT NULL" + COMMA_SEP +
                    " FOREIGN KEY (" + PuzzleColumns.COLUMN_COLLECTION_ID + ") REFERENCES " +
                    PuzzleCollectionColumns.TABLE_NAME + " (" + PuzzleCollectionColumns._ID + ")," +
                    " FOREIGN KEY (" + ReviewColumns.COLUMN_REVIEW_ID + ") REFERENCES " +
                    ReviewColumns.TABLE_NAME + " (" + ReviewColumns._ID +"));";

    private static final String SQL_DELETE_PUZZLES_TABLE =
            "DROP TABLE IF EXISTS " + PuzzleColumns.TABLE_NAME;

    private static final String SQL_CREATE_COLLECTIONS_TABLE =
            "CREATE TABLE " + PuzzleCollectionColumns.TABLE_NAME +
                    " (" + PuzzleCollectionColumns._ID + " INTEGER PRIMARY KEY," +
                    PuzzleCollectionColumns.COLUMN_DESCRIPTION + TEXT_TYPE + " )";

    private static final String SQL_DELETE_COLLECTIONS_TABLE =
            "DROP TABLE IF EXISTS " + PuzzleCollectionColumns.TABLE_NAME;

    private static final String SQL_CREATE_REVIEWS_TABLE =
            "CREATE TABLE " + ReviewColumns.TABLE_NAME +
                    " (" + ReviewColumns._ID + INTEGER_TYPE + " PRIMARY KEY, " +
                    ReviewColumns.COLUMN_EASINESS_FACTOR + " REAL NOT NULL, " +
                    ReviewColumns.COLUMN_REVIEW_INTERVAL + INTEGER_TYPE + COMMA_SEP +
                    ReviewColumns.COLUMN_NEXT_REVIEW + DATE_TYPE + COMMA_SEP +
                    ReviewColumns.COLUMN_LAST_REVIEWED + DATE_TYPE + " )";

    private static final String SQL_DELETE_REVIEWS_TABLE =
            "DROP TABLE IF EXISTS " + ReviewColumns.TABLE_NAME;

    public static final String PUZZLES_COLLECTIONS_TABLES_INNER_JOIN = PuzzleColumns.TABLE_NAME +
            " INNER JOIN " + PuzzleCollectionColumns.TABLE_NAME +
            " ON " + PuzzleColumns.TABLE_NAME + "." + PuzzleColumns.COLUMN_COLLECTION_ID + " = "
            + PuzzleCollectionColumns.TABLE_NAME + "." + PuzzleCollectionColumns._ID +
            " INNER JOIN " + ReviewColumns.TABLE_NAME +
            " ON " + PuzzleColumns.TABLE_NAME + "." + PuzzleColumns.COLUMN_REVIEW_ID + " = "
            + ReviewColumns.TABLE_NAME + "." + ReviewColumns._ID;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_COLLECTIONS_TABLE);
        db.execSQL(SQL_CREATE_REVIEWS_TABLE);
        db.execSQL(SQL_CREATE_PUZZLES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_COLLECTIONS_TABLE);
        db.execSQL(SQL_DELETE_REVIEWS_TABLE);
        db.execSQL(SQL_DELETE_PUZZLES_TABLE);
        onCreate(db);
    }

}
