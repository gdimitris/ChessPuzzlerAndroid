package dimitris.chessboardutils.tests;

import dimitris.chessboardutils.Piece;

import static dimitris.chessboardutils.Piece.PieceColor.*;
import static dimitris.chessboardutils.Piece.PieceType.*;
import static junit.framework.Assert.assertEquals;

/**
 * Created by dimitris on 8/30/15.
 */
public class ChessPiecesTestHelpers {

    public static void assertBlackPawn(Piece toTest){
        assertType(Pawn, toTest);
        assertColor(Black, toTest);
    }

    public static void assertWhitePawn(Piece toTest){
        assertType(Pawn, toTest);
        assertColor(White, toTest);
    }

    public static void assertBlackBishop(Piece toTest){
        assertType(Bishop, toTest);
        assertColor(Black, toTest);
    }

    public static void assertWhiteBishop(Piece toTest){
        assertType(Bishop, toTest);
        assertColor(White, toTest);
    }

    public static void assertBlackKnight(Piece toTest){
        assertType(Knight, toTest);
        assertColor(White, toTest);
    }

    public static void assertWhiteKnight(Piece toTest){
        assertType(Knight, toTest);
        assertColor(White, toTest);
    }

    public static void assertBlackRook(Piece toTest){
        assertType(Rook, toTest);
        assertColor(Black, toTest);
    }

    public static void assertWhiteRook(Piece toTest){
        assertType(Rook, toTest);
        assertColor(White, toTest);
    }

    public static void assertBlackQueen(Piece toTest){
        assertType(Queen, toTest);
        assertColor(Black, toTest);
    }

    public static void assertWhiteQueen(Piece toTest){
        assertType(Queen, toTest);
        assertColor(White, toTest);
    }

    public static void assertBlackKing(Piece toTest){
        assertType(King, toTest);
        assertColor(Black, toTest);
    }

    public static void assertWhiteKing(Piece toTest){
        assertType(King, toTest);
        assertColor(White, toTest);
    }

    public static void assertType(Piece.PieceType expectedType, Piece toTest){
        assertEquals(expectedType, toTest.type);
    }

    public static void assertColor(Piece.PieceColor expectedColor, Piece toTest) {
        assertEquals(expectedColor, toTest.color);
    }
}
