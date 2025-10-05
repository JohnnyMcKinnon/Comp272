/*
 * *** Johnny McKinnon Comp272 001 ***
 *
 * This file implements a simple AVL Tree focused only on INSERTIONS.
 * You are to implement the four rotation methods so that insertions
 * keep the tree balanced. No deletion of nodes is required in this assignment.
 *
 * Notes to students:
 *  - We use heightOf(null) = -1 and height(leaf) = 0.
 *  - Duplicates are ignored (do nothing).
 *  - The preorderTraversal() returns values separated by a single space,
 *    and ends with a space (e.g., "20 10 30 ").
 */

class Node {
    int value;
    int height;
    Node leftChild, rightChild;

    public Node(int data) {
        value = data;
        height = 0;
        leftChild = rightChild = null;
    }
}

public class LUC_AVLTree {
    private Node rootNode;

    public LUC_AVLTree()         { rootNode = null; }
    public void removeAll()      { rootNode = null; }
    public boolean checkEmpty()  { return rootNode == null; }
    public void insert(int v)    { rootNode = insertElement(v, rootNode); }
    public String preorderTraversal() { return preorderTraversal(rootNode); }

    // ----- Helpers we need for insertion/height/balance -----
    private int getHeight(Node n) { return (n == null) ? -1 : n.height; }
    private int getMax(int a, int b) { return (a > b) ? a : b; }

    private int getBalanceFactor(Node n) {
        if (n == null) return 0;
        int lh = getHeight(n.leftChild)  + 1;
        int rh = getHeight(n.rightChild) + 1;
        return lh - rh; // balanced if in {-1,0,1}
    }

    private String preorderTraversal(Node n) {
        if (n == null) return "";
        return n.value + " " + preorderTraversal(n.leftChild) + preorderTraversal(n.rightChild);
    }

    // ----- Insert with rebalancing (calls your rotations) -----
    private Node insertElement(int value, Node node) {
        if (node == null) return new Node(value);

        if (value < node.value) {
            node.leftChild = insertElement(value, node.leftChild);
            int bf = getBalanceFactor(node);
            if (Math.abs(bf) > 1) {
                // Left heavy: choose LL or LR based on where we inserted
                if (value < node.leftChild.value) {
                    node = LLRotation(node);   // TODO: you implement
                } else {
                    node = LRRotation(node);   // TODO: you implement
                }
            }
        } else if (value > node.value) {
            node.rightChild = insertElement(value, node.rightChild);
            int bf = getBalanceFactor(node);
            if (Math.abs(bf) > 1) {
                // Right heavy: choose RR or RL based on where we inserted
                if (value > node.rightChild.value) {
                    node = RRRotation(node);   // TODO: you implement
                } else {
                    node = RLRotation(node);   // TODO: you implement
                }
            }
        } else {
            // duplicate -> do nothing
        }

        // update height on the way back up
        node.height = getMax(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        return node;
    }

    /***********************************************************
     * YOUR WORK STARTS HERE
     * Implement the four rotations below. Keep them small and neat.
     * After rotation, update the heights of the nodes you moved.
     ***********************************************************/

    /**
     * LLRotation: Right-rotate around X when left-left heavy.
     * Return the new subtree root.
     */
    private Node LLRotation(Node x) {
        // TODO: implement, then UPDATE heights, then return new root
        return x; // placeholder so code compiles
    }

    /**
     * RRRotation: Left-rotate around X when right-right heavy.
     * Return the new subtree root.
     */
    private Node RRRotation(Node x) {
        // TODO: implement, then UPDATE heights, then return new root
        return x; // placeholder so code compiles
    }

    /**
     * LRRotation: First rotate left child to the left, then rotate X to the right.
     * Return the new subtree root.
     */
    private Node LRRotation(Node x) {
        // TODO: implement using your other rotations (small and clean)
        return x; // placeholder so code compiles
    }

    /**
     * RLRotation: First rotate right child to the right, then rotate X to the left.
     * Return the new subtree root.
     */
    private Node RLRotation(Node x) {
        // TODO: implement using your other rotations (small and clean)
        return x; // placeholder so code compiles
    }
}