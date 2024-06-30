import java.util.ArrayList;
import java.util.List;

public class MergableHeap {
    public List<Integer> heap;

    public MergableHeap() {
        this.heap = new ArrayList<>();  
    }

    public static MergableHeap make_heap() {
        return null;
    }

    public void insert(MergableHeap heap) {
        return;
    }

    public int minimum() {
        if (this.heap == null) throw new RuntimeException("Heap is empty");
        return this.heap.get(0);
    }

    public void extract_min(MergableHeap heap) {
        return;
    }

    public void union(MergableHeap heap) {
        return;
    }

    public MergableHeap make_heap_from_file(String path_to_file, String input_type) {
        return null;
    }

    public void print_heap() {
        return;
    }
}
