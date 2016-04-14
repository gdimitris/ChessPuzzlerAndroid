package dimitris.android.app;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dimitris on 14/04/16.
 */

public class CardDividerItemDecorator extends RecyclerView.ItemDecoration {
    private int dividerHeight;

    public CardDividerItemDecorator(int dividerHeight){
        this.dividerHeight = dividerHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;
    }
}
