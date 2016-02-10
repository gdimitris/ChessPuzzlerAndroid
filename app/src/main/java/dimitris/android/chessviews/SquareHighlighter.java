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

    public void setSquare(Square square){
        if(square!= null)
            selectionRect = square.getRect();
        else
            selectionRect = null;
    }

    public void draw(Canvas canvas) {
        if(selectionRect!= null)
            canvas.drawRect(selectionRect, selectionPaint);
    }
}
