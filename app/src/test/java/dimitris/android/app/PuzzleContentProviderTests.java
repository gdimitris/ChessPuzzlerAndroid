package dimitris.android.app;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowContentResolver;

import static dimitris.android.app.db.PuzzleDBTable.CONTENT_AUTHORITY;
import static dimitris.android.app.db.PuzzleDBTable.PuzzleColumns;
import static junit.framework.Assert.assertEquals;

/**
 * Created by dimitris on 18/04/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class PuzzleContentProviderTests {

    private ContentResolver contentResolver;
    private ShadowContentResolver shadowContentResolver;
    private PuzzleContentProvider puzzleContentProvider;

    @Before
    public void setUp(){
        puzzleContentProvider = new PuzzleContentProvider();
        contentResolver = RuntimeEnvironment.application.getContentResolver();
        shadowContentResolver = Shadows.shadowOf(contentResolver);
        puzzleContentProvider.onCreate();
        ShadowContentResolver.registerProvider(CONTENT_AUTHORITY, puzzleContentProvider);
    }

    @Test
    public void testInsertNewPuzzle(){
        String description = "test description";
        String fen = "test fen";
        String solution = "test solution";


        ContentValues values = new ContentValues();
        values.put(PuzzleColumns._ID, "1");
        values.put(PuzzleColumns.COLUMN_DESCRIPTION, description);
        values.put(PuzzleColumns.COLUMN_FEN, fen);
        values.put(PuzzleColumns.COLUMN_SOLUTION, solution);
        values.put(PuzzleColumns.COLUMN_PUZZLE_ID, "1");
        values.put(PuzzleColumns.COLUMN_COLLECTION_ID,"1");
        values.put(PuzzleColumns.COLUMN_REVIEW_ID,"1");

        Uri uri = contentResolver.insert(PuzzleColumns.CONTENT_URI,values);


        Robolectric.flushForegroundThreadScheduler();

        String puzzleId = PuzzleColumns.getPuzzleIdFromUri(uri);

        Cursor cursor = contentResolver.query(PuzzleColumns.CONTENT_URI,null,null,null,null);

        while(cursor.moveToNext()){
            Log.e("test","Row id: "+ cursor.getString(0));
        }
        cursor.moveToFirst();
        assertTrue(cursor.getCount() == 1);


        Uri queryUri = PuzzleColumns.CONTENT_URI.buildUpon().appendPath(puzzleId).build();
        Cursor cursor1 = shadowContentResolver.query(queryUri, null, null, null, null);
        while (cursor1.moveToNext()){
            assertEquals(puzzleId,cursor1.getString(0));
            assertEquals(description, cursor1.getString(1));
            assertEquals(fen, cursor1.getString(2));
            assertEquals(solution, cursor1.getString(3));
        }
    }
}
