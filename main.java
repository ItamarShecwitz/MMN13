class Main {
    public static void main(String[] args){
        System.out.println("\nInitilized new heap from file named heaper:");
        MergableHeap heaper = MergableHeap.make_heap_from_file("./example.txt");

        System.out.println("\nheaper:");
        heaper.print_heap();

        System.out.println("\nheaper min value:");
        System.out.println(heaper.extract_min());

        System.out.println("\nheaper without min value:");
        heaper.print_heap();


        heaper.insert(17);
        heaper.insert(2);
        heaper.insert(5);
        heaper.insert(7);
        heaper.insert(9);
        heaper.insert(120);
        heaper.insert(4);
        heaper.insert(1);

        System.out.println("\nheaper after adding 8 new nodes:");
        heaper.print_heap();

        System.out.println("\nheaper min value (again):");
        System.out.println(heaper.minimum());

        MergableHeap heaper2 = MergableHeap.make_heap();
        heaper2.insert(18);
        heaper2.insert(3);
        heaper2.insert(10);
        heaper2.insert(90);
        heaper2.insert(91);
        heaper2.insert(1210);
        heaper2.insert(14);
        heaper2.insert(143);

        System.out.println("\nmaking heaper2, with the make_heap function this time:");
        heaper2.print_heap();

        System.out.println("\nunite the heapers:");
        MergableHeap new_heap = MergableHeap.union(heaper, heaper2);
        new_heap.print_heap();

        System.out.println("\nnew united heaper min value:");
        System.out.println(new_heap.extract_min());

        
        System.out.println("\nnew heaper without min value:");
        new_heap.print_heap();
        
    }
}