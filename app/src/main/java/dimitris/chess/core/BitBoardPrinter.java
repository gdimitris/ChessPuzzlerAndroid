package dimitris.chess.core;

class BitBoardPrinter {

	public static String print(Long number){
		int zeros = Long.numberOfLeadingZeros(number);
		String str = "";
		
		for(int i=0;i<zeros;i++)
			str+="0";
		return applyNewLines(str+Long.toBinaryString(number));
	}

	public static String print(UInt64 number){
        return applyNewLines(number.toString());
    }

	private static String applyNewLines(String str){
		String toReturn = "";
		for(int i =0; i<8 ;i++){
			String rotated = new StringBuffer(str.substring(i*8, (i*8) + 8 )).reverse().toString();
			toReturn += rotated + "\n";
		}
		return toReturn;
	}
	
}
