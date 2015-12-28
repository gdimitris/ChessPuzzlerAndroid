package dimitris.dimitris.chess.bitboards;

import java.util.Arrays;
import java.util.BitSet;

/**
 * Created by enviouscreep on 12/27/15.
 */
public class UInt64 {

    private BitSet bitSet;

    public UInt64(){
        bitSet = new BitSet(64);
        bitSet.set(0,63,false);
    }

    public UInt64(String number){
        bitSet = new BitSet(64);
        bitSet.set(0,63,false);
        number = insertLeadingZerosIfNecessary(number);
        initializeBitSet(number);
    }

    private String insertLeadingZerosIfNecessary(String number) {
        String result = number;
        if (number.length()<64){
            int diff = 64 - number.length();
            char[] chars = new char[diff];
            Arrays.fill(chars,'0');
            result = new String(chars) + result;
        }

        return result;
    }

    private void initializeBitSet(String number) {
        for (int index = number.indexOf('1'); index >= 0 ; index = number.indexOf('1', index + 1)){
            bitSet.set(index);
        }
    }

    public static UInt64 create(String number){
        return new UInt64(number);
    }

    public UInt64 and(UInt64 rhs){
        UInt64 clone = (UInt64) this.clone();
        clone.bitSet.and(rhs.bitSet);

        return clone;
    }

    public UInt64 or(UInt64 rhs){
        UInt64 clone = (UInt64) this.clone();
        clone.bitSet.or(rhs.bitSet);

        return clone;
    }

    public UInt64 xor(UInt64 rhs){
        UInt64 clone = (UInt64) this.clone();
        clone.bitSet.xor(rhs.bitSet);

        return clone;
    }

    public UInt64 flip(){
        UInt64 clone = (UInt64) this.clone();
        clone.bitSet.flip(0,bitSet.size());

        return clone;
    }

    public int cardinality(){
        return bitSet.cardinality();
    }

    @Override
    public String toString() {
        String result = "";

        for(int i = 0 ; i<bitSet.size();i++){
            if (bitSet.get(i)){
               result+="1";
            } else {
               result+="0";
            }
        }
        return result;
    }

    @Override
    protected Object clone() {
        UInt64 clone = new UInt64();
        clone.bitSet = (BitSet) this.bitSet.clone();
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        UInt64 other = (UInt64)o;
        return this.bitSet.equals(other.bitSet);
    }
}
