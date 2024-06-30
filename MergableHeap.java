public class MergableHeap {
    public int[] heap;

    public MergableHeap() {
        this.heap = new int[1];
    }

    public static MergableHeap make_heap() {
        return null;
    }

    public void insert(MergableHeap heap) {
        return;
    }

    public int minimum() {
        if (heap == null) throw new RuntimeException("Heap is empty");
        return this.heap[0];
    }

    public void extract_min(MergableHeap heap) {
        return;
    }

    public void union(MergableHeap heap) {
        return;
    }
}
