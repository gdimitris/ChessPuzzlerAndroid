package dimitris.android.chessviews;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import dimitris.android.chessviews.Pieces.BlackPieceFactory;
import dimitris.android.chessviews.Pieces.Piece;
import dimitris.android.chessviews.Pieces.WhitePieceFactory;

/**
 * Created by dimitris on 10/02/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class FenParserTests {

    private FenParser parser;
    private WhitePieceFactory whitePieceFactory;
    private BlackPieceFactory blackPieceFactory;
    private static int TEST_SIZE = 12;

    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Before
    public void setup(){
        Context mockContext = RuntimeEnvironment.application.getApplicationContext();
        Typeface typeface = FontLoader.loadDefaultFont(mockContext);
        whitePieceFactory = new WhitePieceFactory(typeface,TEST_SIZE);
        blackPieceFactory = new BlackPieceFactory(typeface,TEST_SIZE);
        parser = new FenParser(whitePieceFactory,blackPieceFactory,TEST_SIZE);
    }

    @Test
    public void assertCorrectNumberOfPieces(){
        List<Piece> res = parser.parse("4kb1r/p2n1ppp/4q3/4p1B1/4P3/1Q6/PPP2PPP/2KR4 w k - 1 0");
        Assert.assertEquals(21,res.size());

        res = parser.parse("r1b2k1r/ppp1bppp/8/1B1Q4/5q2/2P5/PPP2PPP/R3R1K1 w - - 1 0");
        Assert.assertEquals(24,res.size());

        res = parser.parse("5rkr/pp2Rp2/1b1p1Pb1/3P2Q1/2n3P1/2p5/P4P2/4R1K1 w - - 1 0");
        Assert.assertEquals(20, res.size());

        res = parser.parse("1r1kr3/Nbppn1pp/1b6/8/6Q1/3B1P2/Pq3P1P/3RR1K1 w - - 1 0");
        Assert.assertEquals(21, res.size());

        res = parser.parse("5rk1/1p1q2bp/p2pN1p1/2pP2Bn/2P3P1/1P6/P4QKP/5R2 w - - 1 0");
        Assert.assertEquals(22, res.size());
    }

    @Test
    public void testThrowsExceptionAtBadFen(){
        exception.expect(RuntimeException.class);
        parser.parse("5rk1/1p1q2bp/p2pN1p1/2dfapP2Bn/2P3P1/asdf");
    }

}
