import java.util.Arrays;

/**
 * 167. Two Sum II - Input Array Is Sorted
 * Medium
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number.
 * Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 *
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 *
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 *
 * Your solution must use only constant extra space.
 */
public class prob_167 {
    public static void main(String[] args) {
        Solution_167 solution = new Solution_167();
        int[] numbers = {2,7,11,15};
        int target = 9;
        int[] numbers1 = {1,2,3,4,4,9,56,90};
        int target1 = 8;
        System.out.println(Arrays.toString(solution.twoSum_v2(numbers1, target1)));
    }
}

class Solution_167 {
    int[] numbers;

    /**
     * Solution using binary search
     * Time Complexity - O(n*logn), Space Complexity - O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        this.numbers = numbers;
        int i,j;
        for (i = 0; i < numbers.length; i++) {
            j = this.binarySearch(target-numbers[i], i);
            if(j != -1) return new int[]{i+1,j+1};
        }
        return new int[]{-1,-1};
    }

    /**
     * Solution using array traversal - two indices
     * Time Complexity - O(n), Space Complexity - O(1)
     */
    public int[] twoSum_v2(int[] numbers, int target) {
        int twoSum;
        for (int i = 0, j = numbers.length-1; i < numbers.length && j >= 0; ) {
            twoSum = numbers[i]+numbers[j];
            if(twoSum == target) return new int[]{i+1, j+1};
            if(twoSum > target) j--;
            if(twoSum < target) i++;
        }
        return new int[]{-1,-1};
    }

    private int binarySearch(int target, int i) {
        int left = 0, right = numbers.length-1;
        while(left <= right) {
            int mid = (left+right)/2;
            mid = mid == i ? mid+1 : mid;
            if(numbers[mid] == target) {
                return mid;
            }
            if(numbers[mid] < target) left = mid+1;
            else right = mid-1;
        }
        return -1;
    }
}
