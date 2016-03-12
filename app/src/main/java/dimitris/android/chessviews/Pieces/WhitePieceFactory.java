package dimitris.android.chessviews.Pieces;

import android.graphics.Typeface;

import static dimitris.android.chessviews.Pieces.PieceLayersHelper.WhiteBishop_BlackLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.WhiteBishop_WhiteLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.WhiteKing_BlackLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.WhiteKing_WhiteLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.WhiteKnight_BlackLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.WhiteKnight_WhiteLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.WhitePawn_BlackLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.WhitePawn_WhiteLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.WhiteQueen_BlackLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.WhiteQueen_WhiteLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.WhiteRook_BlackLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.WhiteRook_WhiteLayer;


public class WhitePieceFactory extends AbstractPieceFactory {

    public WhitePieceFactory(Typeface font, int size) {
        super(font, size);
    }

    @Override
    public Piece createKing() {
        Piece piece =  new King(whitePaint, blackPaint);
        piece.setWhiteLayerLetter(WhiteKing_WhiteLayer);
        piece.setBlackLayerLetter(WhiteKing_BlackLayer);
        return piece;
    }

    @Override
    public Piece createQueen() {
        Piece piece = new Queen(whitePaint, blackPaint);
        piece.setWhiteLayerLetter(WhiteQueen_WhiteLayer);
        piece.setBlackLayerLetter(WhiteQueen_BlackLayer);
        return piece;
    }

    @Override
    public Piece createRook() {
        Piece piece = new Rook(whitePaint, blackPaint);
        piece.setWhiteLayerLetter(WhiteRook_WhiteLayer);
        piece.setBlackLayerLetter(WhiteRook_BlackLayer);
        return piece;
    }

    @Override
    public Piece createBishop() {
        Piece piece =  new Bishop(whitePaint, blackPaint);
        piece.setWhiteLayerLetter(WhiteBishop_WhiteLayer);
        piece.setBlackLayerLetter(WhiteBishop_BlackLayer);
        return piece;
    }

    @Override
    public Piece createKnight() {
        Piece piece = new Knight(whitePaint, blackPaint);
        piece.setWhiteLayerLetter(WhiteKnight_WhiteLayer);
        piece.setBlackLayerLetter(WhiteKnight_BlackLayer);
        return piece;
    }

    @Override
    public Piece createPawn() {
        Piece piece = new Pawn(whitePaint, blackPaint);
        piece.setWhiteLayerLetter(WhitePawn_WhiteLayer);
        piece.setBlackLayerLetter(WhitePawn_BlackLayer);
        return piece;
    }
}
