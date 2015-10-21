package dimitris.android.chessviews;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import dimitris.android.chessviews.Pieces.Piece;

public abstract class SquareView {

    protected final Paint itsPaint;
    private final String name;
    private final Rect rect;
    private Piece piece;
    private boolean isSelected;
    private SquareHighlighter selector;

    public SquareView(String name, Rect rect) {
        this.name = name;
        this.rect = rect;
        itsPaint = new Paint();
        isSelected = false;
        selector = new SquareHighlighter(rect);
    }

    public String getName() {
        return name;
    }

    public boolean contains(int x, int y) {
        return rect.contains(x, y);
    }

    public Rect getRect() {
        return rect;
    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(rect, itsPaint);
        drawSelector(canvas);
        drawPiece(canvas);
    }

    private void drawSelector(Canvas canvas) {
        if (isSelected)
            selector.draw(canvas);
    }

    private void drawPiece(Canvas canvas) {
        if (piece != null)
            piece.draw(canvas, getRect());
    }

    public boolean isEmpty() {
        return (piece == null);
    }

    @Override
    public boolean equals(Object o) {
        SquareView toCheck = (SquareView) o;
        if (name.equals(toCheck.name) && piece == toCheck.piece)
            return true;
        return false;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        return name;
    }
}