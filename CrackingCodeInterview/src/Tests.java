import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;


public class Tests {

	
	//add stack tests (some stuff is wrong). Some needs to be done. 
	//add bit stuff
	//add object oriented stuff
	//add 
	@Test 
	public void testStrings(){
		System.out.println(System.getProperty("java.library.path"));
		Strings strings = new Strings();
		String s1 = "Hi";
		String s2 = "Howdy";
		String s3 = "Lollapalooza";
		String s4 = "gg";
		assertTrue(strings.allUniqueCharacters(s1));
		assertTrue(strings.allUniqueCharacters(s2));
		assertFalse(strings.allUniqueCharacters(s3));
		assertFalse(strings.allUniqueCharacters(s4));
		
		char[] cStyle = {'h', 'i', 'd', 'e', '/', 'n'};
		char[] cStyleReverse = {'e', 'd', 'i', 'h', '/', 'n'};
		char[] reversed = strings.reverseString(cStyle);
		for(int i=0; i<cStyle.length; i++){	
			assertTrue(reversed[i] == cStyleReverse[i]);
		}
		
		s1 = strings.removeDuplicates(s3);
		assertTrue(s1.equals("pz"));
		
		String ana1 = "howy";
		String ana2 = "yowh";
		String ana3 = "abcd";
		String ana4 = "yoowh";
		assertTrue(strings.areAnagrams(ana1, ana2));
		assertFalse(strings.areAnagrams(ana2, ana3));
		assertFalse(strings.areAnagrams(ana2, ana4));
		
		String stringWithSpaces = "Hi mom and dad";
		String noSpaces = strings.needlessReplacement(stringWithSpaces);
		assertTrue(noSpaces.equals("Hi%20mom%20and%20dad"));
		
		int[][] matrix = new int[5][5];
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[0].length; j++){
				matrix[i][j] = 1;
			}
		}
		matrix[0][0] = 0;
		matrix[4][4] = 0;
		int[][] result = strings.findZerosInMatrix(matrix);
		for(int i=0; i<result.length; i++){
			assertTrue(result[0][i] == 0);
			assertTrue(result[4][i] == 0);
			assertTrue(result[i][0] == 0);
			assertTrue(result[i][4] == 0);
		}
		for(int i=1; i<result.length-1; i++){
			for(int j=1; j<result.length-1; j++){
				assertTrue(result[i][j] == 1);
			}
		}
		
		
		s1 = "howdy";
		s2 = "yhowd";
		s3 = "dyhow";
		s4 = "dyhoww";
		assertTrue(strings.isRotation(s1, s2));
		assertTrue(strings.isRotation(s2, s3));
		assertTrue(strings.isRotation(s1, s3));
		assertFalse(strings.isRotation(s3, s4));
		
	}
	@Test
	public void testLinkedLists(){
		LinkedNode node1 = new LinkedNode(1);
        LinkedNode node2 = new LinkedNode(2);
        LinkedNode node3 = new LinkedNode(3);
        node1.nextNode = node2;
        node2.nextNode = node3;

        LinkedNode node4 = new LinkedNode(1);
        LinkedNode node5 = new LinkedNode(2);
        LinkedNode node6 = new LinkedNode(3);
        LinkedNode node7 = new LinkedNode(4);
        node4.nextNode = node5;
        node5.nextNode = node6;
        node6.nextNode = node7;
        LinkedLists linkedLists = new LinkedLists();

        //Summing 123 + 1234
        LinkedNode node = linkedLists.sumLinkedLists(node1, node4);
        assertTrue(node.value == 2);
        assertTrue(node.nextNode.value == 4);
        assertTrue(node.nextNode.nextNode.value == 6);
        assertTrue(node.nextNode.nextNode.nextNode.value == 4);
        //Should be 4642 (so, in orde 2,4,6,4)
        
        node3.nextNode = node2;
        node = linkedLists.getCircularNode(node1);
        assertTrue(node.value == 2);
        //Should return node with value 2
        
        node1 = new LinkedNode(1);
        node2 = new LinkedNode(2);
        node3 = new LinkedNode(3);
        node4 = new LinkedNode(1);
        node5 = new LinkedNode(5);
        node6 = new LinkedNode(5);
        node1.nextNode = node2;
        node2.nextNode = node3;
        node3.nextNode = node4;
        node = linkedLists.removeDuplicates(node1);//should be 2-->3
        assertTrue(node.value == 2);
        assertTrue(node.nextNode.value == 3);
        
        node1 = new LinkedNode(1);
        node2 = new LinkedNode(2);
        node3 = new LinkedNode(3);
        node4 = new LinkedNode(4);
        node5 = new LinkedNode(5);
        node1.nextNode = node2;
        node2.nextNode = node3;
        node3.nextNode = node4;
        node4.nextNode = node5;
        linkedLists.deleteMiddleNode(node3);
        assertTrue(node2.nextNode.value == 4);
        assertTrue(node2.nextNode.nextNode.value == 5);
        
        node1 = new LinkedNode(1);
        node2 = new LinkedNode(2);
        node3 = new LinkedNode(3);
        node4 = new LinkedNode(4);
        node5 = new LinkedNode(5);
        node1.nextNode = node2;
        node2.nextNode = node3;
        node3.nextNode = node4;
        node4.nextNode = node5;
        linkedLists.nthToLastElement(node1, 3); //delete node 2 
        assertTrue(node1.nextNode.value == 3);     
	}
	
	
	
	@Test
	public void testRecursion() {
		Recursion recursion = new Recursion();
		assertTrue(recursion.nthFibonacci(0) == 0);
		assertTrue(recursion.nthFibonacci(1)==1);
		assertTrue(recursion.nthFibonacci(2)==1);
		assertTrue(recursion.nthFibonacci(3)==2);
		assertTrue(recursion.nthFibonacci(10)==55);
		
		Set<String> stringPermutations = recursion.getPermutations("", "Tes");
		assertTrue(stringPermutations.contains("Tes"));
		assertTrue(stringPermutations.contains("seT"));
		assertTrue(stringPermutations.contains("Tse"));
		assertTrue(stringPermutations.contains("sTe"));
		assertTrue(stringPermutations.contains("esT"));
		assertTrue(stringPermutations.contains("eTs"));
		
		 Set<String> set = new HashSet<String>();
	     set.add("hi");
	     set.add("bye");
	     set.add("lol");
	     Set<Set<String>> setSet = recursion.getSubsets(set); //6 sets. Hi, Bye, Lol, Hi-Bye, Bye-Lol, Hi-Lol
	     Set<String> set1 = new HashSet<String>();
	     set1.add("hi");
	     set1.add("bye");
	     Set<String> set2 = new HashSet<String>();
	     set2.add("hi");
	     set2.add("lol");
	     Set<String> set3 = new HashSet<String>();
	     set3.add("bye");
	     set3.add("lol");
	     Set<String> set4 = new HashSet<String>();
	     set4.add("hi");
	     Set<String> set5 = new HashSet<String>();
	     set5.add("bye");
	     Set<String> set6 = new HashSet<String>();
	     set6.add("lol");
	     assertTrue(setSet.contains(set1));
	     assertTrue(setSet.contains(set2));
	     assertTrue(setSet.contains(set3));
	     assertTrue(setSet.contains(set4));
	     assertTrue(setSet.contains(set5));
	     assertTrue(setSet.contains(set6));
	     
	     Set<String> parenthesis = recursion.printParenthesis(3);
	     assertTrue(parenthesis.contains("()()()"));
	     assertTrue(parenthesis.contains("((()))"));
	     assertTrue(parenthesis.contains("(()())"));
	     assertTrue(parenthesis.contains("()(())"));
	     assertTrue(parenthesis.contains("(())()"));
	     
	     
	     Integer[][] grid = new Integer[5][5];
	     
	     for(int i=0; i<5; i++){
	    	 for(int j=0; j<5; j++){
	    	     grid[i][j] = Recursion.COLOR_BLUE;
	    	 }
	     }
	     for(int i=0; i<5; i++){
	    	 grid[0][i] = Recursion.COLOR_RED;
	    	 grid[4][i] = Recursion.COLOR_RED;
	    	 grid[i][0] = Recursion.COLOR_RED;
	    	 grid[i][4] = Recursion.COLOR_RED;
	     }
	     recursion.paintFill(3, 2, Recursion.COLOR_RED, grid);
	     for(int i=0; i<5; i++){
	    	 for(int j=0; j<5; j++){
	    		 assertTrue(grid[i][j] == Recursion.COLOR_RED);
	    	 }
	     }
	     
	     assertTrue(recursion.waysToCreateAmount(1)==1);
	     assertTrue(recursion.waysToCreateAmount(5)==2);
	     assertTrue(recursion.waysToCreateAmount(10)==4);
	     
	     
	     assertTrue(recursion.printQueens(8));
	     //Commenting out because it takes forever to compute false
	     //assertFalse(recursion.printQueens(9));
	     
	     
	}
}
