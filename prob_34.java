import java.util.Arrays;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 * <p>
 * Medium
 * <p>
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * <p>
 * If target is not found in the array, return [-1, -1].
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class prob_34 {

    public static void main(String[] args) {
        Solution_34 solution = new Solution_34();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        System.out.println(Arrays.toString(solution.searchRange(nums, target)));
    }

}

/**
 * Solution using two binary searches, one to find the minimum edge which matches target and the other to find the maximum edge that matches target
 * <p>
 * Time Complexity - O(logN), Space Complexity - O(1)
 */
class Solution_34 {

    public int[] searchRange(int[] nums, int target) {
        int[] edges = new int[2];
        edges[0] = this.findEdgeBinarySearch(nums, target, true);
        edges[1] = this.findEdgeBinarySearch(nums, target, false);
        return edges;
    }

    public int findEdgeBinarySearch(int[] nums, int target, boolean flag) {
        int left = 0, right = nums.length - 1, mid = (left + right) / 2, edge = -1;
        while (left <= right) {
            if (flag && nums[mid] == target) {
                edge = mid;
                if (mid == 0) return edge;
                right = mid - 1;
            } else if (!flag && nums[mid] == target) {
                edge = mid;
                if (mid == nums.length - 1) return edge;
                left = mid + 1;
            } else if (nums[mid] > target) right = mid - 1;
            else left = mid + 1;
            mid = (left + right) / 2;
        }
        return edge;
    }

}