import java.util.ArrayList;
import java.util.List;

public class MergableHeap {
    public List<Integer> heap;

    public MergableHeap() {
        this.heap = new ArrayList<>();  
    }

    public static MergableHeap make_heap() {
        return new MergableHeap();
    }

    public void insert(int value) {
        this.heap.add(Integer.MAX_VALUE);
        this.heap_increase_key(this.heap.size()-1, value);
    }

    public void heap_increase_key(int index, int key) {
        if (key >= this.heap.get(index)){return;}
        this.heap.set(index, key);
        while(index > 0 && this.heap.get((int) (index/2)) > this.heap.get(index)){
            int parent_index = (int) (index/2);
            swap_indexes(index, parent_index);
            index = parent_index;
        }
    }

    public void swap_indexes(int index_1, int index_2) {
        int temp = this.heap.get(index_1);
        this.heap.set(index_1, this.heap.get(index_2));
        this.heap.set(index_2, temp);
    }

    public int minimum() {
        if (heap.isEmpty()) throw new RuntimeException("Heap is empty");
        return this.heap.get(0);
    }

    public void extract_min(MergableHeap heap) {
        return;
    }

    public static MergableHeap union(MergableHeap heap1, MergableHeap heap2) {
        MergableHeap new_heap = heap1;
        for(int key : heap2.heap) {
            new_heap.insert(key);
        }
        return new_heap;
    }

    public MergableHeap make_heap_from_file(String path_to_file, String input_type) {
        return null;
    }

    public void print_heap() {
        System.out.println(this.heap);
    }

    public void pretty_print_heap() {
        System.out.print("\n");
        int level =1;
        for(int i=0; i<this.heap.size(); i++) {
            System.out.print(this.heap.get(i));
            System.out.print(" ");
            if(i+1==Math.pow(2, level)-1){
                System.out.print("\n");
                level++;
            }
        }
        System.out.print("\n\n");
    }
}
