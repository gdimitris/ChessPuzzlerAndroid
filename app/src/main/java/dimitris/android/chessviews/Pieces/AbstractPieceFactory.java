package dimitris.android.chessviews.Pieces;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;


public abstract class AbstractPieceFactory {

    private final Typeface font;
    private final int size;
    protected Paint blackPaint;
    protected Paint whitePaint;

    public AbstractPieceFactory(Typeface font, int size) {
        this.font = font;
        this.size = size;
        initializePaints();
    }

    private void initializePaints() {
        blackPaint = new Paint();
        setupPaintProperties(blackPaint, Color.rgb(0, 0, 0));
        whitePaint = new Paint();
        setupPaintProperties(whitePaint, Color.rgb(255, 255, 255));
    }

    private void setupPaintProperties(Paint paint, int color) {
        paint.setColor(color);
        paint.setTypeface(font);
        paint.setAntiAlias(true);
        paint.setTextSize(size);
    }

    public abstract Piece createKing();

    public abstract Piece createQueen();

    public abstract Piece createRook();

    public abstract Piece createBishop();

    public abstract Piece createKnight();

    public abstract Piece createPawn();

    public Piece createPiece(char piece) {
        piece = Character.toLowerCase(piece);
        switch (piece) {
            case 'k':
                return createKing();
            case 'q':
                return createQueen();
            case 'r':
                return createRook();
            case 'b':
                return createBishop();
            case 'n':
                return createKnight();
            case 'p':
                return createPawn();
            default:
                throw new RuntimeException("Character '"+piece+"' not a valid piece...");
        }
    }
}
