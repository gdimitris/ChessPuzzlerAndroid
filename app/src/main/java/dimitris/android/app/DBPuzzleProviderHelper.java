package dimitris.android.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static dimitris.android.app.DBPuzzleProviderContract.PuzzleTableColumns.*;

/**
 * Created by dimitris on 07/03/16.
 */
public class DBPuzzleProviderHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Puzzles.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_PUZZLES_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    " (" + _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    COLUMN_FEN + TEXT_TYPE + COMMA_SEP +
                    COLUMN_SOLUTION + TEXT_TYPE + " )";

    private static final String SQL_DELETE_PUZZLES_TABLE =
            "DROP IF EXISTS " + TABLE_NAME;

    public DBPuzzleProviderHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PUZZLES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PUZZLES_TABLE);
        onCreate(db);
    }

}
