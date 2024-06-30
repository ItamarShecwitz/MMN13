import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MergableHeap {
    public Node head;

    public MergableHeap() {
        this.head = null;  
    }

    public static MergableHeap make_heap() {
        return new MergableHeap();
    }

    public void insert(int value) {
        Node new_node = new Node(value, null);
        if(this.head == null) {this.head = new_node;}
        else {this.get_last_node().set_left(new_node);}
    }

    public int minimum() {
        if (this.head == null) throw new RuntimeException("Heap is empty");
        return this.head.value;
    }

    public int extract_min() {
        if (this.head == null) throw new RuntimeException("Heap is empty");
        return 1;
    }

    public Node get_last_node() {
        if (this.head == null) throw new RuntimeException("Heap is empty");
        Node temp_node = this.head;
        while(temp_node.left != null) {
            temp_node = temp_node.left;
        }
        return temp_node;
    }

    public static MergableHeap union(MergableHeap heap1, MergableHeap heap2) {
        if (heap1 == null) throw new RuntimeException("Heap is empty");
        return null;

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

    public void print_heap(Node node) {
        System.out.println(node.value);
        if(node.left != null) {print_heap(node.left);}
        if(node.right != null) {print_heap(node.right);}
    }

}
