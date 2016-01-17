package dimitris.android.chessviews;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Rect;


public class LightSquareView extends SquareView {

    public LightSquareView(String name, Rect rect) {
        super(name, rect);
        itsPaint.setColor(Color.rgb(255, 222, 173));
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
