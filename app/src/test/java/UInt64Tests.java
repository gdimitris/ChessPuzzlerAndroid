import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import dimitris.chess.bitboards.UInt64;

import static org.junit.Assert.assertEquals;

/**
 * Created by enviouscreep on 12/27/15.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class UInt64Tests {

    @Test
    public void testCreatesValidBitSets(){

        String nums[] = {
                "0000000000000000000000000101100000010100000010001110011110010001",
                "1011100111110011000010000000010000001000000000000000000000001000",
                "1011100111110011000010000101110000011100000010001110011110011001",
                "1111111111111111111111111111111111111111111111111111111111111111"
        };

        for(int i=0; i<nums.length; i++)
            assertEquals(nums[i], UInt64.create(nums[i]).toString());
    }

    @Test
    public void testCorrectLeadingZeros(){
        String num = "101100000010100000010001110011110010001";
        String expected = "0000000000000000000000000101100000010100000010001110011110010001";

        assertEquals(expected, UInt64.create(num).toString());
    }

    @Test
    public void testAndOperation(){
        UInt64 lhs_num = UInt64.create("101100000010100000010001110011110010001");
        UInt64 rhs_num = UInt64.create("11100011000010000000010000010000000010001110011100000000");
        UInt64 expected_int = UInt64.create("10000000010001110011100000000");

        assertEquals(expected_int,lhs_num.and(rhs_num));
    }

    @Test
    public void testOrOperation(){
        UInt64 lhs_num = UInt64.create("101100000010100000010001110011110010001");
        UInt64 rhs_num = UInt64.create("1011100111110011000010000000010000001000000000000000000000001000");
        UInt64 expected_int = UInt64.create("1011100111110011000010000101110000011100000010001110011110011001");


        assertEquals(expected_int, lhs_num.or(rhs_num));
    }

    @Test
    public void testXorOperation(){
        UInt64 lhs_num = UInt64.create("101100000010100000010001110011110010001");
        UInt64 rhs_num = UInt64.create("1011100111110011000010000000010000001000000000000000000000001000");
        UInt64 expected_int = UInt64.create("1011100111110011000010000101110000011100000010001110011110011001");

        assertEquals(expected_int, lhs_num.xor(rhs_num));
    }

    @Test
    public void testFlipOperation(){
        UInt64 original_num = UInt64.create("1111111111111111111111111111111111111111111111110000000011111111");
        UInt64 expected = UInt64.create("1111111100000000");

        assertEquals(expected,original_num.flip());
    }

}
