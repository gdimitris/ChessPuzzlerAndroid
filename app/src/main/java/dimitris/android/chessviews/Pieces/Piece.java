package dimitris.android.chessviews.Pieces;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;


public abstract class Piece extends Drawable {

    private final Paint whitePaint;
    private final Paint blackPaint;
    protected String whiteLayerLetter;
    protected String blackLayerLetter;
    private Rect positionRect;
    private int currentPositionRow;
    private int currentPositionColumn;
    private int drawSize;

    public Piece(Paint whitePaint, Paint blackPaint) {
        this.whitePaint = whitePaint;
        this.blackPaint = blackPaint;
        this.positionRect = new Rect();
    }

    @Override
    public void draw(Canvas canvas) {
        int x = currentPositionColumn * drawSize;
        int y = (currentPositionRow + 1) * drawSize;

        canvas.drawText(whiteLayerLetter, x, y, whitePaint);
        Log.e("PieceWhiteLayer","Drawing letter '" + whiteLayerLetter + "' at x:" + x + " y:"+ y +" and drawSize: "+drawSize);
        canvas.drawText(blackLayerLetter, x, y, blackPaint);
        //Log.e("PieceBlackLayer","Drawing letter '" + blackLayerLetter + "' at x:" + x + " y:"+ y +" and drawSize: "+drawSize);
    }

    public void setWhiteLayerLetter(String layerLetter){
        this.whiteLayerLetter = layerLetter;
    }

    public void setBlackLayerLetter(String layerLetter){
        this.blackLayerLetter = layerLetter;
    }

    public void setPositionCoords(int row, int col){
        this.currentPositionColumn = col;
        this.currentPositionRow = row;
    }

    public Rect getPositionRect(){
        return positionRect;
    }

    public void setSize(int size){
        Log.e("setSize","Changing size from " + drawSize + " to "+size);
        drawSize = size;
        positionRect.set(currentPositionColumn * size, currentPositionRow * size, (currentPositionColumn + 1) * size, (currentPositionRow + 1) * size);
        Log.e("setSize", "Position rect is :" +positionRect.toString());
    }

    public abstract String toString();

    @Override
    public int getOpacity() {
        return 0;
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
    }

    @Override
    public void setAlpha(int alpha) {
    }
}
