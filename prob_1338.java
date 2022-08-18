import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 1338. Reduce Array Size to The Half
 * <p>
 * Medium
 * <p>
 * You are given an integer array arr. You can choose a set of integers and remove all the occurrences of these integers in the array.
 * <p>
 * Return the minimum size of the set so that at least half of the integers of the array are removed.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= arr.length <= 10^5
 * <p>
 * arr.length is even.
 * <p>
 * 1 <= arr[i] <= 10^5
 */
public class prob_1338 {

    public static void main(String[] args) {
        Solution_1338 solution = new Solution_1338();
        int[] arr = new int[]{3, 3, 3, 3, 5, 5, 5, 2, 2, 7};
        System.out.println(solution.minSetSize(arr));
    }

}

class Solution_1338 {

    /**
     * Solution using sorting array by frequency using hash map and priority queue
     * <p>
     * Time Complexity - O(NlogN), Space Complexity - O(N)
     */
    public int minSetSize(int[] arr) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int value;
        for (int i : arr) {
            if (hashMap.containsKey(i)) {
                value = hashMap.get(i) + 1;
                hashMap.put(i, value);
            } else {
                hashMap.put(i, 1);
            }
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        hashMap.forEach((key, val) -> priorityQueue.add(val));
        int runningCount = 0;
        value = 0;
        while (runningCount < arr.length / 2) {
            runningCount += priorityQueue.remove();
            value += 1;
        }
        return value;
    }

}