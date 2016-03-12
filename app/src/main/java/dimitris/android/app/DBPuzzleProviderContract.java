package dimitris.android.app;

import android.provider.BaseColumns;

/**
 * Created by dimitris on 07/03/16.
 */
public class DBPuzzleProviderContract {

    public DBPuzzleProviderContract(){}

    public static abstract class PuzzleTableColumns implements BaseColumns{
        public static final String TABLE_NAME = "Puzzles";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_FEN = "fen";
        public static final String COLUMN_SOLUTION = "solution";
        public static final String COLUMN_TYPE_ID = "type_id";

        public static final int PUZZLE_ID_COLUMN_NUM = 0;
        public static final int PUZZLE_DESCRIPTION_COLUMN_NUM = 1;
        public static final int PUZZLE_FEN_COLUMN_NUM = 2;
        public static final int PUZZLE_SOLUTION_COLUMN_NUM = 3;
    }

}
