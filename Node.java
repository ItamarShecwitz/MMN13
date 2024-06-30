public class Node {
    int value;
    Node left = null;
    Node right = null;
    Node parent = null;

    public Node(int value, Node parent) {
        this.value = value;
        if (parent != null) {this.parent = parent;}
        
    }

    public void set_left(Node left_node) {
        this.left = left_node;
        left_node.parent = this;
    }
    
    public void set_right(Node right_node) {
        this.right = right_node;
        right_node.parent = this;
    }
}
