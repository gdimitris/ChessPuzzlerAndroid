package dimitris.android.app;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import com.dimitris.chesspuzzler.R;

public class MainActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeListView();
    }

    private void initializeListView() {
        String[] fields = new String[] {PuzzleCollectionDBTable.PuzzleCollectionColumns.COLUMN_DESCRIPTION, "count"};
        int[] mappedViewLabels = new int[] {R.id.label, R.id.puzzleCount};
        getLoaderManager().initLoader(0,null,this);
        cursorAdapter = new ButtonWrappingCursorAdapter(this, R.layout.puzzle_list_item,null,fields,mappedViewLabels,0);
        setListAdapter(cursorAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = new String[]{"_id","description","count"};
        Uri uri = PuzzleCollectionDBTable.PuzzleCollectionColumns.CONTENT_URI.buildUpon().appendPath("count").build();
        CursorLoader cursorLoader = new CursorLoader(this, uri,projection,null,null,null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
        Log.e("CursorLoader", "Loading Finished! got "+data.getCount() + " entries");
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}
