import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dimitris.dimitris.chess.bitboards.Bitboard;
import dimitris.dimitris.chess.bitboards.UInt64;

import static dimitris.dimitris.chess.bitboards.Bitboard.*;
import static dimitris.dimitris.chess.bitboards.Bitboard.PieceColor.*;
import static dimitris.dimitris.chess.bitboards.Bitboard.PieceType.*;
import static dimitris.dimitris.chess.bitboards.Bitboard.PieceType.NullPiece;
import static junit.framework.Assert.assertEquals;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class BitboardTests {

    private Bitboard bb;

    @Before
    public void setUp(){
        bb = new Bitboard();
    }

    @Test
    public void testSetsPieces(){
        String squares[] = {"e1","d5","a2", "a1"};
        PieceType types[] = { King, Bishop, Pawn, Rook};
        PieceColor colors[] = { White, Black, White, White};

        for(int i=0; i<squares.length;i++){
            bb.setPieceAtSquare(types[i],colors[i],squares[i]);
            assertCorrectPiece(types[i],colors[i],squares[i]);
        }
    }

    private void assertCorrectPiece(PieceType type, PieceColor color, String square) {
        assertEquals(type, bb.getPieceType(square));
        assertEquals(color, bb.getPieceColor(square));
    }

    @Test
    public void testUnsetsPieces(){
        bb.setFenPosition("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0");
        String squares[] = { "a1","a2", "b2", "c4", "d5", "d8", "e8", "f8"};

        for(int i=0; i<squares.length; i++){
            bb.removePieceFromSquare(squares[i]);
            assertSquareEmpty(squares[i]);
        }
    }

    private void assertSquareEmpty(String square) {
        assertEquals(NullPiece, bb.getPieceType(square));
        assertEquals(NullColor,bb.getPieceColor(square));
    }


    //Expected values calculated from here: http://cinnamonchess.altervista.org/bitboard_calculator/Calc.html
    @Test
    public void test_getsAllWhitePieces(){
        bb.setFenPosition("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0");

        UInt64 actual = bb.getAllWhitePieces();
        UInt64 expected = UInt64.create("101100000010100000010001110011110010001");

        assertEquals(expected, actual);
    }

    @Test
    public void test_getsAllBlackPieces(){
        bb.setFenPosition("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0");

        UInt64 actual = bb.getAllBlackPieces();
        UInt64 expected = UInt64.create("1011100111110011000010000000010000001000000000000000000000001000");

        assertEquals(expected, actual);
    }
}
