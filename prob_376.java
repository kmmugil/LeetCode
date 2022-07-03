/**
 * 376. Wiggle Subsequence
 * <p>
 * Medium
 * <p>
 * A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with one element and a sequence with two non-equal elements are trivially wiggle sequences.
 * <p>
 * For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) alternate between positive and negative.
 * In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences.
 * The first is not because its first two differences are positive, and the second is not because its last difference is zero.
 * <p>
 * A subsequence is obtained by deleting some elements (possibly zero) from the original sequence, leaving the remaining elements in their original order.
 * <p>
 * Given an integer array nums, return the length of the longest wiggle subsequence of nums.
 */
public class prob_376 {

    public static void main(String[] args) {
        Solution_376 solution = new Solution_376();
//        int[] nums = {1, 7, 4, 9, 2, 5};
        int[] nums = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
//        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//        int[] nums = {0, 0};
        System.out.println(solution.wiggleMaxLength(nums));
    }

}

class Solution_376 {

    /**
     * Reverse iterating the array - Greedy algorithm
     * <p>
     * Here the trick is to reverse iterate the array and each for each iteration we compare the neighbouring elements.
     * <p>
     * If nums[i] < nums[i+1] then we add 1 to memo[1] (contains -ve diff) and assign to memo[0] (result becomes +ve diff)
     * <p>
     * if nums[i] > nums[i+1] then we add 1 to memo[0] (contains +ve diff) and assign to memo[1] (result becomes -ve diff)
     * <p>
     * Here memo[0] denotes the longest sequence which starts with a +ve diff and memo[1] denotes the longest sequence which starts with a -ve diff
     * <p>
     * Time Complexity - O(n), Space Complexity - O(1)
     * <p>
     * Proof:
     * <p>
     * There are two cases for each comparison (W.L.O.G. assume a[i] > a[i+1])
     * <p>
     * Then if memo[0] = memo[0] is valid even without comparison with right elements because if a[i+1] was part of the +ve diff start sequence, removing it and adding
     * a[i+1] would yield the same result. If a[i+1] wasn't include in the +ve diff start sequence, the same logic holds for the next element
     * <p>
     * memo[1] = 1+memo[0] is valid and is greater than memo[1] because
     * <p>
     * if memo[0] > memo[1] then memo[0]+1 > memo[1], if memo[0] < memo[1] then memo[0]+1 == memo[1]
     * <p>
     * Because at any point of time, the max difference between memo[0] ad memo[1] == 1
     */
    public int wiggleMaxLength(int[] nums) {
        int[] memo = new int[2];
        memo[0] = memo[1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] == nums[i + 1]) continue;
            if (nums[i] > nums[i + 1]) memo[1] = 1 + memo[0];
            else memo[0] = 1 + memo[1];
        }
        return Math.max(memo[0], memo[1]);
    }

}