class Main {
    public static void main(String[] args){
        MergableHeap heaper = MergableHeap.make_heap();
        System.out.println(heaper.heap);
        heaper.insert(17);
        heaper.insert(2);
        heaper.insert(5);
        heaper.insert(7);
        heaper.insert(9);
        heaper.insert(120);
        heaper.insert(4);
        heaper.insert(1);
        System.out.println(heaper.heap);
        System.out.println(heaper.minimum());
    }
}