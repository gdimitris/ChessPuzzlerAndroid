package dimitris.android.app;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;

import com.dimitris.chesspuzzler.R;

/**
 * Created by dimitris on 3/27/16.
 */
public class ButtonWrappingCursorAdapter extends SimpleCursorAdapter {

    private PuzzleLoaderCallback callback;

    public ButtonWrappingCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        callback = (PuzzleLoaderCallback) context;
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        super.bindView(view,context,cursor);

        final int currentCollectionId = cursor.getInt(0);
        Button button = (Button) view.findViewById(R.id.reviewButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.loadRequestedForPuzzlesWithCollectionId(currentCollectionId);
            }
        });
    }
}
