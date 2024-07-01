import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Represents a Mergable Heap data structure.
 */
public class MergableHeap {

    /** The root node of the heap. */
    public Node head;

    /**
     * Initializes an empty MergableHeap.
     */
    public MergableHeap() {
        this.head = null;
    }

    /**
     * Creates and returns a new instance of MergableHeap.
     * @return A new empty instance of MergableHeap.
     */
    public static MergableHeap make_heap() {
        return new MergableHeap();
    }

    /**
     * Inserts a new value into the heap.
     * @param value The value to insert.
     */
    public void insert(int value) {
        Node new_node = new Node(value);
        if (this.head == null) {
            this.head = new_node;
        } else {
            insert_after_last_node(this.head, new_node, 1, get_max_filled_level(), false);
            min_heapify_up(new_node);
        }
    }

    /**
     * Maintains the heap property by bubbling up recursively the node if necessary.
     * @param node The node to heapify up.
     */
    public void min_heapify_up(Node node) {
        Node parent = node.parent;
        if (parent == null) return;

        if (parent.value > node.value) {
            swap(node, parent);
            min_heapify_up(parent);
        }
    }

    /**
     * Maintains the heap property by bubbling down recursively the node if necessary.
     * @param node The node to heapify down.
     */
    public void min_heapify_down(Node node) {
        Node left = node.left;
        Node right = node.right;
        Node smallest = node;

        if (left != null && left.value < node.value) {
            smallest = left;
        }
        if (right != null && right.value < smallest.value) {
            smallest = right;
        }
        if (smallest != node) {
            swap(node, smallest);
            min_heapify_down(smallest);
        }
    }

    /**
     * Swaps the values of two nodes.
     * @param node1 The first node.
     * @param node2 The second node.
     * @throws RuntimeException if either node1 or node2 is empty.
     */
    public static void swap(Node node1, Node node2) {
        if (node1 == null) throw new RuntimeException("Node 1 is empty");
        if (node2 == null) throw new RuntimeException("Node 2 is empty");
        int temp_value = node1.value;
        node1.value = node2.value;
        node2.value = temp_value;
    }

    /**
     * Recursively finds the last node and plants the new node there.
     * @param node_parent The parent node to start the insertion.
     * @param new_node The new node to insert.
     * @param level The current level in the heap.
     * @param max_filled_level The maximum level (height) of the heap where the level is full.
     * @param placed Flag indicating if the node has been placed.
     * @return True if the node was successfully placed, false otherwise.
     */
    public boolean insert_after_last_node(Node node_parent, Node new_node, int level, int max_filled_level, boolean placed) {
        if (node_parent.left == null && level == max_filled_level) {
            node_parent.set_left(new_node);
            return true;
        } else if (node_parent.right == null && level == max_filled_level) {
            node_parent.set_right(new_node);
            return true;
        } else {
            if (node_parent.left != null && level < max_filled_level) {
                placed = insert_after_last_node(node_parent.left, new_node, level + 1, max_filled_level, placed);
            }
            if (node_parent.right != null && level < max_filled_level && !placed) {
                placed = insert_after_last_node(node_parent.right, new_node, level + 1, max_filled_level, placed);
            }
        }
        return placed;
    }

    /**
     * Gets the maximum level (height) of the heap where the level is full.
     * @return The maximum level (height) of the heap where the level is full.
     */
    public int get_max_filled_level() {
        if(this.head == null) {return 0;}
        int max_level = 1;
        Node current_node = this.head;
        while (current_node.right != null) {
            current_node = current_node.right;
            max_level++;
        }
        return max_level;
    }

    /**
     * Gets the maximum level (height) of the heap.
     * @return The maximum level (height) of the heap.
     */
    public int get_max_level() {
        if(this.head == null) {return 0;}
        int max_level = 1;
        Node current_node = this.head;
        while (current_node.left != null) {
            current_node = current_node.left;
            max_level++;
        }
        return max_level;
    }

    /**
     * Retrieves the minimum value of the heap.
     * @return The minimum value of the heap.
     * @throws RuntimeException if the heap is empty.
     */
    public int minimum() {
        if (this.head == null) throw new RuntimeException("Heap is empty");
        return this.head.value;
    }

    /**
     * Extracts and returns the minimum value from the heap.
     * @return The extracted minimum value.
     * @throws RuntimeException if the heap is empty.
     */
    public int extract_min() {
        if (this.head == null) throw new RuntimeException("Heap is empty");
        int min_value = head.value;
        Node last_node = get_last_node();

        if (last_node == head) {
            head = null;
            return min_value;
        }
        swap(head, last_node);
        last_node.delete_node();
        min_heapify_down(head);

        return min_value;
    }

    /**
     * Gets the last node in the heap.
     * @return The last node in the heap.
     * @throws RuntimeException if the heap is empty.
     */
    public Node get_last_node() {
        if (this.head == null) throw new RuntimeException("Heap is empty");
        return get_last_node(head, get_max_level(), null);
    }

    /**
     * Recursively finds the last node at a given level.
     * @param node The current node.
     * @param level The current level.
     * @param last_node The last node found so far.
     * @return The last node at the given level.
     */
    public Node get_last_node(Node node, int level, Node last_node) {
        if (level == 1 && node != null) {
            last_node = node;
        } else if (level > 1) {
            if (node.left != null) {
                last_node = get_last_node(node.left, level - 1, last_node);
            }
            if (node.right != null) {
                last_node = get_last_node(node.right, level - 1, last_node);
            }
        }
        return last_node;
    }

    /**
     * Combines two heaps into a new heap.
     * @param heap1 The first heap.
     * @param heap2 The second heap.
     * @return A new heap containing all elements from heap1 and heap2.
     * @throws RuntimeException if either heap1 or heap2 is empty.
     */
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

    /**
     * Creates a new heap from a file.
     * @param path_to_file The path to the file containing heap data.
     * @return A heap populated with data from the file.
     */
    public static MergableHeap make_heap_from_file(String path_to_file) {
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

    /**
     * Prints the heap.
     */
    public void print_heap() {
        int height = get_max_filled_level() + 1;
        for (int level = 1; level <= height; level++) {
            print_level(head, level);
            System.out.println(); // New line after each level
        }
    }

    /**
     * Helper method to print nodes at a specific level of the heap.
     * @param node The starting node.
     * @param level The current level in the heap.
     */
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