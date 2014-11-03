
/**
 * Created by Davis Treybig on 7/16/14.
 */
public class Bits {
    public Bits(){

    }

    /**
     * You are given two 32-bit numbers, N and M, and two bit positions, i and j Write a
     method to set all bits between i and j in N equal to M (e g , M becomes a substring of
     N located at i and starting at j)
     EXAMPLE:
     Input: N = 10000000000, M = 10101, i = 2, j = 6
     Output: N = 10001010100
     */

    public Integer replaceBitSubstring(int N, int M, int i, int j){
    	
    	//1s up to i
    	int onesBefore = (1 << i) -1;
    	//1s after j
    	int onesAfter = ~0 - ((1 << j)-1);
    	//make a mask with 0
    	int mask = onesBefore + onesAfter;
    	N = N & mask;
    	M = M << i;
    	return (N | M);			
    }

}
