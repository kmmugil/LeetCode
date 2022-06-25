import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 1354. Construct Target Array With Multiple Sums
 * Hard
 * You are given an array target of n integers. From a starting array arr consisting of n 1's, you may perform the following procedure :
 *
 * let x be the sum of all elements currently in your array.
 * choose index i, such that 0 <= i < n and set the value of arr at index i to x.
 * You may repeat this procedure as many times as needed.
 * Return true if it is possible to construct the target array from arr, otherwise, return false.
 */
public class prob_1354 {
    public static void main(String[] args) {
        Solution_1354 solution = new Solution_1354();
        int[] target = new int[] {8,5};
        System.out.println(solution.isPossible(target));
    }
}

class Solution_1354 {

    /**
     * Solution using Max Heap implemented using Priority Queue
     * This is the data structure to use when we want to find the max/min/most-frequent element in each iteration which modifies that array
     * Time Complexity - O(NlogN), Space Complexity - O(N)
     * Where N is the length of the target array
     */
    public boolean isPossible(int[] target) {
        if(target.length == 1) return target[0] == 1;
        int tSum = 0, cMax, tmp;
        PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i : target) {
            maxPriorityQueue.add(i);
            tSum += i;
        }
        while(!maxPriorityQueue.isEmpty()) {
            cMax = maxPriorityQueue.remove();
            if(cMax == 1 || tSum - cMax == 1) return true;
            tmp = cMax % (tSum - cMax);
            if(tmp == 0 || tmp == cMax) return false;
            tSum -= cMax - tmp;
            maxPriorityQueue.add(tmp);
        }
        return true;
    }

}
