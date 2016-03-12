package dimitris.android.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

/**
 * Created by dimitris on 07/03/16.
 */
public class DBLoaderTask extends AsyncTask<DBDelegate,Void,Void> {

    private Context context;

    public DBLoaderTask(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(DBDelegate... params) {
        DBPuzzleProviderHelper helper = new DBPuzzleProviderHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        DBDelegate delegate = params[0];
        delegate.onDatabaseInstanceLoaded(db);

        return null;
    }

}
