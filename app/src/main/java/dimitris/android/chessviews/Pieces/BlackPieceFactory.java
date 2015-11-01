package dimitris.android.chessviews.Pieces;

import android.graphics.Typeface;

import static dimitris.android.chessviews.Pieces.Piece.PieceColor.Black;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.*;


public class BlackPieceFactory extends AbstractPieceFactory {

    public BlackPieceFactory(Typeface font, int size) {
        super(font, size);
    }

    @Override
    public Piece createKing() {
        Piece piece =  new King(whitePaint, blackPaint, Black);
        piece.setWhiteLayerLetter(BlackKing_WhiteLayer);
        piece.setBlackLayerLetter(BlackKing_BlackLayer);
        return piece;
    }

    @Override
    public Piece createQueen() {
        Piece piece = new Queen(whitePaint, blackPaint, Black);
        piece.setWhiteLayerLetter(BlackQueen_WhiteLayer);
        piece.setBlackLayerLetter(BlackQueen_BlackLayer);
        return piece;
    }

    @Override
    public Piece createRook() {
        Piece piece = new Rook(whitePaint, blackPaint, Black);
        piece.setWhiteLayerLetter(BlackRook_WhiteLayer);
        piece.setBlackLayerLetter(BlackRook_BlackLayer);
        return piece;
    }

    @Override
    public Piece createBishop() {
        Piece piece = new Bishop(whitePaint, blackPaint, Black);
        piece.setWhiteLayerLetter(BlackBishop_WhiteLayer);
        piece.setBlackLayerLetter(BlackBishop_BlackLayer);
        return piece;
    }

    @Override
    public Piece createKnight() {
        Piece piece = new Knight(whitePaint, blackPaint, Black);
        piece.setWhiteLayerLetter(BlackKnight_WhiteLayer);
        piece.setBlackLayerLetter(BlackKnight_BlackLayer);
        return piece;
    }

    @Override
    public Piece createPawn() {
        Piece piece = new Pawn(whitePaint, blackPaint, Black);
        piece.setWhiteLayerLetter(BlackPawn_WhiteLayer);
        piece.setBlackLayerLetter(BlackPawn_BlackLayer);
        return piece;
    }

}
