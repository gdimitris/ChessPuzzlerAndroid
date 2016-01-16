package dimitris.chess.core;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dimitris.chess.core.Piece.PieceColor;
import dimitris.chess.core.Piece.PieceType;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class BitboardTests {

    private Bitboard bb;
    private Piece whiteKing;
    private Piece blackBishop;
    private Piece whitePawn;
    private Piece whiteRook;
    private Piece nullPiece;
    private Piece[] pieces;
    private MoveFactory moveFactory;

    @Before
    public void setUp(){
        bb = new Bitboard();
        whiteKing = PieceFactory.createPiece(PieceType.King, PieceColor.White);
        blackBishop = PieceFactory.createPiece(PieceType.Bishop, PieceColor.Black);
        whitePawn = PieceFactory.createPiece(PieceType.Pawn, PieceColor.White);
        whiteRook = PieceFactory.createPiece(PieceType.Rook, PieceColor.White);
        nullPiece = PieceFactory.createNullPiece();
        pieces = new Piece[]{whiteKing, blackBishop, whitePawn, whiteRook};
        moveFactory = new MoveFactory(bb);
    }

    @Test
    public void testSetsPieces(){
        String squares[] = {"e1","d5","a2","a1"};

        for(int i=0; i<squares.length;i++){
            bb.setPieceAtSquare(pieces[i], squares[i]);
            assertExpectedPieceAtSquare(pieces[i] ,squares[i]);
        }
    }

    @Test
    public void testRemovesPieces(){
        bb.setPosition("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0");
        String squares[] = { "a1","a2", "b2", "c4", "d5", "d8", "e8", "f8"};

        for(int i=0; i<squares.length; i++){
            bb.removePieceFromSquare(squares[i]);
            assertSquareEmpty(squares[i]);
        }
    }

    //Expected values calculated from here: http://cinnamonchess.altervista.org/bitboard_calculator/Calc.html
    @Test
    public void test_getsAllWhitePieces(){
        bb.setPosition("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0");

        UInt64 actual = bb.getAllWhitePieces();
        UInt64 expected = UInt64.create("101100000010100000010001110011110010001");

        assertEquals(expected, actual);
    }

    @Test
    public void test_getsAllBlackPieces(){
        bb.setPosition("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0");

        UInt64 actual = bb.getAllBlackPieces();
        UInt64 expected = UInt64.create("1011100111110011000010000000010000001000000000000000000000001000");

        assertEquals(expected, actual);
    }

    @Test
    public void test_makesMoves(){
        bb.setPosition("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0");
        Move move1 = moveFactory.createMove("c2","c3");
        Move move2 = moveFactory.createMove("d1","c2");
        Move move3 = moveFactory.createMove("e1", "f1");
        Move move4 = moveFactory.createMove("c2", "a4");

        Move moves[] = {move1, move2, move3, move4};
        Piece pieces[] = {whitePawn, blackBishop, whiteKing, blackBishop};

        for(int i=0;i<moves.length;i++){
            bb.doMove(moves[i]);
            assertMoveDone(pieces[i],moves[i]);
        }
    }

    @Test
    public void test_canUndoMove(){
        bb.setPosition("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0");
        Move move1 = moveFactory.createMove("c2","c3");
        bb.doMove(move1);
        assertMoveDone(whitePawn,move1);
        bb.undoMove(move1);
        assertMoveUndone(move1, whitePawn);
    }

    private void assertMoveUndone(Move move, Piece movedPiece){
        Piece capturedPiece = move.capturedPiece;
        assertExpectedPieceAtSquare(capturedPiece, move.destination);
        assertExpectedPieceAtSquare(movedPiece, move.source);

        Piece actualPiece = bb.getPieceAtSquare(move.source);
        assertEquals(movedPiece,actualPiece);
    }

    private void assertMoveDone(Piece piece, Move move) {
        assertExpectedPieceAtSquare(piece, move.destination);
        assertSquareEmpty(move.source);
    }

    private void assertSquareEmpty(String square) {
        assertExpectedPieceAtSquare(nullPiece, square);
    }

    private void assertExpectedPieceAtSquare(Piece expectedPiece, String square) {
        Piece actualPiece = bb.getPieceAtSquare(square);
        assertTrue(expectedPiece.equals(actualPiece));
    }
}
