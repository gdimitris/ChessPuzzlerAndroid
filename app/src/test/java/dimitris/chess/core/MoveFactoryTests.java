package dimitris.chess.core;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dimitris on 1/11/16.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MoveFactoryTests {

    private MoveFactory moveFactory;

    @Before
    public void setUp(){

        Bitboard board = new Bitboard();
        board.setUpFenPosition("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R");
        moveFactory = new MoveFactory(board);

    }

    @Test
    public void testCreatesSimpleMove(){
        Move move = moveFactory.createMove("c2","c3");

        assertEquals("c2", move.source);
        assertEquals("c3", move.destination);
    }

    //To be covered:
    //Nd4
    //Nxd4
    //Nxd4+
    //Nxd4#
    //cxd4
    //O-O
    //O-O-O
    //d8=Q
    //d8=Q+
    //d8=Q#

}
