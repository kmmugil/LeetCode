import java.util.Arrays;

/**
 * 1480. Running Sum of 1d Array
 * Easy
 * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
 *
 * Return the running sum of nums.
 */
public class prob_1480 {
    public static void main(String[] args) {
        Solution_1480 solution = new Solution_1480();
        int[] nums = {1,2,3,4};
        System.out.println(Arrays.toString(solution.runningSum(nums)));
    }
}

/**
 * Solution using for loop
 * Time Complexity - O(n), Space Complexity - O(1)
 */
class Solution_1480 {
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i-1];
        }
        return nums;
    }
}
