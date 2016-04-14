package dimitris.android.app;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dimitris.chesspuzzler.R;

/**
 * Created by dimitris on 12/04/16.
 */
public class CursorRecyclerViewAdapter extends RecyclerView.Adapter<CursorRecyclerViewAdapter.ViewHolder> {

    private Cursor cursor;
    private DataSetObserver dataSetObserver;


    public CursorRecyclerViewAdapter(){
        this.cursor = null;
        createDataSetObserver();
    }

    private void createDataSetObserver() {
        dataSetObserver = new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                notifyDataSetChanged();
            }

            @Override
            public void onInvalidated() {
                super.onInvalidated();
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public CursorRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.puzzle_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (!cursor.moveToPosition(position)) {
            throw new RuntimeException("Cursor out of bounds for position: " + position);
        }

        holder.descriptionTextView.setText(cursor.getString(1));
        holder.puzzleCount.setText(cursor.getString(2));
        holder.collectionId = cursor.getInt(0);

    }

    public void changeCursor(Cursor cursor){
        if (this.cursor == cursor)
            return;

        if(this.cursor != null){
            this.cursor.unregisterDataSetObserver(dataSetObserver);
            this.cursor.close();
        }

        this.cursor = cursor;
        this.cursor.registerDataSetObserver(dataSetObserver);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (cursor != null)
            return cursor.getCount();
        return 0;
    }

    @Override
    public long getItemId(int position) {
        if (cursor != null) {
            cursor.moveToPosition(position);

            int id_index = cursor.getColumnIndex("_id");
            cursor.getLong(id_index);
        }
        return -1;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(true);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button reviewButton;
        public TextView descriptionTextView;
        public TextView puzzleCount;
        public int collectionId;

        public ViewHolder(View itemView)  {
            super(itemView);
            final Context context = itemView.getContext();
            reviewButton = (Button) itemView.findViewById(R.id.reviewButton);
            descriptionTextView = (TextView) itemView.findViewById(R.id.label);
            puzzleCount = (TextView) itemView.findViewById(R.id.puzzleCount);

            reviewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent playActivityIntent = new Intent(context, PlayPuzzleActivity.class);
                    playActivityIntent.putExtra("requestedId",collectionId);
                    //, ActivityOptions.makeSceneTransitionAnimation(context.).toBundle()
                    context.startActivity(playActivityIntent);
                }
            });
        }
    }
}
