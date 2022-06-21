import java.util.Arrays;
import java.util.Comparator;

/**
 * Implementation of the max-heap data structure
 * Property - The root/parent node is always greater than or equal to that of its child nodes
 * Operations:
 * 1. Insertion  - Insert new element into heap such that the property is maintained - O(logN)
 * 2. Peeking    - Peek the max element in the heap - O(1)
 * 3. Extraction - Extract the max element from the heap such the property is maintained in the resultant heap - O(logN)
 */
public class Heap {

    private final int[] heapData;

    private Comparator<Integer> comparator;

    private int size;

    public Heap(int maxSize, boolean isMax) {
        this.heapData = new int[maxSize];
        this.size = 0;
        this.comparator = this.generateHeapComparator(isMax);
    }

    public int peek() {
        return this.heapData[0];
    }

    public void insert(int node) {
        this.heapData[this.size] = node;
        int childPosition = this.size;
        int parentPosition = this.getParentPosition(childPosition);
        while(this.heapData[childPosition] > this.heapData[parentPosition]) {
            this.swap(childPosition, parentPosition);
            childPosition = parentPosition;
            parentPosition = this.getParentPosition(childPosition);
        }
        this.size++;
    }

    public int pop() {
        int node = this.heapData[0];
        this.heapData[0] = this.heapData[this.size--];
        this.maintainHeap(0);
        return node;
    }

    private int getParentPosition(int position) {
        return (position - 1) / 2;
    }

    private int getLeftChildPosition(int position) {
        return position * 2 + 1;
    }

    private int getRightChildPosition(int position) {
        return position * 2 + 2;
    }

    private boolean isLeafPosition(int position) {
        return position >= size / 2;
    }

    private void swap(int first, int second) {
        int tmp = this.heapData[second];
        this.heapData[second] = this.heapData[first];
        this.heapData[first] = tmp;
    }

    private void maintainHeap(int position) {
        int leftChild, rightChild;
        if(!this.isLeafPosition(position)) {
            leftChild = this.getLeftChildPosition(position);
            rightChild = this.getRightChildPosition(position);
            if(this.comparator.compare(this.heapData[leftChild], this.heapData[position]) > 0 || this.comparator.compare(this.heapData[rightChild], this.heapData[position]) > 0) {
                if(this.comparator.compare(this.heapData[leftChild], this.heapData[rightChild]) > 0) {
                    this.swap(leftChild, position);
                    this.maintainHeap(leftChild);
                } else {
                    this.swap(rightChild, position);
                    this.maintainHeap(rightChild);
                }
            }
        }
    }

    private void convertHeap(boolean toMaxHeap) {
        this.comparator = this.generateHeapComparator(toMaxHeap);
        for (int i = size/2 - 1 ; i >= 0; i--) {
            this.maintainHeap(i);
        }
    }

    private Comparator<Integer> generateHeapComparator(boolean isMax) {
        return Comparator.comparing(ints -> ints, (i1, i2) -> {
            if(isMax) return i1-i2;
            else return i2-i1;
        });
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.size/2; i++) {
            stringBuilder.append(this.heapData[i]).append("\t\t").append(this.heapData[this.getLeftChildPosition(i)]).append("\t\t").append(this.heapData[this.getRightChildPosition(i)]).append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Heap heap = new Heap(12, true);
        for (int i = 0; i < 10; i++) {
            heap.insert(i);
        }
        System.out.println(heap);
        System.out.println(heap.peek());
        System.out.println(heap.pop());
        System.out.println(heap);
        heap.convertHeap(false);
        System.out.println(heap);
        heap.convertHeap(true);
        System.out.println(heap);
    }

}
