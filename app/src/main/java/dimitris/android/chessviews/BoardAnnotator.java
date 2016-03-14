package dimitris.android.chessviews;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

/**
 * Created by enviouscreep on 3/14/16.
 */
public class BoardAnnotator{

    private int squareSize;
    private Typeface font = Typeface.DEFAULT;
    private Paint paint;
    private Rect boardBounds;

    public BoardAnnotator(Rect boardBounds){
        initializePaint();
        setBoardBounds(boardBounds);
    }

    private void initializePaint(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setSubpixelText(true);
        paint.setTypeface(font);
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    public void setSquareSize(int size){
        this.squareSize = size;
        paint.setTextSize(squareSize/5);
    }

    public void setBoardBounds(Rect boardBounds){
        this.boardBounds = boardBounds;
    }

    public void drawAnnotations(Canvas canvas){
        drawRows(canvas);
        drawColumns(canvas);
    }

    private void drawRows(Canvas canvas){
        String rows = "abcdefgh";
        float currentX;
        float currentY;
        int inset = squareSize/10;

        for(int i=0;i<8;i++){
            currentX = inset+(i*squareSize);
            currentY = boardBounds.bottom-inset;
            String currentRow = String.valueOf(rows.charAt(i));

            canvas.drawText(currentRow,currentX,currentY,paint);
        }
    }

    private void drawColumns(Canvas canvas){
        String columns = "12345678";
        float currentX;
        float currentY;
        int insetX = squareSize/8;
        int insetY = squareSize/5;

        for(int i=0;i<8;i++){
            currentX = insetX;
            currentY = insetY +(i*squareSize);
            String currentCol = String.valueOf(columns.charAt(7-i));

            canvas.drawText(currentCol, currentX, currentY, paint);
        }
    }
}
