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
import android.widget.Toast;

import com.dimitris.chesspuzzler.R;

import dimitris.android.app.db.PuzzleCollectionDBTable;

public class MainActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>, PuzzleLoaderCallback {

    private SimpleCursorAdapter cursorAdapter;

    private static final int COLLECTION_LOADER = 1;
    private static final int PUZZLE_LOADER = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeListView();
    }

    private void initializeListView() {
        String[] fields = new String[] {PuzzleCollectionDBTable.PuzzleCollectionColumns.COLUMN_DESCRIPTION, "count"};
        int[] mappedViewLabels = new int[] {R.id.label, R.id.puzzleCount};
        getLoaderManager().initLoader(COLLECTION_LOADER,null,this);
        cursorAdapter = new ButtonWrappingCursorAdapter(this, R.layout.puzzle_list_item,null,fields,mappedViewLabels,0);
        setListAdapter(cursorAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = null;
        switch (id){
            case COLLECTION_LOADER:
                cursorLoader = getCollectionCursorLoader();
                break;
            case PUZZLE_LOADER:
                cursorLoader = getPuzzleLoader(args);
                break;
        }
        return cursorLoader;
    }

    private CursorLoader getPuzzleLoader(Bundle bundle) {
        String collectionId = bundle.getString("collectionId");
        Uri uri = PuzzleCollectionDBTable.PuzzleCollectionColumns.CONTENT_URI.buildUpon().appendPath(collectionId).build();

        CursorLoader cursorLoader = new CursorLoader(this,uri,null,null,null,null);
        return cursorLoader;
    }

    private CursorLoader getCollectionCursorLoader() {
        Uri uri = PuzzleCollectionDBTable.PuzzleCollectionColumns.CONTENT_URI.buildUpon().appendPath("count").build();

        CursorLoader cursorLoader = new CursorLoader(this, uri,null,null,null,null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        int loaderId = loader.getId();
        switch (loaderId){
            case PUZZLE_LOADER:
                Log.e("CursorLoader", "Loading of Activity requested to review" + data.getCount() + "  puzzles");
                break;
            case COLLECTION_LOADER:
                cursorAdapter.swapCursor(data);
                Log.e("CursorLoader", "Loading Finished! got "+data.getCount() + " entries");
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }

    @Override
    public void loadRequestedForPuzzlesWithCollectionId(int collectionId) {
        Toast.makeText(this,"Load Requested for puzzles with Collection id: " + collectionId,Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putString("collectionId",String.valueOf(collectionId));

        getLoaderManager().restartLoader(PUZZLE_LOADER,bundle,this);
    }
}
