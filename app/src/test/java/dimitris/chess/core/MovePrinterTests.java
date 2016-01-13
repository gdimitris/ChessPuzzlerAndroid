package dimitris.chess.core;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimitris on 1/13/16.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MovePrinterTests {

    private Bitboard board;
    private MoveFactory moveFactory;
    private List<Move> moveList;

    @Before
    public void setUp(){
        board = new Bitboard();
        board.setUpFenPosition("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R");

        moveFactory = new MoveFactory(board);
        moveList = new ArrayList<>();
    }

    @Test
    public void testPrintsMoveListWithFirstWhiteMove(){
        Move move1 = moveFactory.createMove("c2","c3");
        Move move2 = moveFactory.createMove("d4","c6");
        Move move3 = moveFactory.createMove("e5","g4");
        moveList.add(move1);
        moveList.add(move2);
        moveList.add(move3);

        String expected = "1. c3 Nc6 2. Ng4";
        assertMoveList(expected);
    }

    @Test
    public void testPrintsMoveListWithFirstBlackMove(){
        Move move1 = moveFactory.createMove("b7","b6");
        Move move2 = moveFactory.createMove("g5","h4");
        Move move3 = moveFactory.createMove("d8","c7");
        moveList.add(move1);
        moveList.add(move2);
        moveList.add(move3);


        String expected = "1... b6 2. Bh4 Qc7";
        assertMoveList(expected);

    }

    private void assertMoveList(String expected) {
        String actual = MovePrinter.printMoveList(moveList);

        Assert.assertEquals(expected, actual);
    }

}
