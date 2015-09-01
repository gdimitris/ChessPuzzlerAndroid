package dimitris.chessboardutils.tests;

import org.junit.Before;
import org.junit.Test;

import dimitris.chessboardutils.Board;
import dimitris.chessboardutils.BoardFactory;
import dimitris.chessboardutils.Piece;

import static dimitris.chessboardutils.tests.ChessPiecesTestHelpers.assertBlackKing;
import static dimitris.chessboardutils.tests.ChessPiecesTestHelpers.assertBlackPawn;
import static dimitris.chessboardutils.tests.ChessPiecesTestHelpers.assertBlackQueen;
import static dimitris.chessboardutils.tests.ChessPiecesTestHelpers.assertBlackRook;
import static dimitris.chessboardutils.tests.ChessPiecesTestHelpers.assertWhiteKing;
import static dimitris.chessboardutils.tests.ChessPiecesTestHelpers.assertWhiteKnight;
import static dimitris.chessboardutils.tests.ChessPiecesTestHelpers.assertWhitePawn;
import static dimitris.chessboardutils.tests.ChessPiecesTestHelpers.assertWhiteRook;


/**
 * Created by dimitris on 8/30/15.
 */
public class BoardFactoryTests {

    @Before
    public void setup(){

    }

    @Test
    public void testCreatesInitialPositionFromFEN(){
        Board board = BoardFactory.create("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        Piece piece = board.getPieceAt("e2");
        assertWhitePawn(piece);

        piece = board.getPieceAt("e1");
        assertWhiteKing(piece);

        piece = board.getPieceAt("d8");
        assertBlackQueen(piece);

        piece = board.getPieceAt("h1");
        assertWhiteRook(piece);

        piece = board.getPieceAt("a8");
        assertBlackRook(piece);
    }

    @Test
    public void testCreatesRandomPositionFromFen(){
        Board board = BoardFactory.create("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R");
        Piece piece = board.getPieceAt("f3");
        assertWhiteKnight(piece);

        piece = board.getPieceAt("c5");
        assertBlackPawn(piece);

        piece = board.getPieceAt("e4");
        assertWhitePawn(piece);

    }


    @Test
    public void testCreatedAnotherRandomPositionFromFen(){
        Board board = BoardFactory.create("7K/8/k1P5/7p/8/8/8/8");

        Piece piece = board.getPieceAt("a6");
        assertBlackKing(piece);

        piece = board.getPieceAt("h8");
        assertWhiteKing(piece);

        piece = board.getPieceAt("h5");
        assertBlackPawn(piece);

        piece = board.getPieceAt("c6");
        assertWhitePawn(piece);
    }
}
