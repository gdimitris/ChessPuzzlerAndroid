package dimitris.android.app.db;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

/**
 * Created by dimitris on 21/07/16.
 */
public class ReviewScheduleFetchTask extends AsyncTask<String,Void,Void>{

    private Context context;

    public ReviewScheduleFetchTask(Context context){
        this.context = context;
    }


    @Override
    protected Void doInBackground(String... strings) {
        String reviewId = strings[0];

        ContentResolver resolver = context.getContentResolver();
        Uri reviewUri = ReviewDBTable.BASE_CONTENT_URI.buildUpon()
                .appendPath(ReviewDBTable.REVIEW_PATH)
                .appendPath(reviewId)
                .build();
        Cursor cursor = resolver.query(reviewUri,null, null, null, null);
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
