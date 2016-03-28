package dimitris.android.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static dimitris.android.app.db.PuzzleCollectionDBTable.*;
import static dimitris.android.app.db.PuzzleDBTable.*;

/**
 * Created by dimitris on 07/03/16.
 */
public class PuzzleDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "Puzzles.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_PUZZLES_TABLE =
            "CREATE TABLE " + PuzzleColumns.TABLE_NAME +
                    " (" + PuzzleColumns._ID + INTEGER_TYPE + " PRIMARY KEY," +
                    PuzzleColumns.COLUMN_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    PuzzleColumns.COLUMN_FEN + TEXT_TYPE + COMMA_SEP +
                    PuzzleColumns.COLUMN_SOLUTION + TEXT_TYPE + COMMA_SEP +
                    PuzzleColumns.COLUMN_COLLECTION_ID + INTEGER_TYPE + " NOT NULL" + COMMA_SEP +
                    " FOREIGN KEY (" + PuzzleColumns.COLUMN_COLLECTION_ID + ") REFERENCES " +
                    PuzzleCollectionColumns.TABLE_NAME + " (" + PuzzleCollectionColumns._ID + "));";

    private static final String SQL_DELETE_PUZZLES_TABLE =
            "DROP TABLE IF EXISTS " + PuzzleColumns.TABLE_NAME;

    private static final String SQL_CREATE_COLLECTIONS_TABLE =
            "CREATE TABLE " + PuzzleCollectionColumns.TABLE_NAME +
                    " (" + PuzzleCollectionColumns._ID + " INTEGER PRIMARY KEY," +
                    PuzzleCollectionColumns.COLUMN_DESCRIPTION + TEXT_TYPE + " )";

    private static final String SQL_DELETE_COLLECTIONS_TABLE =
            "DROP TABLE IF EXISTS " + PuzzleCollectionColumns.TABLE_NAME;

    public PuzzleDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_COLLECTIONS_TABLE);
        db.execSQL(SQL_CREATE_PUZZLES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_COLLECTIONS_TABLE);
        db.execSQL(SQL_DELETE_PUZZLES_TABLE);
        onCreate(db);
    }

}
