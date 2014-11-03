package GeeksForGeeksProblems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class GreedyAlgorithmQuestions {

	/**
	 * Given n ropes of different lengths, connect the ropes so that the total cost is
	 * minimized. Assume the cost of connecting any two ropes is the sum of their
	 * length. Returns the cost;
	 */
	public int connectRopes(int[] ropePieces){
		List<Integer> unsortedList = new ArrayList<Integer>();
		for(int i=0; i<ropePieces.length; i++){
			unsortedList.add(ropePieces[i]);
		}
		//Sort lengths is ascending order
		Collections.sort(unsortedList);
		int cost = 0;
		while(unsortedList.size() > 1){
			int combinedRopes = unsortedList.remove(0) + unsortedList.remove(0);
			cost = cost + combinedRopes;
			unsortedList.add(combinedRopes);
			Collections.sort(unsortedList);
		}
		
		return cost;
	}
	
	/**
	 * Given a string and a positive integer d, rearrange a string so that
	 * the same characters are d distance away. If no possible rearrangement
	 * can be found, returns null
	 */
	public String rearrangeString(int d, String s){
		Map<Character, Integer> letterMap = new HashMap<Character, Integer>();
		for(int i=0; i<s.length(); i++){
			if(letterMap.containsKey(s.charAt(i))){
				letterMap.put(s.charAt(i), letterMap.get(s.charAt(i))+1);
			}
			else{
				letterMap.put(s.charAt(i), 1);
			}
		}
		
		List<letterAndFrequency> frequencyList = new ArrayList<letterAndFrequency>();
		for(Character c : letterMap.keySet()){
			frequencyList.add(new letterAndFrequency(c, letterMap.get(c)));
		}
		Collections.sort(frequencyList);
		
		
		char[] arrayOutput = new char[s.length()];
		int overallPosition = 0;
		for(int i=frequencyList.size()-1; i>=0; i--){
			int k=overallPosition;
			for(int j=0; j<frequencyList.get(i).frequency; j++){
				arrayOutput[k] = frequencyList.get(i).c;
				if(k >= s.length()){
					return null;
				}
				k = k + d;			
			}
			overallPosition++;
		}
	
		String stringOutput = String.copyValueOf(arrayOutput);
		return stringOutput;
	}
	
	/**
	 * Helper class for rearrange string method
	 */
	private class letterAndFrequency implements Comparable<letterAndFrequency>{
		private Character c;
		private int frequency;
		public letterAndFrequency(Character c, int frequency){
			this.c = c;
			this.frequency = frequency;
		}
		@Override
		public int compareTo(letterAndFrequency o) {
			// TODO Auto-generated method stub
			if(this.frequency < o.frequency){
				return -1;
			}
			else if(this.frequency == o.frequency){
				return 1;
			}
			return 0;
		}
	}
	
	
	public static void main(String[] args){
		GreedyAlgorithmQuestions a = new GreedyAlgorithmQuestions();
		int[] ropePieces = {4, 3, 2, 6};
		System.out.println(a.connectRopes(ropePieces));
		
		System.out.println(a.rearrangeString(2, "abb"));
		System.out.println(a.rearrangeString(3, "aacbbc"));
	}
	
}
