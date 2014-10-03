
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Davis Treybig on 7/16/14.
 */
public class Stacks {
    public Stacks(){

    }

    /**
     * Implementing a stack with pop, push, and min (all O(1))
     */
    private class MinimumStack{
        List<Integer> stackList = new ArrayList<Integer>();
        Integer minimum;
        //Sorted least minimum to most minumum.

        public MinimumStack(){

        }
        public int pop(){
            if(stackList.get(stackList.size()-1) == minimum) {
                int returnValue = stackList.remove(stackList.size() - 1);
                if(stackList.size()==0){
                    minimum = null;
                    return returnValue;
                }
                if (!stackList.contains(minimum)) {
                    //minimum removed. Must recalculate minimum.
                    minimum = stackList.get(0);
                    for(int i=0; i<stackList.size(); i++){
                        if(stackList.get(i)<min()){
                            minimum = stackList.get(i);
                        }
                    }
                }
                return returnValue;
            }
            else{
                return stackList.remove(stackList.size()-1);
            }
        }
        public void push(int i){
            stackList.add(i);
            if(stackList.size()==0){
                minimum=i;
            }
            else{
                if(i<minimum){
                    minimum = i;
                }
            }
        }
        public int min(){
            return minimum;
        }

    }

    /**
     * Implement a Queue using 2 stacks
     */
    private class stackQueue{
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();
        private stackQueue(){

        }

        /**
         * First in, First out
         */
        public Integer pop(){
            for(Integer i : stack1){
                stack2.add(stack1.pop());
            }
            Integer returnValue = stack2.pop();
            for(Integer i: stack2){
                stack1.add(stack2.pop());
            }
            return returnValue;
        }

        /**
         * Adds item
         */
        public void push(Integer i){
            stack1.add(i);
        }
    }

    /**
     * Implement 3 stacks with an array
     */
    private class threeStackArray{
        //Stack 1 grows from start up
        //Stack 2 grows from end down
        //Stack 3 grows from 1/3 length up
        Object[] array = new Object[Integer.MAX_VALUE-5];
        //Indices hold position of most recent value
        int stack2StartIndex = (Integer.MAX_VALUE-5)/3;
        int stack1Index;
        int stack2Index;
        int stack3Index;

        public threeStackArray(){

        }
        private void shiftStackTwoUp(){
            if(array[stack2StartIndex]==null){
                stack2StartIndex++;
                return;
            }
            else{
                Object current = array[stack2StartIndex];
                Object next = array[stack2StartIndex+1];
                Object temp = next;
                array[stack2StartIndex+1] = current;
                array[stack2StartIndex] = null;
                stack2StartIndex++;
                while(temp!=null){
                    current = next;
                    next = array[stack2StartIndex+1];
                    temp = next;
                    array[stack2StartIndex+1] = current;

                }


            }
        }
        public void stackOnePush(Object o){
            if(array[0]==null){
                array[0]=o;
                stack1Index = 0;
            }
            else{
                stack1Index++;
                if(array[stack1Index]!=null){
                    shiftStackTwoUp();
                }
                array[stack1Index]=o;
            }
        }
        public void stackTwoPush(Object o){

        }
        public void stackThreePush(Object o){

        }
        public Object stackOnePop(){
            Object o = array[stack1Index];
            array[stack1Index] = null;
            stack1Index--;
            return o;
        }
        public Object stackTwoPop(){
            Object o = array[stack2Index];
            array[stack2Index] = null;
            stack2Index--;
            return o;
        }
        public Object stackThreePop(){
            Object o = array[stack3Index];
            array[stack3Index] = null;
            stack3Index++;
            return o;
        }
    }
    /**
     * Sort a stack in ascending order, using only peep, pop, push, isEmpty
     */
    public Stack<Object> sortStack(Stack<Object> stack){
        return null;
    }
    public void testStuff(){

    }
}
