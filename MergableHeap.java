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

    public int extract_min() {
        if (heap.isEmpty()) throw new RuntimeException("Heap is empty");
        int minimum_value = this.heap.get(0);
        // this.heap.set(0, this.heap.get(this.heap.size()-1));
        this.heap.set(0, this.heap.get(this.heap.size()-1));
        this.heap.remove(this.heap.get(this.heap.size()-1));
        min_heapify(0);
        return minimum_value;
    }

    public void min_heapify(int index) {
        int left_index = 2 * index;
        int right_index = 2 * index + 1;
        int largest_index;
        if(left_index <= this.heap.size() && this.heap.get(left_index) < this.heap.get(index)) {
            largest_index = left_index;
        } else {
            largest_index = index;
        }
        if(right_index <= this.heap.size() && this.heap.get(right_index) < this.heap.get(index)) {
            largest_index = right_index;
        }
        if(largest_index != index){
            swap_indexes(index, largest_index);
            min_heapify(largest_index);
        }
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
