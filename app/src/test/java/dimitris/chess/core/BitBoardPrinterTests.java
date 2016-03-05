package dimitris.chess.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BitBoardPrinterTests {

    private static final String A_FILE = "100000001000000010000000100000001000000010000000100000001";

    @Before
    public void setUp() {

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

        String actual = BitBoardPrinter.print(UInt64.create(A_FILE));
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

        String actual = BitBoardPrinter.print((long) 0b11111111);
        assertEquals(expected, actual);
    }

    @Test
    public void testPrintsUInt64(){
        UInt64 num = UInt64.create("101100000010100000010001110011110010001");
        String expected = "00000000\n"
                        + "00000000\n"
                        + "00000000\n"
                        + "00011010\n"
                        + "00101000\n"
                        + "00010000\n"
                        + "11100111\n"
                        + "10001001\n";

        String actual = BitBoardPrinter.print(num);
        assertEquals(expected, actual);
    }
}
