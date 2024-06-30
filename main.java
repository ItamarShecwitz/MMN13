class Main {
    public static void main(String[] args){
        MergableHeap heaper = MergableHeap.make_heap_from_file("./example.txt", "hi");
        heaper.print_heap(heaper.head);
        // heaper.insert(17);
        // heaper.insert(2);
        // heaper.insert(5);
        // heaper.insert(7);
        // heaper.insert(9);
        // heaper.insert(120);
        // heaper.insert(4);
        // heaper.insert(1);
        // heaper.pretty_print_heap();
        // System.out.println(heaper.minimum());

        // MergableHeap heaper2 = MergableHeap.make_heap();
        // heaper2.insert(18);
        // heaper2.insert(3);
        // heaper2.insert(10);
        // heaper2.insert(90);
        // heaper2.insert(91);
        // heaper2.insert(1210);
        // heaper2.insert(14);
        // heaper2.insert(143);
        // heaper2.pretty_print_heap();

        // MergableHeap new_heap = MergableHeap.union(heaper, heaper2);
        // new_heap.pretty_print_heap();

        // System.out.println(new_heap.extract_min());
        // new_heap.pretty_print_heap();
    }
}