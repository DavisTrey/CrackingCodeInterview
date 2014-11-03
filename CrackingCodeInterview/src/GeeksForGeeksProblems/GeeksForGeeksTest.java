package GeeksForGeeksProblems;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeeksForGeeksTest {
	
	@Test
	public void testReverseIntegerBits(){
		BitQuestions bitQuestions = new BitQuestions();
		int i = 1;
		int k = 0x80000000;
		assertTrue(k == bitQuestions.reverseBits(i));
		assertTrue(i == bitQuestions.reverseBits(k));
		
		int m = 0x00010001;
		int n = 0x80008000;
		assertTrue(n == bitQuestions.reverseBits(m));
		assertTrue(m == bitQuestions.reverseBits(n));
	}

}
