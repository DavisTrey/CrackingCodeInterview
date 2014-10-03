
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Davis Treybig on 7/16/14.
 */
public class LinkedLists {

    public LinkedLists(){

    }

    /**
     * Removes all duplicate values within a LinkedList.
     * IE: 1->2->1->3->2 becomes 3
     * @param head Head of LinkedList
     */
    public LinkedNode removeDuplicates(LinkedNode head){
        if(head==null){
            return null;
        }
        if(head.getNext()==null){
            return head;
        }

        Set<Integer> existingValues = new HashSet<Integer>();
        List<Integer> nonDuplicateValues = new ArrayList<Integer>();

        LinkedNode currentNode = head;
        nonDuplicateValues.add(currentNode.value);
        existingValues.add(currentNode.value);
        while(currentNode.getNext()!=null){
            currentNode = currentNode.getNext();
            if(existingValues.contains(currentNode.getValue())){
                for(int i=0; i<nonDuplicateValues.size(); i++){
                    if(nonDuplicateValues.get(i) == currentNode.getValue()){
                        nonDuplicateValues.remove(i);
                        i=i-1;
                    }
                }
            }
            else{
                //Not already added
                existingValues.add(currentNode.getValue());
                nonDuplicateValues.add(currentNode.getValue());
            }
        }
        if(nonDuplicateValues.size()==0){
            return null;
        }
        LinkedNode start = new LinkedNode(nonDuplicateValues.get(0));
        LinkedNode previous;
        LinkedNode current = start;
        for(int i=1; i<nonDuplicateValues.size(); i++){
            previous = current;
            current = new LinkedNode(nonDuplicateValues.get(i));
            previous.nextNode = current;
        }

        return start;

    }
    /**
     * Removes nth to last element in a LinkedList. Assumes that the last element would have index
     * 0, second to last index 1, etc.
     */
    public LinkedNode nthToLastElement(LinkedNode head, int elementToRemove){
        //Can't remove negative element
        if(elementToRemove<0){
            return head;
        }

        LinkedNode current = head;
        int size = 1;

        while(current.getNext()!=null){
            current=current.getNext();
            size++;
        }
        //Can't remove an element equal to or greater than the size of linked list
        if(elementToRemove>=size){
            return head;
        }

        current = head;
        for(int i=0; i<size-2-elementToRemove; i++){
            current=current.getNext();
        }
        if(head.getNext().getNext()!=null){
            current.nextNode = current.getNext().getNext();
        }
        else{
            current.nextNode = null;
        }
        return head;
    }
    /**
     * Implement an algorithm to delete a node in the middle of a single linked list, given
     only access to that node. Will return null if the given element is the last element
     EXAMPLE
     Input: the node ‘c’ from the linked list a->b->c->d->e
     Result: nothing is returned, but the new linked list looks like a->b->d->e
     */
    public void deleteMiddleNode(LinkedNode node){
        if(node==null || node.nextNode==null){
            return;
        }

        LinkedNode current = node;
        while(current.nextNode !=null){
            current.value = current.nextNode.value;
            if(current.nextNode.nextNode==null){
                current.nextNode=null;
            }
            else{
                current=current.nextNode;
            }
        }
    }

    /**
     * Sums the two linked lists, assuming each is a list of single digits forming a number
     */
    public LinkedNode sumLinkedLists(LinkedNode head1, LinkedNode head2){
        return sumLinkedLists(head1, head2, 0);
    }

    private LinkedNode sumLinkedLists(LinkedNode head1, LinkedNode head2, int carry){
        if(head1==null && head2==null){
            if(carry==0){
                return null;
            }
            if(carry==1){
                return new LinkedNode(1);
            }
        }
        if(head1==null){
            head2.value=head2.value+carry;
            if(head2.value>9){
                head2.value=head2.value-10;
                carry=1;
                head2.nextNode=sumLinkedLists(new LinkedNode(0), head2.nextNode, carry);
                return head2;
            }
            else{
                return head2;
            }
        }
        if(head2==null){
            head1.value = head1.value + carry;
            if(head1.value>9){
                head1.value = head1.value-10;
                carry=1;
                head1.nextNode = sumLinkedLists(head1.nextNode, new LinkedNode(0), carry);
                return head1;
            }
            else{
                return head1;
            }
        }

        //neither is null
        int sum = head1.value+head2.value+carry;
        carry = 0;
        if(sum>10){
            sum=10-sum;
            carry = 1;
        }
        LinkedNode result = new LinkedNode(sum);
        result.nextNode = sumLinkedLists(head1.nextNode, head2.nextNode, carry);
        return result;
    }


    /**
     * Given a circular LinkedList, return the node at the beginning of the loop
     */
    public LinkedNode getCircularNode(LinkedNode node){
        if(node==null){
            return null;
        }
        List<LinkedNode> passedNodes = new ArrayList<LinkedNode>();
        passedNodes.add(node);
        while(node.getNext()!=null){
            node=node.getNext();
            for(int i=0; i<passedNodes.size(); i++){
                //Use == to compare memory location
                if(passedNodes.get(i) == node){
                    return node;
                }
            }
            passedNodes.add(node);
        }
        return null;
    }

    

}

