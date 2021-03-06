package dimitris.android.app.db;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dimitris on 3/27/16.
 */
public class PuzzleCollectionDBTable {

    public static final String CONTENT_AUTHORITY = "com.dimitris.chesspuzzler";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String COLLECTION_PATH = "collections";

    public static abstract class PuzzleCollectionColumns implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(COLLECTION_PATH).build();
        public static final Uri PUZZLE_COUNT_URI = CONTENT_URI.buildUpon().appendPath("count").build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + COLLECTION_PATH;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + COLLECTION_PATH;

        public static final String TABLE_NAME = "Collections";
        public static final String COLUMN_COLLECTION_ID = "_id";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String[] ALL_COLUMNS = new String[]{COLUMN_COLLECTION_ID, COLUMN_DESCRIPTION};

        public static final int COLLECTION_ID_COLUMN_NUM = 0;
        public static final int COLLECTION_DESCRIPTION_COLUMN_NUM = 1;

        public static String getCollectionIdFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }
}
