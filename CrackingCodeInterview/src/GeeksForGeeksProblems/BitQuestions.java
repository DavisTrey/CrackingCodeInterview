package GeeksForGeeksProblems;

public class BitQuestions {

	/**
	 * Reverse the bits in an integer
	 */
	public int reverseBits(int i){
		int lowerHalf = 0x0000FFFF & i;
		int upperHalf = 0xFFFF0000 & i;
		
		for(int j=0; j<32; j++){
			int isolator = 1 << j;
			int bitToMove;
			int amountToShift;
			if(j<16){
				bitToMove = isolator & lowerHalf;
				amountToShift = Math.abs(15-j) * 2 + 1;
			}
			else{
				bitToMove = isolator & upperHalf;
				amountToShift = Math.abs(j-16) * 2 + 1;
			}
			if(j<16){
				lowerHalf = lowerHalf | (bitToMove << amountToShift);
			}
			else{
				upperHalf = upperHalf | (bitToMove >>> amountToShift);
			}
		}
		
		upperHalf = upperHalf & (0x0000FFFF);
		lowerHalf = lowerHalf & (0xFFFF0000);
		
		return upperHalf | lowerHalf;
	}
	
	
}
