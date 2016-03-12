package dimitris.android.app;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import static dimitris.android.app.DBPuzzleProviderContract.PuzzleTableColumns.TABLE_NAME;
import static dimitris.android.app.DBPuzzleProviderContract.PuzzleTableColumns._ID;

/**
 * Created by dimitris on 09/03/16.
 */
public class PuzzleContentProvider extends ContentProvider {

    public static final Uri CONTENT_URI = Uri.parse("content://com.dimitris.chesspuzzler/puzzles");
    private SQLiteDatabase puzzleDB;

    private static final int ALL_PUZZLES_URI_NUM = 1;
    private static final int PUZZLE_ID_URI_NUM = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI("com.dimitris.chesspuzzler","puzzles",ALL_PUZZLES_URI_NUM);
        uriMatcher.addURI("com.dimtiris.chesspuzzler","puzzles/#", PUZZLE_ID_URI_NUM);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DBPuzzleProviderHelper helper = new DBPuzzleProviderHelper(context);
        puzzleDB = helper.getWritableDatabase();
        return puzzleDB != null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(TABLE_NAME);
        int switch_num = uriMatcher.match(uri);
        switch (switch_num){
            case PUZZLE_ID_URI_NUM:
                builder.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
                break;
            default: break;
        }

        Cursor c = builder.query(puzzleDB,projection,selection,selectionArgs,null,null,sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case ALL_PUZZLES_URI_NUM: return "vnd.android.cursor.dir/com.dimitris.chesspuzzler";
            case PUZZLE_ID_URI_NUM: return "vnd.android.cursor.item/com.dimitris.chesspuzzler";
            default: throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = puzzleDB.insert(TABLE_NAME,null,values);

        if (rowID > 0){
            Uri resultUri = ContentUris.withAppendedId(CONTENT_URI,rowID);
            getContext().getContentResolver().notifyChange(uri, null);
            return resultUri;
        }
        throw new SQLException("Failed to insert row into "+ uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count;

        switch (uriMatcher.match(uri)){
            case ALL_PUZZLES_URI_NUM:
                count = puzzleDB.delete(TABLE_NAME, selection, selectionArgs);
                break;
            case PUZZLE_ID_URI_NUM:
                String sel = getSelectionString(uri, selection);
                count = puzzleDB.delete(TABLE_NAME, sel, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    private String getSelectionString(Uri uri, String selection) {
        String segment = uri.getPathSegments().get(1);
        return _ID + "=" + segment + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : "");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        int count;
        switch (uriMatcher.match(uri)){
            case ALL_PUZZLES_URI_NUM:
                count = puzzleDB.update(TABLE_NAME,values,selection,selectionArgs);
                break;
            case PUZZLE_ID_URI_NUM:
                String sel = getSelectionString(uri, selection);
                count = puzzleDB.update(TABLE_NAME, values, sel, selectionArgs);
                break;
            default: throw new IllegalArgumentException("Unsupported URI "+ uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

}
