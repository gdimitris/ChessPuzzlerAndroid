package dimitris.android.chessviews.Pieces;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


public abstract class Piece {

    private final Paint whitePaint;
    private final Paint blackPaint;
    protected String whiteLayerLetter;
    protected String blackLayerLetter;
    protected PieceColor color;

    public Piece(Paint whitePaint, Paint blackPaint, PieceColor color) {
        this.whitePaint = whitePaint;
        this.blackPaint = blackPaint;
        this.color = color;
    }

    public void draw(Canvas canvas, Rect rect) {
        canvas.drawText(whiteLayerLetter, rect.left, rect.bottom, whitePaint);
        canvas.drawText(blackLayerLetter, rect.left, rect.bottom, blackPaint);
    }

    public PieceColor getColor() {
        return color;
    }

    public abstract String toString();

    public enum PieceColor {
        White,
        Black
    }

    public void setWhiteLayerLetter(String layerLetter){
        this.whiteLayerLetter = layerLetter;
    }

    public void setBlackLayerLetter(String layerLetter){
        this.blackLayerLetter = layerLetter;
    }
}
