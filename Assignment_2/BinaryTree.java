/*
 * *** Johnny McKinnon Comp272 001 ***
 *
 * This file shows a simple Binary Tree (we'll insert like a BST for convenience).
 * You will implement two small helpers:
 *   1) replaceValueHelper(node, oldVal, newVal)  -- replace every match of oldVal with newVal
 *   2) averageHelper(node) -> int[]{sum, count}  -- return subtree sum and node count
 *
 * Keep your code clear and direct. No tricks.
 */

class BTNode {
    int data;
    BTNode left, right;
    BTNode(int d) { data = d; }
}

public class BinaryTree {
    private BTNode root;

    public BinaryTree() { root = null; }
    public void clear()  { root = null; }

    /* Insert like a simple BST so we can build a predictable shape quickly. */
    public void insert(int value) { root = insertRec(root, value); }
    private BTNode insertRec(BTNode n, int v) {
        if (n == null) return new BTNode(v);
        if (v < n.data) n.left  = insertRec(n.left, v);
        else if (v > n.data) n.right = insertRec(n.right, v);
        else { /* duplicate -> do nothing */ }
        return n;
    }

    /* Preorder traversal as a single string with spaces, ending with a space. */
    public String preOrder() { return preOrderRec(root); }
    private String preOrderRec(BTNode n) {
        if (n == null) return "";
        return n.data + " " + preOrderRec(n.left) + preOrderRec(n.right);
    }

    /* Replace all occurrences of oldVal with newVal in the entire tree. */
    public void replaceValue(int oldVal, int newVal) {
        replaceValueHelper(root, oldVal, newVal); // you implement the helper
    }

    /* Return the average of all node values as a double.
       If the tree is empty, we define the average to be 0.0 (simple and safe). */
    public double average() {
        if (root == null) return 0.0; // empty tree -> average is 0.0
        int[] pair = averageHelper(root); // pair[0] = sum, pair[1] = count
        int sum = pair[0], count = pair[1];
        if (count == 0) return 0.0;   // safety
        return (double) sum / (double) count;
    }

    /***********************************************************
     * YOUR WORK STARTS HERE
     * Implement the two helpers below. Keep them small and clean.
     ***********************************************************/

    /* replaceValueHelper:
       Visit every node. If node.data == oldVal, change it to newVal. */
    protected void replaceValueHelper(BTNode node, int oldVal, int newVal) {
        // TODO: implement (use simple recursion). If node is null, just return.
        if(node==null){
            return;
        }
        if(node.data==oldVal){
            node.data=newVal;
        }
        replaceValueHelper(node.left, oldVal, newVal);
        replaceValueHelper(node.right, oldVal, newVal);
    }

    /* averageHelper:
       Return an int array {sum, count} for this subtree.
       Use post-order style: get left pair, right pair, then add current node. */
    protected int[] averageHelper(BTNode node) {
        // TODO: implement and return {sum, count}
        if(node==null){
        return new int[]{0, 0}; // placeholder so code compiles
        }
        int[] left=averageHelper(node.left);
        int[] right=averageHelper(node.right);
        int sum=left[0]+right[0]+node.data;
        int count=left[1]+right[1]+1;
        return new int[]{sum,count};
    }
}