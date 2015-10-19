package dimitris.android.chessviews.Pieces;

import android.graphics.Typeface;


public class BlackPieceFactory extends PieceFactory {

    public BlackPieceFactory(Typeface font, int size) {
        super(font, size);
    }

    @Override
    public Piece createKing() {
        return new BlackKing(whitePaint, blackPaint);
    }

    @Override
    public Piece createQueen() {
        return new BlackQueen(whitePaint, blackPaint);
    }

    @Override
    public Piece createRook() {
        return new BlackRook(whitePaint, blackPaint);
    }

    @Override
    public Piece createBishop() {
        return new BlackBishop(whitePaint, blackPaint);
    }

    @Override
    public Piece createKnight() {
        return new BlackKnight(whitePaint, blackPaint);
    }

    @Override
    public Piece createPawn() {
        return new BlackPawn(whitePaint, blackPaint);
    }

}
