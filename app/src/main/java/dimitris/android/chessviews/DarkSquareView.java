package dimitris.android.chessviews;

import android.graphics.Color;
import android.graphics.Rect;


public class DarkSquareView extends SquareView {

    public DarkSquareView(String name, Rect rect) {
        super(name, rect);
        itsPaint.setColor(Color.rgb(160, 82, 45));
    }
}
