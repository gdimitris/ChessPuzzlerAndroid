package dimitris.android.chessviews;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class SquareSelector {

    protected final Paint selectionPaint;
    protected final Rect selectionRect;

    public SquareSelector(Rect squareRect) {
        selectionPaint = new Paint();
        selectionPaint.setColor(Color.YELLOW);
        selectionPaint.setAlpha(120);
        selectionPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        selectionRect = squareRect;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(selectionRect, selectionPaint);
    }
}
