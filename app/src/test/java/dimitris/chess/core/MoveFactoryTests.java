package dimitris.chess.core;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static dimitris.chess.core.Piece.PieceColor.Black;
import static dimitris.chess.core.Piece.PieceColor.White;
import static dimitris.chess.core.Piece.PieceType.Knight;
import static dimitris.chess.core.Piece.PieceType.Pawn;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by dimitris on 1/11/16.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MoveFactoryTests {

    private MoveFactory moveFactory;
    private Piece whitePawn;
    private Piece nullPiece;
    private Piece blackKnight;

    @Before
    public void setUp(){
        whitePawn = PieceFactory.createPiece(Pawn,White);
        nullPiece = PieceFactory.createNullPiece();
        blackKnight = PieceFactory.createPiece(Knight,Black);

        Board board = new Bitboard();
        board.setPosition("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R");
        moveFactory = new MoveFactory(board);
    }

    //Nd4
    @Test
    public void testCreatesSimpleMove(){
        Move move = moveFactory.createMove("c2","c3");

        assertEquals("c2", move.source);
        assertEquals("c3", move.destination);

        assertExpectedPiece(move.movingPiece, whitePawn);
        assertExpectedPiece(move.capturedPiece, nullPiece);
        assertFalse(move.isCapture);
    }

    //Nxd4
    //cxd4
    @Test
    public void testCreatesCaptureMove(){
        Move move = moveFactory.createMove("d4","c2");

        assertEquals("d4", move.source);
        assertEquals("c2", move.destination);

        assertExpectedPiece(move.movingPiece, blackKnight);
        assertExpectedPiece(move.capturedPiece, whitePawn);
        assertTrue(move.isCapture);
    }


    private void assertExpectedPiece(Piece movingPiece, Piece expectedPiece){
        assertTrue(movingPiece.equals(expectedPiece));
    }

    //To be covered:


    //Nxd4+
    //Nxd4#

    //O-O
    //O-O-O
    //d8=Q
    //d8=Q+
    //d8=Q#

}
