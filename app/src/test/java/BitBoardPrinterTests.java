import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dimitris.dimitris.chess.bitboards.BitBoardPrinter;

import static dimitris.dimitris.chess.bitboards.BitboardConstants.A_FILE;
import static dimitris.dimitris.chess.bitboards.BitboardConstants.F_FILE;
import static dimitris.dimitris.chess.bitboards.BitboardConstants.RANK_1;
import static dimitris.dimitris.chess.bitboards.BitboardConstants.RANK_5;
import static junit.framework.Assert.assertEquals;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class BitBoardPrinterTests {

    @Before
    public void setUp() {

    }

    @Test
    public void testPrintsFFile() {
        String expected = "00000100\n" +
                "00000100\n" +
                "00000100\n" +
                "00000100\n" +
                "00000100\n" +
                "00000100\n" +
                "00000100\n" +
                "00000100\n";
        String actual = BitBoardPrinter.print(F_FILE);
        assertEquals(expected, actual);

    }

    @Test
    public void testPrintsAFile() {
        String expected = "10000000\n"
                        + "10000000\n"
                        + "10000000\n"
                        + "10000000\n"
                        + "10000000\n"
                        + "10000000\n"
                        + "10000000\n"
                        + "10000000\n";

        String actual = BitBoardPrinter.print(A_FILE);
        assertEquals(expected, actual);
    }

    @Test
    public void testPrintsFirstRank(){
        String expected = "00000000\n"
                        + "00000000\n"
                        + "00000000\n"
                        + "00000000\n"
                        + "00000000\n"
                        + "00000000\n"
                        + "00000000\n"
                        + "11111111\n";

        String actual = BitBoardPrinter.print(RANK_1);
        assertEquals(expected, actual);
    }

    @Test
    public void testPringsFifthRank(){
        String expected = "00000000\n"
                        + "00000000\n"
                        + "00000000\n"
                        + "11111111\n"
                        + "00000000\n"
                        + "00000000\n"
                        + "00000000\n"
                        + "00000000\n";

        String actual = BitBoardPrinter.print(RANK_5);
        assertEquals(expected, actual);
    }
}
