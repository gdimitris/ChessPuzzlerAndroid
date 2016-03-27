package dimitris.android.app;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.dimitris.chesspuzzler.R;

/**
 * Created by dimitris on 3/27/16.
 */
public class ButtonWrappingCursorAdapter extends SimpleCursorAdapter {
    public ButtonWrappingCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        super.bindView(view,context,cursor);
        Button button = (Button) view.findViewById(R.id.reviewButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked review button", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
