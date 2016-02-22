package dimitris.android.chessviews.Pieces;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;


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
        canvas.drawText(blackLayerLetter, x, y, blackPaint);
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
        positionRect.set(currentPositionColumn * drawSize, currentPositionRow * drawSize, (currentPositionColumn + 1) * drawSize, (currentPositionRow + 1) * drawSize);
    }

    public Rect getPositionRect(){
        return positionRect;
    }

    public void setSize(int size){
        drawSize = size;
        positionRect.set(currentPositionColumn * size, currentPositionRow * size, (currentPositionColumn + 1) * size, (currentPositionRow + 1) * size);
        whitePaint.setTextSize(size);
        blackPaint.setTextSize(size);
    }

    public int getRow(){
        return currentPositionRow;
    }

    public int getCol(){
        return currentPositionColumn;
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
