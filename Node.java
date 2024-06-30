/**
 * Represents a Node in a binary tree.
 */
public class Node {
    /** The value stored in the node. */
    int value;

    /** Reference to the left child node. */
    Node left = null;

    /** Reference to the right child node. */
    Node right = null;

    /** Reference to the parent node. */
    Node parent = null;

    /**
     * Initializes a Node with a given value.
     * @param value The value to store in the node.
     */
    public Node(int value) {
        this.value = value;
    }

    /**
     * Sets the left child of the node and updates the child's parent reference.
     * @param left_node The node to set as the left child.
     */
    public void set_left(Node left_node) {
        this.left = left_node;
        left_node.parent = this;
    }
    
    /**
     * Sets the right child of the node and updates the child's parent reference.
     * @param right_node The node to set as the right child.
     */
    public void set_right(Node right_node) {
        this.right = right_node;
        right_node.parent = this;
    }

    /**
     * Deletes the node from the tree by nullifying its parent's reference to it.
     */
    public void delete_node() {
        if(this.parent == null){return;}
        else if(this.parent.left == this){this.parent.left = null;}
        else{this.parent.right = null;}
    }
}
