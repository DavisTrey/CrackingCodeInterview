package GeeksForGeeksProblems;

import java.util.Arrays;

public class Recursion {
	/**
	 * Given two integers, k and n, print out all increasing sequences of length k
	 * such that the elements in every sequence are from the first n natural numbers
	 */
	public void printSequences(int k, int n){
		printSequenceRecursive(k, n, 0, new int[k]);	
	}
	
	/**
	 * Recursive helper function for printSequences
	 */
	private void printSequenceRecursive(int k, int n, int position, int[] array){
		if(position == array.length){
			System.out.println(Arrays.toString(array));
			return;
		}
		
		int startingNumber;
		if(position == 0){
			startingNumber = 1;
		}
		else{
			startingNumber = array[position - 1] +1;
		}
		
		for(int i = startingNumber; i<=n; i++){
			array[position] = i;
			printSequenceRecursive(k, n, position+1, array);
		}
	}
	
	/**
	 * Given an array of characters n and an integer k, print all possible strings of length
	 * k that can be formed from the set 
	 */
	private void printStringsFromSet(char[] n, int k){
		printStringsFromSetHelper(n, k, new StringBuilder());
	}
	
	private void printStringsFromSetHelper(char[] n, int k, StringBuilder s){
		if(s.length() == k){
			System.out.println(s.toString());
			return;
		}
		for(int i=0; i<n.length; i++){
			StringBuilder builder = new StringBuilder(s);
			builder.append(n[i]);
			printStringsFromSetHelper(n, k, builder);
		}
	}
	
	
	
	public static void main(String[] args){
		Recursion recursion = new Recursion();
		recursion.printSequences(3, 5);
		
		char[] characters = {'a', 'b', 'c'};
		recursion.printStringsFromSet(characters, 2);
	}
}
