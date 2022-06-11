import java.util.Arrays;

/**
 * 1658. Minimum Operations to Reduce X to Zero
 * Medium
 * You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its
 * value from x. Note that this modifies the array for future operations.
 *
 * Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.
 */
public class prob_1658 {
    public static void main(String[] args) {
        Solution_1658 solution = new Solution_1658();
//        int[] nums = {1,1,4,2,3};
//        int x = 5;
        int[] nums = {3,2,20,1,1,3};
        int x = 10;
//        int[] nums = {1,1};
//        int x = 3;
//        int[] nums = {2,2,5,1,1};
//        int x = 5;
        System.out.println(solution.minOperations_v2(nums, x));
    }
}

class Solution_1658 {

    int[] nums;
    int[][] store;

    /**
     * Solution using dynamic programming
     * Time Complexity - O(n*n), Space Complexity - O(n*n)
     */
    public int minOperations(int[] nums, int x) {
        this.nums = nums;
        store = new int[nums.length][nums.length];
        for (int[] ints : store) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        int min = this.recurseShrinkArray(0, nums.length-1, x);
        for (int[] ints : store) {
            System.out.println(Arrays.toString(ints));
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int recurseShrinkArray(int i, int j, int bal) {
        if(i >= nums.length || j < 0 || bal < 0) return Integer.MAX_VALUE;
        if(store[i][j] != Integer.MAX_VALUE) return store[i][j];
        if(bal == 0) {
            store[i][j] = 0;
        } else {
            int min = Integer.MAX_VALUE;
            min = Math.min(min, this.recurseShrinkArray(i+1, j, bal-nums[i]));
            min = Math.min(min, this.recurseShrinkArray(i, j-1, bal-nums[j]));
            store[i][j] = min == Integer.MAX_VALUE ? min : min+1;
        }
        return store[i][j];
    }

    /**
     * Solution using contra proof
     * Time Complexity - O(n), Space Complexity - O(1)
     */
    public int minOperations_v2(int[] nums, int x) {
        int i, j, runningCount = 0, max = Integer.MIN_VALUE;
        x = -x;
        for (int num : nums) {
            x += num;
        }
        for(i = 0, j = 0; j < nums.length; j++) {
            runningCount += nums[j];
            while(runningCount > x && i <= j) runningCount -= nums[i++];
            if(runningCount == x) max = Math.max(max, j-i+1);
        }
        return max == Integer.MIN_VALUE ? -1 : nums.length-max;
    }

}
