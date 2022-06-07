import java.util.Arrays;

/**
 * 88. Merge Sorted Array
 * Easy
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 *
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 *
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the
 * first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 */
public class prob_88 {
    public static void main(String[] args) {
        Solution_88 solution = new Solution_88();
        int[] nums1 = {1,2,3,0,0,0}, nums2 = {2,5,6};
        int m = 3, n = 3;
        solution.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }
}

/**
 * Solution using array traversal
 * Time Complexity - O(m+n), Space Complexity - O(m+n)
 * where m,n are the lengths of the corresponding arrays
 */
class Solution_88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] mergeArray = new int[m+n];
        int i, j, k;
        for (i = 0, j = 0, k = 0; i < m && j < n; k++) {
            if(nums1[i] <= nums2[j]) {
                mergeArray[k] = nums1[i];
                i++;
            }
            else {
                mergeArray[k] = nums2[j];
                j++;
            }
        }
        if(i < m) {
            for (; i < m; i++, k++) {
                mergeArray[k] = nums1[i];
            }
        } else {
            for (; j < n; j++, k++) {
                mergeArray[k] = nums2[j];
            }
        }
        for (i = 0; i < m+n; i++) {
            nums1[i] = mergeArray[i];
        }
    }
}