
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

    public Integer replaceBitSubstring(int M, int N, int i, int j){
    	String s = "";
    	for(int k=0; k<32; k++){
    		if(k>i && k<j){
    			s = s+"0";
    		}
    		else{
    			s=s+"1";
    		}
    	} 	
    	//Replaces bits between i and j with 0s
    	N = N & Integer.parseInt(s, 2);
    	int shiftAmount = 32 - i;
    	
        for(int k=0; k<32; k++){
        	if(k>i && k<j){
        		s = s + 
        	}
        	else{
        		s = s+"1";
        	}
        }
    }

}
