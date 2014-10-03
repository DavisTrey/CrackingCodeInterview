
import java.util.*;

/**
 * Created by Davis Treybig on 7/16/14.
 */
public class Trees {
    private class TreeNode{
        public Integer value;
        public TreeNode right;
        public TreeNode left;
        public TreeNode parent;
        public TreeNode(Integer i){
            value = i;
        }
        public TreeNode(){}
    }
    private class GraphNode{
        public Integer value;
        public List<GraphNode> connectedNodes = new ArrayList<GraphNode>();
        public GraphNode(Integer i){
            value = i;
        }
    }

    public Trees(){

    }
    public boolean isBalanced(TreeNode head){
        if(head==null){
            return true;
        }
        if(Math.abs(findMaxDistanceToLeaf(head.left)-findMaxDistanceToLeaf(head.right))<=1){
            if(isBalanced(head.right) && isBalanced(head.left)){
                return true;
            }
        }
        return false;
    }

    private int findMaxDistanceToLeaf(TreeNode node){
        if(node==null){
            return 0;
        }
        if(node.left==null && node.right==null){
            return 1;
        }
        if(node.right==null){
            return 1+findMaxDistanceToLeaf(node.left);
        }
        if(node.left==null){
            return 1+findMaxDistanceToLeaf(node.right);
        }
        return Math.max(1+findMaxDistanceToLeaf(node.left), 1+findMaxDistanceToLeaf(node.right));
    }

    /**
     * Determines whether there is a connection between two nodes in a directional graph
     */
    public boolean isRouteBetweenNodes(GraphNode node1, GraphNode node2){
        if(node1==null || node2 == null){
            return false;
        }
        if(node1==node2){
            return true;
        }
        for(GraphNode node : node1.connectedNodes){
            boolean b = isRouteBetweenNodes(node, node2);
            if(b) return true;
        }
        for(GraphNode node : node2.connectedNodes){
            boolean b = isRouteBetweenNodes(node, node1);
            if(b) return true;
        }
        return false;
    }

    /**
     * Given a sorted (increasing order) array, write an algorithm to create a binary tree with
     minimal height
     */
    public TreeNode createBinaryTree(Integer[] array){
        if(array.length==0){
            return null;
        }
        if(array.length==1){
            return new TreeNode(array[0]);
        }
        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> graphStack = new ArrayDeque<TreeNode>();
        graphStack.add(root);

        for(int i=1; i<array.length; i=i+2){
            TreeNode current = graphStack.poll();
            TreeNode left = new TreeNode(array[i]);
            current.left = left;
            graphStack.add(left);
            if(array.length>i+1){
                TreeNode right = new TreeNode(array[i+1]);
                current.right = right;
                graphStack.add(right);
            }
        }
        return root;
    }

    /**
     * Given a binary search tree, create a LinkedList displaying the elements at each depth level
     * IE, tree with a depth of 5 will have 5 linked lists
     */
    public List<java.util.LinkedList<Integer>> createDepthLinkedLists(TreeNode node){
        if(node==null){
            return null;
        }
        List<java.util.LinkedList<Integer>> linkedListList = new ArrayList<java.util.LinkedList<Integer>>();
        Queue<TreeNode> nodeQueue = new ArrayDeque<TreeNode>();
        nodeQueue.add(node);
        while(!nodeQueue.isEmpty()){
            java.util.LinkedList<Integer> list = new java.util.LinkedList<Integer>();
            Queue<TreeNode> innerQueue = new ArrayDeque<TreeNode>();
            while(!nodeQueue.isEmpty()){
                innerQueue.add(nodeQueue.poll());
            }
            while(!innerQueue.isEmpty()){
                TreeNode currentNode = innerQueue.poll();
                list.add(currentNode.value);
                if(currentNode.left!=null){
                    nodeQueue.add(currentNode.left);
                }
                if(currentNode.right!=null){
                    nodeQueue.add(currentNode.right);
                }
            }
            linkedListList.add(list);
        }
        return linkedListList;
    }

    /**
     * Find the "next node" in a binary search tree, assuming nodes have links to their parents
     */
    public TreeNode findNextNode(TreeNode node){
        if(node.right==null){
            //if this is to right of parent, with no children, there is no next node
            if(node.parent==null || node.parent.right == node){
                return null;
            }
            //if this is to left of parent with no children, parent is next
            return node.parent;
        }
        else if(node.right != null){
            TreeNode current = node.right;
            while(current.left!=null){
                current = current.left;
            }
            return current;
        }
        //this is never reached
        return null;
    }

    /**
     * Finds the first common ancestor of two nodes in a binary tree
     */
    public TreeNode firstCommonAncestor(TreeNode node1, TreeNode node2){
        if(node1==null || node2==null){
            return null;
        }
        if(node1==node2){
            return node1;
        }
        return null;
    }

    /**
     * Determines if tree 2 is a subtree of tree 1 (assuming tree 1 has millions of nodes and
     * tree 2 has hundreds
     */
    public boolean isSubtree(TreeNode largeTree, TreeNode smallTree){
        if(largeTree == null || smallTree == null){
            return false;
        }
        //BFS on large tree
        Queue<TreeNode> BFSQueue = new ArrayDeque<TreeNode>();
        BFSQueue.add(largeTree);
        while(!BFSQueue.isEmpty()){
            TreeNode currentNode = BFSQueue.poll();
            if(currentNode.left !=null){
                BFSQueue.add(currentNode.left);
            }
            if(currentNode.right != null){
                BFSQueue.add(currentNode.right);
            }
            if(currentNode.value == smallTree.value){
                if(checkSubtree(currentNode, smallTree)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines if a tree is a subtree of another, assuming the subtree would start at the
     * root node
     */
    private boolean checkSubtree(TreeNode tree1, TreeNode subtree){
        if(tree1 == null && subtree == null){
            return true;
        }
        if(tree1 == null || subtree == null){
            return false;
        }
        Queue<TreeNode> largeTreeQueue = new ArrayDeque<TreeNode>();
        Queue<TreeNode> subtreeQueue = new ArrayDeque<TreeNode>();
        largeTreeQueue.add(tree1);
        subtreeQueue.add(subtree);
        while(!subtreeQueue.isEmpty()){
            TreeNode largeTreeCurrent = largeTreeQueue.poll();
            TreeNode smallTreeCurrent = subtreeQueue.poll();
            if(largeTreeCurrent.value != smallTreeCurrent.value){
                return false;
            }
            if(smallTreeCurrent.left !=null && largeTreeCurrent.left == null){
                return false;
            }
            if(smallTreeCurrent.right !=null && largeTreeCurrent.right == null){
                return false;
            }
            if(smallTreeCurrent.left !=null){
                largeTreeQueue.add(largeTreeCurrent.left);
                subtreeQueue.add(smallTreeCurrent.left);
            }
            if(smallTreeCurrent.right !=null){
                largeTreeQueue.add(largeTreeCurrent.right);
                subtreeQueue.add(smallTreeCurrent.right);
            }
        }
        return true;
    }

    /**
     * Returns true if the tree has a path which sums to specified value
     */
    public Boolean hasPathSum(TreeNode root, int sum){
        if(root.value==sum){
            return true;
        }
        if(root.value>sum){
           return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
        }
        if(root.value<sum){
           return hasPathSum(root.left, sum - root.value) || hasPathSum(root.right,
                   sum - root.value) || hasPathSum(root.left, sum) ||
               hasPathSum(root.right, sum);
        }
        return false;
    }


    /**
     * Gets a path in the tree which sums to the specified value. Returns null if none exist
     */
    public List<TreeNode> getPathSum(TreeNode root, int sum){
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        if(root.value==sum) {
            nodeList.add(root);
            return nodeList;
        }
        if(root.value>sum){
            if(root.left!=null){
                if(getPathSum(root.left, sum)!=null){
                    return getPathSum(root.left, sum);
                }
            }
            if(root.right!=null){
                if(getPathSum(root.right, sum)!=null){
                    return getPathSum(root.right, sum);
                }
            }
        }
        if(root.value<sum){
            if(root.left!=null){
                if(getPathSum(root.left, sum - root.value)!=null){
                    //Current value included, add lists
                    nodeList.add(root);
                    nodeList.addAll(getPathSum(root.left, sum - root.value));
                    return nodeList;
                }
                if(getPathSum(root.left, sum)!=null){
                    //Current value not included
                    return getPathSum(root.left, sum);
                }
            }
            if(root.right!=null){
                if(getPathSum(root.right, sum - root.value)!=null){
                    //Current value included, add lists
                    nodeList.add(root);
                    nodeList.addAll(getPathSum(root.right, sum - root.value));
                    return nodeList;
                }
                if(getPathSum(root.right, sum)!=null){
                    //current value not included
                    return getPathSum(root.right, sum);
                }
            }
        }
        return null;
    }


    public void testTrees(){
        //Test Balance
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.right = node2;
        node1.left = node3;
        Boolean b1 = isBalanced(node1); //true
        node2.right=node4;
        Boolean b2 = isBalanced(node1);//true
        node4.right = new TreeNode(5);
        Boolean b3 = isBalanced(node1); //false
           //1
        //3   2
        //     4
        //      5


        //Route
        GraphNode gnode1 = new GraphNode(1);
        GraphNode gnode2 = new GraphNode(2);
        GraphNode gnode3 = new GraphNode(3);
        GraphNode gnode4 = new GraphNode(4);
        GraphNode gnode5 = new GraphNode(5);
        gnode1.connectedNodes.add(gnode2);
        gnode2.connectedNodes.add(gnode3);
        gnode2.connectedNodes.add(gnode4);
        gnode3.connectedNodes.add(gnode5);
        Boolean b = isRouteBetweenNodes(gnode1, gnode5); //should be true
        GraphNode gnode6 = new GraphNode(6);
        boolean b4 = isRouteBetweenNodes(gnode1, gnode6); //should be false

        Integer[] array = {1,2,3,4,5,6,7,7};
        TreeNode root = createBinaryTree(array);
        //1
       //2  3
     //4 5 6 7
    //7

        List<java.util.LinkedList<Integer>> depthList = createDepthLinkedLists(node1);
        //First has 1, second has 3 then 2, third has 4, fourth has 5

        //Parent node stuff needs to be tested


        testSubtree();

        //Testing has path sum
        List<TreeNode> check1=getPathSum(root, 4);//true
        List<TreeNode> check2=getPathSum(root, 10);//true
        List<TreeNode> check3=getPathSum(root, 25); //false
        List<TreeNode> check4=getPathSum(root, 9); //true




    }

    private void testSubtree(){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        node5.right = node7;

        TreeNode sub1 = new TreeNode(2);
        TreeNode sub2 = new TreeNode(4);
        TreeNode sub3 = new TreeNode(5);
        sub1.left = sub2;
        sub1.right = sub3;

        boolean b = isSubtree(node1, sub1); //true
        sub1.right = new TreeNode(7);
        b = isSubtree(node1, sub1); //false


    }
}