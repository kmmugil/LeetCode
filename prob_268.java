/**
 * 268. Missing Number
 * Easy
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 */
public class prob_268 {
    public static void main(String[] args) {
        Solution_268 solution = new Solution_268();
        int[] nums = {9,6,4,2,3,5,7,0,1};
        System.out.println(solution.missingNumber(nums));
    }
}

/**
 * Simple solution using Math
 * Space Complexity - O(1), Time Complexity - O(n)
 * This can also be done using bit manipulation - XOR (think about it)
 */
class Solution_268 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = n*(n+1)/2;
        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }
}
