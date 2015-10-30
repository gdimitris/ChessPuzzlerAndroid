import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dimitris.dimitris.chess.bitboards.Bitboard;

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

        assertEquals(bb.getPieceType("d5"), Bitboard.PieceType.Knight);
        assertEquals(bb.getPieceColor("d5"), Bitboard.PieceColor.White);
    }

    @Test
    public void setup_whiteRook_at_a1(){
        bb.setPieceAtSquare(Bitboard.PieceType.Rook, Bitboard.PieceColor.White, "a1");
        assertEquals(bb.getPieceType("a1"), Bitboard.PieceType.Rook);
        assertEquals(bb.getPieceColor("a1"), Bitboard.PieceColor.White);
    }
}
