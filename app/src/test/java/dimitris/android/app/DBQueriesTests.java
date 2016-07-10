package dimitris.android.app;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Build;

import com.dimitris.chesspuzzler.BuildConfig;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import dimitris.android.app.db.DBHelper;
import dimitris.android.app.db.DBQueries;

import static dimitris.android.app.db.PuzzleCollectionDBTable.PuzzleCollectionColumns;
import static dimitris.android.app.db.PuzzleDBTable.PuzzleColumns;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by dimitris on 18/04/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class DBQueriesTests {

    private DBQueries dbQueries;
    private SQLiteQueryBuilder mockQueryBuilder;
    private SQLiteDatabase mockDb;

    @Before
    public void setUp() {
        mockQueryBuilder = mock(SQLiteQueryBuilder.class);
        mockDb = mock(SQLiteDatabase.class);
        dbQueries = new DBQueries(mockQueryBuilder);
    }

    @Test
    public void testQueryForAllPuzzles() {
        String[] projection = PuzzleColumns.ALL_COLUMNS;
        dbQueries.getAllPuzzles(mockDb);
        verify(mockQueryBuilder, atLeastOnce()).query(mockDb, projection, null, null, null, null, null);
    }

    @Test
    public void testQueryForPuzzleWithId() {
        String testPuzzleId = "123";
        Uri uri = PuzzleColumns.CONTENT_URI.buildUpon().appendPath(testPuzzleId).build();
        String selection = DBQueries.PUZZLE_ID_SELECTION;
        String[] selectionArgs = new String[]{testPuzzleId};

        dbQueries.getPuzzleWithId(mockDb, uri);
        verify(mockQueryBuilder, atLeastOnce())
                .query(mockDb, null, selection, selectionArgs, null, null, null);
    }

    @Test
    public void testQueryForPuzzlesWithCollectionId() {
        String testCollectionId = "22";
        Uri uri = PuzzleColumns.CONTENT_URI
                .buildUpon()
                .appendPath("collection")
                .appendPath(testCollectionId).build();
        String[] projection = PuzzleColumns.ALL_COLUMNS;
        String selection = DBQueries.PUZZLE_WITH_COLLECTION_ID_SELECTION;
        String[] selectionArgs = new String[]{testCollectionId};

        dbQueries.getPuzzlesWithCollectionId(mockDb, uri);
        verify(mockQueryBuilder, atLeastOnce())
                .query(mockDb, projection, selection, selectionArgs, null, null, null);
    }

    @Test
    public void testQueryForCollectionsWithId() {
        String testCollectionId = "222";
        Uri uri = PuzzleCollectionColumns.CONTENT_URI.buildUpon().appendPath(testCollectionId).build();
        String[] projection = PuzzleCollectionColumns.ALL_COLUMNS;
        String selection = DBQueries.COLLECTION_ID_SELECTION;
        String[] selectionArgs = new String[]{testCollectionId};
        dbQueries.getCollectionsWithId(mockDb, uri);

        verify(mockQueryBuilder, atLeastOnce())
                .query(mockDb, projection, selection, selectionArgs, null, null, null);
    }

    @Test
    public void testQueryForCollectionsWithCount() {
        String[] projection = DBQueries.COLLECTIONS_WITH_COUNT;
        String group = "Puzzles.collection_id";

        dbQueries.getCollectionsWithPuzzleCount(mockDb);
        verify(mockQueryBuilder, atLeastOnce())
                .query(mockDb, projection, null, null, group, null, null);
    }

    private ContentValues createDbValues(String description, String fen, String solution) {
        ContentValues values = new ContentValues();
        values.put(PuzzleColumns._ID, "1");
        values.put(PuzzleColumns.COLUMN_DESCRIPTION, description);
        values.put(PuzzleColumns.COLUMN_FEN, fen);
        values.put(PuzzleColumns.COLUMN_SOLUTION, solution);
        values.put(PuzzleColumns.COLUMN_PUZZLE_ID, "1");
        values.put(PuzzleColumns.COLUMN_COLLECTION_ID, "1");
        values.put(PuzzleColumns.COLUMN_REVIEW_ID, "1");
        return values;
    }
}
