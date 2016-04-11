package dimitris.android.app;

import android.app.ActivityOptions;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.dimitris.chesspuzzler.R;

import dimitris.android.app.db.PopulateDbTask;

import static dimitris.android.app.db.PuzzleCollectionDBTable.PuzzleCollectionColumns;

public class MainActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>, PuzzleLoaderCallback {

    private SimpleCursorAdapter cursorAdapter;

    private static final int COLLECTION_LOADER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeListView();
    }

    private void initializeListView() {
        String[] fields = new String[] {PuzzleCollectionColumns.COLUMN_DESCRIPTION, "count"};
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
        }
        return cursorLoader;
    }


    private CursorLoader getCollectionCursorLoader() {
        Uri uri = PuzzleCollectionColumns.CONTENT_URI.buildUpon().appendPath("count").build();

        return new CursorLoader(this,uri,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        int loaderId = loader.getId();
        switch (loaderId){
            case COLLECTION_LOADER:
                handleLoadedCollections(data);
                break;
        }
    }

    private void handleLoadedCollections(Cursor data) {
        if (data.getCount() == 0){
            PopulateDbTask dbTask = new PopulateDbTask(this);
            dbTask.execute();
        } else {
            cursorAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }

    @Override
    public void loadRequestedForPuzzlesWithCollectionId(int collectionId) {
        Intent playActivityIntent = new Intent(this,PlayPuzzleActivity.class);
        playActivityIntent.putExtra("requestedId",collectionId);
        startActivity(playActivityIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void refreshLoader(){
        getLoaderManager().restartLoader(COLLECTION_LOADER,null,this);
    }
}
