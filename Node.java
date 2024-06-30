public class Node {
    int value;
    Node left = null;
    Node right = null;
    Node parent = null;

    public Node(int value) {
        this.value = value;
        
    }

    public void set_left(Node left_node) {
        this.left = left_node;
        left_node.parent = this;
    }
    
    public void set_right(Node right_node) {
        this.right = right_node;
        right_node.parent = this;
    }

    public void delete_node() {
        if(this.parent == null){return;}
        else if(this.parent.left == this){this.parent.left = null;}
        else{this.parent.right = null;}
    }
}
