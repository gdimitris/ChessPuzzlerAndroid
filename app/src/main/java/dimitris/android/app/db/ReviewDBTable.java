package dimitris.android.app.db;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dimitris on 4/9/16.
 */
public class ReviewDBTable {
    public static final String CONTENT_AUTHORITY = "com.dimitris.chesspuzzler";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String REVIEW_PATH = "reviews";

    public ReviewDBTable(){}

    public static abstract class ReviewColumns implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(REVIEW_PATH).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + REVIEW_PATH;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + REVIEW_PATH;

        public static final String TABLE_NAME = "Reviews";
        public static final String COLUMN_REVIEW_ID = "_id";
        public static final String COLUMN_EASINESS_FACTOR = "easiness_factor";
        public static final String COLUMN_REVIEW_INTERVAL = "review_interval";
        public static final String COLUMN_NEXT_REVIEW = "next_review";
        public static final String COLUMN_LAST_REVIEWED = "last_reviewed";

        public static final int REVIEW_ID_COLUMN_NUM = 0;
        public static final int EASINESS_FACTOR_COLUMN_NUM = 1;
        public static final int REVIEW_INTERVAL = 2;
        public static final int NEXT_REVIEW_COLUMN_NUM = 3;
        public static final int LAST_REVIEWED_COLUMN_NUM = 4;

        public static String getReviewIdFromUri(Uri reviewUri) {
            return reviewUri.getPathSegments().get(1);
        }
    }
}
