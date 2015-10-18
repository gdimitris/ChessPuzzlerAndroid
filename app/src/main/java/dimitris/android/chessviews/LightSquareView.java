package dimitris.android.chessviews;

import android.graphics.Color;
import android.graphics.Rect;


public class LightSquareView extends SquareView {

    public LightSquareView(String name, Rect rect) {
        super(name, rect);
        itsPaint.setColor(Color.rgb(255, 222, 173));
    }
}
