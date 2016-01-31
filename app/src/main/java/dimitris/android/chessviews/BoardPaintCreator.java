package dimitris.android.chessviews;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by dimitris on 1/31/16.
 */
public class BoardPaintCreator {
    public static Paint createPaintWithSquareSize(int squareSize){
        int darkSquareColor = Color.argb(255 , 160, 82, 45);
        int lightSquareColor = Color.argb(255 ,255, 222, 173);
        Bitmap bitmap = Bitmap.createBitmap(squareSize * 2, squareSize * 2, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        paintLightSquaresInCanvas(squareSize, canvas, getFillPaintWithColor(lightSquareColor));
        paintDarkSquaresInCanvas(squareSize, canvas, getFillPaintWithColor(darkSquareColor));

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(new BitmapShader(bitmap, BitmapShader.TileMode.REPEAT, BitmapShader.TileMode.REPEAT));
        return paint;
    }

    private static Paint getFillPaintWithColor(int lightSquareColor) {
        Paint fillColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillColor.setStyle(Paint.Style.FILL);
        fillColor.setColor(lightSquareColor);
        return fillColor;
    }

    private static void paintLightSquaresInCanvas(int squareSize, Canvas canvas, Paint fillColor) {
        Rect rect = new Rect(0, 0, squareSize, squareSize);
        canvas.drawRect(rect, fillColor);
        rect.offset(squareSize, squareSize);
        canvas.drawRect(rect, fillColor);
    }

    private static void paintDarkSquaresInCanvas(int squareSize, Canvas canvas, Paint fillColor){
        Rect rect = new Rect(0,0, squareSize, squareSize);
        rect.offset(squareSize,0);
        canvas.drawRect(rect, fillColor);
        rect.offset(-squareSize, squareSize);
        canvas.drawRect(rect, fillColor);
    }
}
