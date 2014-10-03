
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Davis Treybig on 7/16/14.
 */
public class Strings {
    public Strings(){

    }
    /**
     * Returns true if the string has no repeat characters
     */
    public boolean allUniqueCharacters(String s){
        for(int i=0; i<s.length(); i++){
            for(int j=0; j<s.length(); j++){
                if(i!=j){
                    if(s.charAt(i)==s.charAt(j)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Reverses a C style string
     */
    public char[] reverseString(char[] chars){
        int head = 0;
        int tail = chars.length - 3;
        while(head<tail){
            char temp = chars[head];
            chars [head] = chars[tail];
            chars[tail] = temp;
            head++;
            tail--;
        }
        return chars;
    }

    /**
     * Removes all duplicate characters from a string
     */
    public String removeDuplicates(String s){
        String newString="";
        boolean isDuplicate=false;
        for(int i=0; i<s.length(); i++){
            for(int j=0; j<s.length();j++){
                if(i!=j){
                    //duplicate character
                    if(Character.toLowerCase(s.charAt(i))==Character.toLowerCase(s.charAt(j))){
                        isDuplicate = true;
                    }

                }
            }
            if(!isDuplicate){
                newString=newString+s.charAt(i);
            }
            isDuplicate = false;
        }
        return newString;
    }

    public boolean areAnagrams(String s1, String s2){
        Map<Character, Integer> characterIntegerMap = new HashMap<Character, Integer>();
        for(int i=0; i<s1.length(); i++){
            if(characterIntegerMap.containsKey(s1.charAt(i))){
                characterIntegerMap.put(s1.charAt(i), characterIntegerMap.get(s1.charAt(i))+1);
            }
            else{
                characterIntegerMap.put(s1.charAt((i)), 1);
            }
        }
        for(int j=0; j<s2.length(); j++){
            if(!characterIntegerMap.containsKey(s2.charAt(j))){
                return false;
            }
            if(characterIntegerMap.get(s2.charAt(j))==0){
                return false;
            }
            characterIntegerMap.put(s2.charAt(j), characterIntegerMap.get(s2.charAt(j))-1);
        }
        return true;
    }

    /**
     * Replaces all spaces in a string with %20
     */
    public String needlessReplacement(String s){
    	List<Character> list = new ArrayList<Character>();
        for(int i=0; i<s.length(); i++){
        	if(s.charAt(i) == ' '){
        		list.add('%');
        		list.add('2');
        		list.add('0');
        	}
        	else{
        		list.add(s.charAt(i)); 
        	}
        }
        char[] array = new char[list.size()];
        for(int i=0; i<list.size(); i++){
        	array[i] = list.get(i);
        }
        return String.valueOf(array);
    }

    /**
     * Rotates an NxN array of pixels by 90 degrees, clockwise
     * **Not working...
     */
    public Pixel[][] rotatePicture(Pixel[][] pixels){
        Set<Integer[]> coordinateSet = new HashSet<Integer[]>();
        for(int i=0; i<pixels.length; i++){
            for(int j=0; j<pixels.length; j++){
                Integer[] array = {i,j};
                Integer[] array2 = {j,i};
                if(!(coordinateSet.contains(array) || coordinateSet.contains(array2))){
                    if((i<pixels.length/2 && j<pixels.length/2) || (i>pixels.length/2 && j>pixels.length/2)){
                        Pixel comparingPixel = pixels[pixels.length-1-i][j];
                        Pixel currentPixel = pixels[i][j];
                        Pixel tempPixel = currentPixel;
                        currentPixel = comparingPixel;
                        comparingPixel = tempPixel;
                        coordinateSet.add(array);
                    }
                    if((i>pixels.length/2 && j<pixels.length/2) || (i<pixels.length/2 && j>pixels.length/2)){
                        Pixel comparingPixel = pixels[i][pixels.length-1-j];
                        Pixel currentPixel = pixels[i][j];
                        Pixel tempPixel = currentPixel;
                        currentPixel = comparingPixel;
                        comparingPixel = tempPixel;
                        coordinateSet.add(array);
                    }
                }
            }
        }
        return pixels;
    }

    /**
     * Helper class for pixel rotation problem
     */
    private class Pixel{
        private Byte[] myBytes;
        public Pixel(Byte[] bytes){
            myBytes = bytes;
        }
        public Byte[] getMyBytes(){
            return myBytes;
        }
    }

    /**
     * Finds all zeros in a matrix, and replace all values
     * in those rows and columns to be zeros as well
     */
    public int[][] findZerosInMatrix(int[][] matrix){
        Set<Integer[]> indexSet = new HashSet<Integer[]>();
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    for(int k=0; k<matrix.length; k++){
                        //replace row
                        Integer[] array = {k,j};
                        indexSet.add(new Integer[]{k,j});
                    }
                    for(int k=0; k<matrix[0].length; k++){
                        //replace column
                        Integer[] array = {i,k};
                        indexSet.add(new Integer[]{i,k});
                    }
                }
            }
        }
        for(Integer[] integers : indexSet){
            matrix[integers[0]][integers[1]] = 0;
        }
        return matrix;
    }

    /**
     * Returns true if 2 strings could have their characters rotated to be equivalent to each other
     * ie, "dog" ad "gdo" are rotations
     */
    public boolean isRotation(String s1, String s2){
        if(s1.length()!=s2.length()){
            return false;
        }
        s1 = s1+s1;
        for(int i=0; i<s1.length()/2; i++){
            if(s1.substring(i, s2.length()+i).equals(s2)){
                return true;
            }
        }
        return false;
    }
}
