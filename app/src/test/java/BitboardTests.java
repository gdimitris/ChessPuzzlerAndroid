import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dimitris.dimitris.chess.bitboards.Bitboard;

import static dimitris.dimitris.chess.bitboards.Bitboard.PieceColor.*;
import static dimitris.dimitris.chess.bitboards.Bitboard.PieceType.*;
import static dimitris.dimitris.chess.bitboards.Bitboard.PieceType.None;
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
    public void setUpFen_WhiteKnightExistsInD5(){
        bb.setFenPosition("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0");

        assertEquals(Knight, bb.getPieceType("d5"));
        assertEquals(White, bb.getPieceColor("d5"));
    }

    @Test
    public void setup_whiteRook_at_a1(){
        bb.setPieceAtSquare(Rook, White, "a1");

        assertEquals(Rook, bb.getPieceType("a1"));
        assertEquals(White, bb.getPieceColor("a1"));
    }

    @Test
    public void setup_whitePawn_at_a2(){
        bb.setPieceAtSquare(Pawn,White, "a2");

        assertEquals(Pawn, bb.getPieceType("a2"));
        assertEquals(White, bb.getPieceColor("a2"));
    }

    @Test
    public void setup_blackBishop_at_d5(){
        bb.setPieceAtSquare(Bishop, Black, "d5");

        assertEquals(Bishop, bb.getPieceType("d5"));
        assertEquals(Black, bb.getPieceColor("d5"));
    }

    @Test
    public void setup_whiteKing_at_e1(){
        bb.setPieceAtSquare(King, White, "e1");

        assertEquals(King, bb.getPieceType("e1"));
        assertEquals(White, bb.getPieceColor("e1"));
    }

    @Test
    public void testUnsetsPieceFromD5(){
        bb.setFenPosition("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0");

        assertEquals(Knight, bb.getPieceType("d5"));
        bb.removePieceFromSquare("d5");

        assertEquals(None, bb.getPieceType("d5"));
        assertEquals(Bitboard.PieceColor.None, bb.getPieceColor("d5"));
    }
}
