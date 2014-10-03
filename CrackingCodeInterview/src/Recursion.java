


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Davis Treybig on 7/16/14.
 */
public class Recursion {
    public Recursion(){
    }

    /**
     * Generate nth fibonacci number (where n>=1)
     */
    public Integer nthFibonacci(int n){
        if(n<0){
            return null;
        }
        if(n==0){
        	return 0;
        }
        if(n==1 || n==2){
            return 1;
        }
        else{
            return nthFibonacci(n - 1)+nthFibonacci(n-2);
        }
    }
    
    /**
     * Returns all permutations of a string
     */
    public Set<String> getPermutations(String prefix, String s){
        Set<String> stringSet = new HashSet<String>();
        if(s.equals("")){
            stringSet.add(prefix);
            return stringSet;
        }
        for(int i=0; i<s.length(); i++){
            Set<String> subSets;
            if(i==0){
                subSets = getPermutations(prefix+s.charAt(i), s.substring(1, s.length()));
            }
            else if(i==s.length()-1){
                subSets = getPermutations(prefix+s.charAt(i), s.substring(0, i));
            }
            else{
                subSets = getPermutations(prefix+s.charAt(i), s.substring(0, i)+s.substring(i+1, s.length()));
            }
            stringSet.addAll(subSets);
        }
        return stringSet;
    }

    /**
     * Returns all subsets of a set (Assumes that the full set is NOT a subset of itself)
     */
    public Set<Set<String>> getSubsets(Set<String> set){
        Set<Set<String>> setsOfSets = new HashSet<Set<String>>();
        if(set.isEmpty()){
            return null;
        }
        if(set.size()==1){
            setsOfSets.add(set);
            return setsOfSets;
        }

        for(int i=1; i<set.size(); i++){
            setsOfSets.addAll(getSubsetsOfSize(new HashSet<String>(), set, i));
        }
        return setsOfSets;
    }    
    /**
     * Gets subsets of the set with the given size
     */
    private Set<Set<String>> getSubsetsOfSize(Set<String> prefixSet, Set<String> set, int size){
        if(prefixSet.size()==0){
            if(size<=0){
                return null;
            }
            if(size>set.size()){
                return null;
            }
        }
        Set<Set<String>> subsets = new HashSet<Set<String>>();
        if(prefixSet.size()==size && size!=0){
            subsets.add(prefixSet);
            return subsets;
        }
        for(String o : set){
            Set<String> newPrefix = new HashSet<String>(prefixSet);
            newPrefix.add(o);
            Set<String> newSet = new HashSet<String>(set);
            newSet.remove(o);
            subsets.addAll(getSubsetsOfSize(newPrefix, newSet, size));
        }
        return subsets;
    }
    
    /**
     * Returns true if you can place the given number of queens on a chess board without
     * any two being on the same column, row, or diagonal. Prints out a potential placement
     */
    public boolean printQueens(int numberQueens){
        Boolean[][] chessGrid = new Boolean[8][8];
        for(int i=0; i<chessGrid.length; i++){
            for(int j=0; j<chessGrid.length; j++){
                chessGrid[i][j] = false;
            }
        }
        if(printQueens(numberQueens, chessGrid)){
            return true;
        }
        return false;
    }
    private Boolean printQueens(int numberQueens, Boolean[][] grid){
        if(numberQueens==0){
            StringBuilder builder = new StringBuilder();
            for(int i=0; i<grid.length; i++){
                for(int j=0; j<grid.length; j++){
                    if(grid[i][j]){
                        builder.append("Q");
                    }
                    else{
                        builder.append("X");
                    }
                }
                builder.append("\n");
            }
            System.out.println(builder.toString());
            return true;
        }
        Boolean result = false;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid.length; j++){
                if(safeToPlace(i,j, grid)){
                    grid[i][j] = true;
                    if(printQueens(numberQueens-1, grid)){
                        result = true;
                        return result;
                    }
                    grid[i][j]=false;
                }
            }
        }
        return result;
    }
    private boolean safeToPlace(int x, int y, Boolean[][] grid){
        for(int i=0; i<grid.length; i++){
            if(grid[i][y] == true){
                return false;
            }
        }
        for(int j=0; j<grid.length; j++){
            if(grid[x][j] == true){
                return false;
            }
        }
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid.length; j++){
                if(grid[i][j]==true && Math.abs(i-x)==Math.abs(j-y)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Print all valid possible combinations of n pairs of parenthesis
     * and return the Set of strings portraying those parenthesis
     */
    public Set<String> printParenthesis(int numberOfParenthesis){
    	return printParenthesis("", numberOfParenthesis);
    }
    private Set<String> printParenthesis(String prefix, int number){
    	Set<String> stringSet = new HashSet<String>();

    	if(number == 0){
    		stringSet.add(prefix);
    		System.out.println(prefix);
    		return stringSet;
    	}
    	for(int i=0; i<prefix.length(); i++){
    		if(prefix.charAt(i) == '('){
    			if(i==0){
    				stringSet.addAll(printParenthesis("(()"+prefix.substring(1), number-1));
    			}
    			else {
    				stringSet.addAll(printParenthesis(prefix.substring(0, i)+"(()"+prefix.substring(i+1, prefix.length()), number-1));
    			}
    		}
    		else if(prefix.charAt(i) == ')'){
    			if(i==prefix.length()){
    				stringSet.addAll(printParenthesis(prefix+"()", number-1));
    			}
    			else{
    				stringSet.addAll(printParenthesis(prefix.substring(0, i)+")()"+prefix.substring(i+1, prefix.length()), number-1));
    			}
    		}
    	}
    	if(prefix.equals("")){
    		stringSet.addAll(printParenthesis("()", number-1));
    	}
    	return stringSet;
    }
    
    
    public static final int COLOR_RED = 1;
    public static final int COLOR_BLUE = 2;
    public static final int COLOR_GREEN = 3;
    
    /**
     * Fills a grid of color with a new color starting at the given
     * point. Fills until a border of the specified color is reached
     */
    public Integer[][] paintFill(int x, int y, int color, Integer[][] grid){
    	if(grid[x][y] == color){
    		return grid;
    	}
    	else{
    		grid[x][y] = color;
    		if(x<grid.length-1){
    			paintFill(x+1, y, color, grid);
    		}
    		if(x>0){
    			paintFill(x-1, y, color, grid);
    		}
    		if(y>0){
    			paintFill(x, y-1, color, grid);
    		}
    		if(y<grid[x].length - 1){
    			paintFill(x, y+1, color, grid);
    		}
    	}
    	return grid;
    }
    /**
     * Returns the number of unique ways to create n cents via only
     * quarters, dimes, nickels, and pennies
     */
    public int waysToCreateAmount(int cents){
    	List<Sum> sumList = new ArrayList<Sum>();
    	return waysToCreateAmount(cents, sumList, new Sum(0,0,0,0));	
    }
    private int waysToCreateAmount(int cents, List<Sum> sumList, Sum sum){
    	int cumSum = 0;
    	if(cents==0){
    		if(!sumList.contains(sum)){
    			sumList.add(sum);
    			return 1;
    		}
    		return 0;
    	}
    	if(cents>=1){
    		Sum newSum = new Sum(sum);
    		newSum.penny++;
    		cumSum = cumSum + waysToCreateAmount(cents-1, sumList, newSum);
    	}
    	if(cents>=5){
    		Sum newSum = new Sum(sum);
    		newSum.nickel++;
    		cumSum = cumSum + waysToCreateAmount(cents-5, sumList, newSum);
    	}
    	if(cents >= 10){
    		Sum newSum = new Sum(sum);
    		newSum.dime++;
    		cumSum = cumSum + waysToCreateAmount(cents-10, sumList, newSum);
    	}
    	if(cents >= 25){
    		Sum newSum = new Sum(sum);
    		newSum.quarter++;
    		cumSum = cumSum + waysToCreateAmount(cents-25, sumList, newSum);
    	}
    	return cumSum;  
    }
    private class Sum{
    	public int quarter;
    	public int dime;
    	public int nickel;
    	public int penny;
    	public Sum(Sum sum){
    		this.quarter = sum.quarter;
    		this.dime = sum.dime;
    		this.nickel = sum.nickel;
    		this.penny = sum.penny;
    	}
    	public Sum(int quarter, int dime, int nickel, int penny){
    		this.quarter = quarter;
    		this.dime = dime;
    		this.nickel = nickel;
    		this.penny = penny;
    	}
    	@Override
    	public boolean equals(Object o){
    		if(!(o instanceof Sum)){
    			return false;
    		}
    		Sum comparing = (Sum) o;
    		if(comparing.quarter == this.quarter && comparing.dime == this.dime 
    				&& comparing.nickel == this.nickel && comparing.penny == this.penny){
    			return true;
    		}
    		return false;
    	}
    }
   

}
