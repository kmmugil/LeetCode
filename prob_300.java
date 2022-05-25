import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 300. Longest Increasing Subsequence
 * Medium
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 */
public class prob_300 {
    public static void main(String[] args) {
        Solution_300 solution = new Solution_300();
//        int[] nums = {10,9,2,5,3,7,101,18};
//        int[] nums = {3,5,6,2,5,4,1,5,6,7,12};
        int[] nums = {1,4,8,5,3,2,0,3};
//        int[] nums = {0,1,0,3,2,3};
//        int[] nums = {10,9};
        System.out.println(solution.lengthOfLIS_v2(nums));
    }
}

class Solution_300 {
    /**
     * Solution using Dynamic Programming
     * Time Complexity - O(n^2), Space Complexity - O(n)
     * Here the sub-problems are the longest increasing subsequence including the index[i], then calculating the max length from those sub-problems
     */
    public int lengthOfLIS(int[] nums) {
        int[] tab = new int[nums.length]; int max = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i; j < nums.length; j++) {
                if(nums[j] > nums[i]) tab[i] = Math.max(tab[i], tab[j]);
            }
            tab[i] += 1;
            max = Math.max(max, tab[i]);
        }
        return max;
    }

    /**
     * Time Complexity - O(nlogn), Space Complexity - O(n)
     * Here while constructing the increasing sequence if we encounter a smaller value than the head,
     * we replace the next value greater than the current value
     * Note that the sequence constructed may not occur in the same order in the original array, the goal here is to minimize the head
     * Although the original array might be different, while replacing the elements it is to be thought of as a future array which has all the lesser existing elements than the
     * current index and this replaced one with other higher ones which result in higher counts than original max
     */
    public int lengthOfLIS_v2(int[] nums) {
        int[] memo = new int[nums.length];
        int count = 1;
        memo[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > memo[count-1]) {
                memo[count] = nums[i];
                count++;
            } else {
                int idx = Arrays.binarySearch(memo, 0, count, nums[i]);
                idx = idx < 0 ? -(idx+1) : idx;
                System.out.println("Replacing: "+idx);
                memo[idx] = nums[i];
            }
            System.out.println(Arrays.toString(memo));
        }
        return count;
    }

}
