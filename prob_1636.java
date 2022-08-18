import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 1636. Sort Array by Increasing Frequency
 * <p>
 * Easy
 * <p>
 * Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.
 * <p>
 * Return the sorted array.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * <p>
 * -100 <= nums[i] <= 100
 */
public class prob_1636 {

    public static void main(String[] args) {
        Solution_1636 solution = new Solution_1636();
//        int[] nums = {1, 1, 2, 2, 2, 3};
        int[] nums = {-1, 1, -6, 4, 5, -6, 1, 4, 1};
        System.out.println(Arrays.toString(solution.frequencySort(nums)));
    }

}

class Solution_1636 {

    /**
     * Solution using bucket sort
     * <p>
     * Time Complexity - O(NlogN), Space Complexity - O(N)
     */
    public int[] frequencySort(int[] nums) {
        int[] values = new int[201];
        for (int num : nums) {
            values[num + 100] += 1;
        }
        List<List<Integer>> countList = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            countList.add(new ArrayList<>());
        }
        System.out.println(Arrays.toString(values));
        for (int i = 0; i < values.length; i++) {
            countList.get(values[i]).add(i);
        }
        System.out.println(countList);
        List<Integer> sortedList = new ArrayList<>();
        for (int i = 0; i < countList.size(); i++) {
            List<Integer> numList = countList.get(i);
            if (numList.size() == 0) continue;
            if (numList.size() == 1) {
                for (int j = 0; j < i; j++) {
                    sortedList.add(numList.get(0));
                }
                continue;
            }
            Integer[] intArr = numList.toArray(new Integer[0]);
            Arrays.sort(intArr, Comparator.comparingInt(ints -> ints));
            for (int k = intArr.length - 1; k >= 0; k--) {
                for (int j = 0; j < i; j++) {
                    sortedList.add(intArr[k]);
                }
            }
        }
        return sortedList.stream().mapToInt(Integer::intValue).map(i -> i - 100).toArray();
    }

}