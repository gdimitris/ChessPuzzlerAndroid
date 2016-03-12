package dimitris.android.app;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by dimitris on 07/03/16.
 */
public interface DBDelegate {
    void onDatabaseInstanceLoaded(SQLiteDatabase db);
}
