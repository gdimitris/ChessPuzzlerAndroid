package dimitris.android.chessviews.Pieces;

import android.graphics.Typeface;

import static dimitris.android.chessviews.Pieces.PieceLayersHelper.BlackBishop_BlackLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.BlackBishop_WhiteLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.BlackKing_BlackLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.BlackKing_WhiteLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.BlackKnight_BlackLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.BlackKnight_WhiteLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.BlackPawn_BlackLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.BlackPawn_WhiteLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.BlackQueen_BlackLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.BlackQueen_WhiteLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.BlackRook_BlackLayer;
import static dimitris.android.chessviews.Pieces.PieceLayersHelper.BlackRook_WhiteLayer;


public class BlackPieceFactory extends AbstractPieceFactory {

    public BlackPieceFactory(Typeface font, int size) {
        super(font, size);
    }

    @Override
    public Piece createKing() {
        Piece piece =  new King(whitePaint, blackPaint);
        piece.setWhiteLayerLetter(BlackKing_WhiteLayer);
        piece.setBlackLayerLetter(BlackKing_BlackLayer);
        return piece;
    }

    @Override
    public Piece createQueen() {
        Piece piece = new Queen(whitePaint, blackPaint);
        piece.setWhiteLayerLetter(BlackQueen_WhiteLayer);
        piece.setBlackLayerLetter(BlackQueen_BlackLayer);
        return piece;
    }

    @Override
    public Piece createRook() {
        Piece piece = new Rook(whitePaint, blackPaint);
        piece.setWhiteLayerLetter(BlackRook_WhiteLayer);
        piece.setBlackLayerLetter(BlackRook_BlackLayer);
        return piece;
    }

    @Override
    public Piece createBishop() {
        Piece piece = new Bishop(whitePaint, blackPaint);
        piece.setWhiteLayerLetter(BlackBishop_WhiteLayer);
        piece.setBlackLayerLetter(BlackBishop_BlackLayer);
        return piece;
    }

    @Override
    public Piece createKnight() {
        Piece piece = new Knight(whitePaint, blackPaint);
        piece.setWhiteLayerLetter(BlackKnight_WhiteLayer);
        piece.setBlackLayerLetter(BlackKnight_BlackLayer);
        return piece;
    }

    @Override
    public Piece createPawn() {
        Piece piece = new Pawn(whitePaint, blackPaint);
        piece.setWhiteLayerLetter(BlackPawn_WhiteLayer);
        piece.setBlackLayerLetter(BlackPawn_BlackLayer);
        return piece;
    }

}
