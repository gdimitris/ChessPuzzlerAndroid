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

    public Piece(Paint whitePaint, Paint blackPaint) {
        this.whitePaint = whitePaint;
        this.blackPaint = blackPaint;
        this.positionRect = new Rect(0,0,60,60);
    }

    public void draw(Canvas canvas) {
        canvas.drawText(whiteLayerLetter, positionRect.left, positionRect.bottom, whitePaint);
        canvas.drawText(blackLayerLetter, positionRect.left, positionRect.bottom, blackPaint);
    }

    public abstract String toString();

    public void setWhiteLayerLetter(String layerLetter){
        this.whiteLayerLetter = layerLetter;
    }

    public void setBlackLayerLetter(String layerLetter){
        this.blackLayerLetter = layerLetter;
    }

    public void setPositionRect(int left,int top, int right, int bottom){
        positionRect.set(left, top, right, bottom);
    }

    public Rect getPositionRect(){
        return positionRect;
    }

    public void setSize(int size){
        int left = positionRect.left;
        int top = positionRect.top;

        positionRect.set(left,top,left+size,top+size);
    }
}
