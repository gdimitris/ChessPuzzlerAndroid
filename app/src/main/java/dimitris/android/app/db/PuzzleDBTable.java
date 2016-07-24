package dimitris.android.app.db;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dimitris on 07/03/16.
 */
public class PuzzleDBTable {

    public static final String CONTENT_AUTHORITY = "com.dimitris.chesspuzzler";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PUZZLE_PATH = "puzzles";

    public PuzzleDBTable(){}

    public static abstract class PuzzleColumns implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PUZZLE_PATH).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PUZZLE_PATH;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PUZZLE_PATH;

        public static final String TABLE_NAME = "Puzzles";
        public static final String COLUMN_PUZZLE_ID = "_id";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_FEN = "fen";
        public static final String COLUMN_SOLUTION = "solution";
        public static final String COLUMN_COLLECTION_ID = "collection_id";
        public static final String COLUMN_REVIEW_ID = "review_id";

        public static final String[] ALL_COLUMNS = new String[]{ TABLE_NAME+"."+COLUMN_PUZZLE_ID,
                TABLE_NAME+"."+COLUMN_DESCRIPTION,TABLE_NAME+"."+COLUMN_FEN,
                TABLE_NAME+"."+COLUMN_SOLUTION,TABLE_NAME+"."+COLUMN_COLLECTION_ID,
                TABLE_NAME+"."+COLUMN_REVIEW_ID};

        public static final int PUZZLE_ID_COLUMN_NUM = 0;
        public static final int PUZZLE_DESCRIPTION_COLUMN_NUM = 1;
        public static final int PUZZLE_FEN_COLUMN_NUM = 2;
        public static final int PUZZLE_SOLUTION_COLUMN_NUM = 3;
        public static final int PUZZLE_REVIEW_ID_COLUMN_NUM = 5;

        public static String getPuzzleIdFromUri(Uri uri){
            return uri.getPathSegments().get(1);
        }

        public static String getCollectionIdFromUri(Uri uri){
            return uri.getPathSegments().get(2);
        }
    }
}
