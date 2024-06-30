import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MergableHeap {
    public Node head;

    public MergableHeap() {
        this.head = null;  
    }

    public static MergableHeap make_heap() {
        return new MergableHeap();
    }

    public void insert(int value) {
        Node new_node = new Node(value);
        if(this.head == null) {this.head = new_node;}
        else {
            insert_after_last_node(this.head, new_node, 1, get_max_level(), false);
            min_heapify_up(new_node);
        }
    }

    public void min_heapify_up(Node node){
        Node parent = node.parent;
        if (parent == null) {return;}

        if(parent.value > node.value){
            swap(node, parent);
            min_heapify_up(parent);
        }
    }

    public void min_heapify_down(Node node){
        Node left = node.left;
        Node right = node.right;
        Node smallest;
        if (left != null && left.value < node.value) {smallest = left;}
        else {smallest = node;}
        if (right != null && right.value < smallest.value) {smallest = right;}
        if(smallest != node){
            swap(node, smallest);
            min_heapify_up(smallest);
        }
    }

    public static void swap(Node node1, Node node2){
        int temp_value = node1.value;
        node1.value = node2.value;
        node2.value = temp_value;
    }

    public boolean insert_after_last_node(Node node_parent, Node new_node, int level, int max_level, boolean placed) { 
        if(node_parent.left == null && level == max_level) {node_parent.set_left(new_node);return true;}
        else if(node_parent.right == null && level == max_level) {node_parent.set_right(new_node);return true;}
        else {
            if (node_parent.left != null && level < max_level) { placed = insert_after_last_node(node_parent.left, new_node, level+1, max_level, placed); }
            if (node_parent.right != null && level < max_level && !placed) { placed = insert_after_last_node(node_parent.right, new_node, level+1, max_level, placed); }
        }
        return placed;
    }

    public int get_max_level() {
        int max_level = 1;
        Node current_node = this.head;
        while(current_node.right != null) {
            current_node = current_node.right;
            max_level++;
        }
        return max_level;
    }

    public int minimum() {
        if (this.head == null) throw new RuntimeException("Heap is empty");
        return this.head.value;
    }

    public int extract_min() {
        if (this.head == null) throw new RuntimeException("Heap is empty");
        int min_value = head.value;
        swap(head, get_last_node());
        print_heap();
        get_last_node().delete_node();
        min_heapify_down(head);
        if(get_last_node() == head){head = null;}
        return min_value;
    }

    public Node get_last_node() {
        if (this.head == null) throw new RuntimeException("Heap is empty");
        Node temp_node = this.head;
        while(temp_node.left != null || temp_node.right != null) {
            if(temp_node.left != null){temp_node = temp_node.left;}
            else if(temp_node.right != null){temp_node = temp_node.right;}
        }
        return temp_node;
    }

    public static MergableHeap union(MergableHeap heap1, MergableHeap heap2) {
        if (heap1 == null) throw new RuntimeException("Heap 1 is empty");
        if (heap2 == null) throw new RuntimeException("Heap 2 is empty");
        MergableHeap new_heap = heap1;
        // Extract all elements from heap2 and insert them into heap1
        while (heap2.head != null) {
            int min_value = heap2.extract_min();
            new_heap.insert(min_value);
        }
        return new_heap;
    }

    public static MergableHeap make_heap_from_file(String path_to_file, String input_type) {
        MergableHeap new_mergable_heap = MergableHeap.make_heap();
        try (BufferedReader br = new BufferedReader(new FileReader(path_to_file))) {
            String line = br.readLine();
            while (line != null) {
                new_mergable_heap.insert(Integer.valueOf(line));
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new_mergable_heap;
    }

    public void print_heap() {
        int height = get_max_level()+1;
        for (int level = 1; level <= height; level++) {
            print_level(head, level);
            System.out.println(); // New line after each level
        }
    }

    private void print_level(Node node, int level) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            System.out.print(node.value + " ");
        } else if (level > 1) {
            print_level(node.left, level - 1);
            print_level(node.right, level - 1);
        }
    }

}
