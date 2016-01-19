package dimitris.android.chessviews;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import dimitris.android.chessviews.Pieces.Piece;

public class SquareView extends Drawable {

    private final String name;
    private Rect rect;
    private Piece piece;
    private boolean isSelected;
    private SquareHighlighter selector;

    public SquareView(String name, Rect rect) {
        this.name = name;
        this.rect = rect;
        isSelected = false;
    }

    public String getName() {
        return name;
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

    @Override
    public void draw(Canvas canvas) {
        drawSelector(canvas);
        drawPiece(canvas);
    }

    private void drawSelector(Canvas canvas) {
        if(isSelected)
            selector.draw(canvas);
    }

    private void drawPiece(Canvas canvas) {
        if (piece != null)
            piece.draw(canvas);
    }

    public boolean isEmpty() {
        return piece == null;
    }

    @Override
    public boolean equals(Object o) {
        SquareView toCheck = (SquareView) o;

        return name.equals(toCheck.name) && piece == toCheck.piece;
    }

    public void setSelected(boolean isSelected) {
        if(isSelected)
            selector = new SquareHighlighter(getRect());
        else
            selector = null;

        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        return name;
    }

    public void resize(int size, int row, int col){
        rect.set(col * size, row * size, (col + 1) * size, (row + 1) * size);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}