package dimitris.android.chessviews;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class SquareHighlighter {

    protected final Paint selectionPaint;
    protected Rect selectionRect;

    public SquareHighlighter(Rect squareRect) {
        selectionPaint = new Paint();
        selectionPaint.setColor(Color.YELLOW);
        selectionPaint.setAlpha(120);
        selectionPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        selectionRect = squareRect;
    }

    public void setSquare(Rect rect){
        selectionRect = rect;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(selectionRect, selectionPaint);
    }
}
