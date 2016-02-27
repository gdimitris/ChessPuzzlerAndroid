package dimitris.android.chessviews.Pieces;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


public abstract class Piece {

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

    public void setSize(int size){
        drawSize = size;
        positionRect.set(currentPositionColumn * size, currentPositionRow * size, (currentPositionColumn + 1) * size, (currentPositionRow + 1) * size);
        whitePaint.setTextSize(size);
        blackPaint.setTextSize(size);
    }

    public int getDrawSize(){
        return drawSize;
    }

    public int getRow(){
        return currentPositionRow;
    }

    public int getCol(){
        return currentPositionColumn;
    }

}
