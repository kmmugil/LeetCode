import java.util.Arrays;

/**
 * 462. Minimum Moves to Equal Array Elements II
 * <p>
 * Medium
 * <p>
 * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
 * <p>
 * In one move, you can increment or decrement an element of the array by 1.
 * <p>
 * Test cases are designed so that the answer will fit in a 32-bit integer.
 */
public class prob_462 {

    public static void main(String[] args) {
        Solution_462 solution = new Solution_462();
        int[] nums = {1, 2, 3};
        System.out.println(solution.minMoves2_v2(nums));
    }

}

class Solution_462 {

    /**
     * Solution using sorting to find the median of the array.
     * <p>
     * Then finding the difference for all the numbers from that median, will give us the minimum # of moves needed to
     * make all the # equal in the array
     * <p>
     * Time Complexity - O(NLogN), Space Complexity - O(1)
     */
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int median, moveCount = 0;
        median = nums[(nums.length - 1) / 2];
        for (int num : nums) {
            moveCount += Math.abs(num - median);
        }
        return moveCount;
    }

    /**
     * Optimization using the fact that a[n-1-i]-a[median] + a[median]-a[i] translates to a[n-1-i]-a[i]
     * <p>
     * The final count doesn't actually depend on the median value
     */
    public int minMoves2_v2(int[] nums) {
        Arrays.sort(nums);
        int moveCount = 0, mid = (nums.length - 1) / 2;
        for (int i = 0; i < mid; i++) {
            moveCount += nums[nums.length - 1 - i] - nums[i];
        }
        return moveCount;
    }

    /**
     * Solution using quick selection
     */
    public int minMoves2_v3(int[] nums) {
        // TODO Implement solution using quick selection
        return 0;
    }

}