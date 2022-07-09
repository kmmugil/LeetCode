import java.util.Arrays;

/**
 * 1696. Jump Game VI
 * <p>
 * Medium
 * <p>
 * You are given a 0-indexed integer array nums and an integer k.
 * <p>
 * You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array.
 * That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
 * <p>
 * You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.
 * <p>
 * Return the maximum score you can get.
 */
public class prob_1696 {

    public static void main(String[] args) {
        Solution_1696 solution = new Solution_1696();
        int[] nums = {1, -1, -2, 4, -7, 3};
        int k = 2;
        System.out.println(solution.maxResult_v2(nums, k));
    }

}

class Solution_1696 {

    int[] memo;

    int[] nums;

    int k;

    /**
     * Dynamic programming approach with recursive function call (memoization) (TOP-DOWN)
     * <p>
     * Time Complexity - O(N^2), Space Complexity - O(N)
     * <p>
     * TLE
     */
    public int maxResult(int[] nums, int k) {
        this.memo = new int[nums.length];
        Arrays.fill(memo, Integer.MIN_VALUE);
        this.nums = nums;
        this.k = k;
        memo[nums.length - 1] = nums[nums.length - 1];
        return this.recurseSteps(0);
    }

    private int recurseSteps(int i) {
        if (memo[i] != Integer.MIN_VALUE) return memo[i];
        int max = Integer.MIN_VALUE;
        int limit = Math.min(memo.length - 1, i + k);
        for (int j = i + 1; j <= limit; j++) {
            max = Math.max(max, this.recurseSteps(j));
        }
        memo[i] = max + nums[i];
        return memo[i];
    }

    /**
     * Dynamic programming without recursion (tabulation) (BOTTOM-UP)
     * <p>
     * Time Complexity - O(N), Space Complexity - O(N)
     * <p>
     * This approach has a time complexity of O(N) because on calculating a new maximum on interval cross, only 'k' elements would have to be examined. And in total the count
     * would be at most N forming N/K intervals, thus N/K * K = N
     */
    public int maxResult_v2(int[] nums, int k) {
        int[] memo = new int[nums.length];
        memo[nums.length - 1] = nums[nums.length - 1];
        int iMax = nums[nums.length - 1];
        int index = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            memo[i] = nums[i] + iMax;
            if (memo[i] >= iMax) {
                iMax = memo[i];
                index = i;
            } else {
                if (index == i + k) {
                    iMax = memo[i];
                    index = i;
                    for (int j = i + 1; j < i + k; j++) {
                        if (memo[j] > iMax) {
                            iMax = memo[j];
                            index = j;
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(memo));
        return memo[0];
    }

}