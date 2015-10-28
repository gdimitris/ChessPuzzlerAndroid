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

    public Piece(Paint whitePaint, Paint blackPaint) {
        this.whitePaint = whitePaint;
        this.blackPaint = blackPaint;
    }

    public void draw(Canvas canvas, Rect rect) {
        canvas.drawText(whiteLayerLetter, rect.left, rect.bottom, whitePaint);
        canvas.drawText(blackLayerLetter, rect.left, rect.bottom, blackPaint);
    }

    public String getWhiteLayer() {
        return whiteLayerLetter;
    }

    public String getBlackLayer() {
        return blackLayerLetter;
    }

    public Paint getBlackPaint() {
        return blackPaint;
    }

    public Paint getWhitePaint() {
        return whitePaint;
    }

    public PieceColor getColor() {
        return color;
    }

    public boolean canBeCaptured() {
        return true;
    }

    public abstract String toString();

//    public abstract String getSANString();

    public enum PieceColor {
        White,
        Black
    }

}
