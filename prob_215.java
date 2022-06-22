import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 215. Kth Largest Element in an Array
 * Medium
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 */
public class prob_215 {
    public static void main(String[] args) {
        Solution_215 solution = new Solution_215();
        int[] nums = new int[] {3,2,1,5,6,4};
        int k = 2;
        System.out.println(solution.findKthLargest_v2(nums, k));
    }
}

/**
 * Need to implement solution using quick-select
 */
class Solution_215 {
    /**
     * Solution using max-heap implementation of Priority Queue
     * Time Complexity - O(NlogN + KlogN), Space Complexity - O(n)
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            pQueue.add(num);
        }
        for (int i = 0; i < k-1; i++) {
            pQueue.remove();
        }
        if(pQueue.isEmpty()) return Integer.MIN_VALUE;
        return pQueue.peek();
    }

    /**
     * Solution using sorting
     * Time Complexity - O(NlogN), Space Complexity - O(1)
     */
    public int findKthLargest_v2(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
}
