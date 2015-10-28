package dimitris.android.chessviews.Pieces;

import android.graphics.Typeface;

import static dimitris.android.chessviews.Pieces.Piece.PieceColor.*;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.*;


public class WhitePieceFactory extends PieceFactory {

    public WhitePieceFactory(Typeface font, int size) {
        super(font, size);
    }

    @Override
    public Piece createKing() {
        Piece piece =  new King(whitePaint, blackPaint, White);
        piece.setWhiteLayerLetter(WhiteKing_WhiteLayer);
        piece.setBlackLayerLetter(WhiteKing_BlackLayer);
        return piece;
    }

    @Override
    public Piece createQueen() {
        Piece piece = new Queen(whitePaint, blackPaint, White);
        piece.setWhiteLayerLetter(WhiteQueen_WhiteLayer);
        piece.setBlackLayerLetter(WhiteQueen_BlackLayer);
        return piece;
    }

    @Override
    public Piece createRook() {
        Piece piece = new Rook(whitePaint, blackPaint, White);
        piece.setWhiteLayerLetter(WhiteRook_WhiteLayer);
        piece.setBlackLayerLetter(WhiteRook_BlackLayer);
        return piece;
    }

    @Override
    public Piece createBishop() {
        Piece piece =  new Bishop(whitePaint, blackPaint, White);
        piece.setWhiteLayerLetter(WhiteBishop_WhiteLayer);
        piece.setBlackLayerLetter(WhiteBishop_BlackLayer);
        return piece;
    }

    @Override
    public Piece createKnight() {
        Piece piece = new Knight(whitePaint, blackPaint, White);
        piece.setWhiteLayerLetter(WhiteKnight_WhiteLayer);
        piece.setBlackLayerLetter(WhiteKnight_BlackLayer);
        return piece;
    }

    @Override
    public Piece createPawn() {
        Piece piece = new Pawn(whitePaint, blackPaint, White);
        piece.setWhiteLayerLetter(WhitePawn_WhiteLayer);
        piece.setBlackLayerLetter(WhitePawn_BlackLayer);
        return piece;
    }
}
