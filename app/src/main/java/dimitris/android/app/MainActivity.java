package dimitris.android.app;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dimitris.chesspuzzler.R;

import dimitris.android.app.db.PopulateDbTask;

import static dimitris.android.app.db.PuzzleCollectionDBTable.PuzzleCollectionColumns;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    private CursorRecyclerViewAdapter cursorAdapter;
    private RecyclerView recyclerView;

    private static final int COLLECTION_LOADER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new CardDividerItemDecorator(20));

        getLoaderManager().initLoader(COLLECTION_LOADER,null,this);
        cursorAdapter = new CursorRecyclerViewAdapter();
        recyclerView.setAdapter(cursorAdapter);
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
        Uri uri = PuzzleCollectionColumns.PUZZLE_COUNT_URI;

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
            cursorAdapter.changeCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.changeCursor(null);
    }

    public void refreshLoader(){
        getLoaderManager().restartLoader(COLLECTION_LOADER,null,this);
    }
}
