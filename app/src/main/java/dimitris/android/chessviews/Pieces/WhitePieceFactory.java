package dimitris.android.chessviews.Pieces;

import android.graphics.Typeface;


public class WhitePieceFactory extends PieceFactory {

    public WhitePieceFactory(Typeface font, int size) {
        super(font, size);
    }

    @Override
    public Piece createKing() {
        return new WhiteKing(whitePaint, blackPaint);
    }

    @Override
    public Piece createQueen() {
        return new WhiteQueen(whitePaint, blackPaint);
    }

    @Override
    public Piece createRook() {
        return new WhiteRook(whitePaint, blackPaint);
    }

    @Override
    public Piece createBishop() {
        return new WhiteBishop(whitePaint, blackPaint);
    }

    @Override
    public Piece createKnight() {
        return new WhiteKnight(whitePaint, blackPaint);
    }

    @Override
    public Piece createPawn() {
        return new WhitePawn(whitePaint, blackPaint);
    }
}
