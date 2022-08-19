import java.util.*;
import java.util.stream.Collectors;

/**
 * 659. Split Array into Consecutive Subsequences
 * <p>
 * Medium
 * <p>
 * You are given an integer array nums that is sorted in non-decreasing order.
 * <p>
 * Determine if it is possible to split nums into one or more subsequences such that both of the following conditions are true:
 * <p>
 * Each subsequence is a consecutive increasing sequence (i.e. each integer is exactly one more than the previous integer).
 * <p>
 * All subsequences have a length of 3 or more.
 * <p>
 * Return true if you can split nums according to the above conditions, or false otherwise.
 * <p>
 * A subsequence of an array is a new array that is formed from the original array by deleting some (can be none) of the elements without disturbing the relative positions of the remaining elements. (i.e., [1,3,5] is a subsequence of [1,2,3,4,5] while [1,3,2] is not).
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^4
 * <p>
 * -1000 <= nums[i] <= 1000
 * <p>
 * nums is sorted in non-decreasing order.
 */
public class prob_659 {

    public static void main(String[] args) {
        Solution_659 solution = new Solution_659();
//        int[] nums = {1, 2, 3, 3, 4, 5};
//        int[] nums = {1, 2, 3, 3, 4, 4, 5, 5};
//        int[] nums = {1, 2, 3, 5, 5, 6, 7};
        int[] nums = {1, 2, 3, 4, 5, 5, 6, 7}; // this test case fails the first greedy approach of construction 
//        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(solution.isPossible(nums));
    }

}

class Solution_659 {

    /**
     * This algorithm fails firstly because it just captures the increasing nature and not consecutive nature, although this can be fixed by omitting the pre-processing step
     * <p>
     * But even then the greedy approach constructing possible consecutive sub-sequences - length 3, fails in this test case {1, 2, 3, 4, 5, 5, 6, 7} (Need dynamic
     * programming on top to make this algorithm work)
     */
    public boolean isPossible_v2(int[] nums) {
        Integer[] count = new Integer[2001];
        for (int num : nums) {
            if (Objects.isNull(count[num + 1000])) count[num + 1000] = 1;
            else count[num + 1000] += 1;
        }
        // pre-processing step START
        int[] counts = Arrays.stream(count).filter(Objects::nonNull).mapToInt(i -> i).toArray();
        int[] ends = new int[counts.length];
        System.out.println(Arrays.toString(counts));
        // pre-processing step END

        List<int[]> pendingList = new ArrayList<>();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 0) continue;
            int tmp = counts[i];
            for (int[] ints : pendingList) {
                ints[0] += 1;
                ints[2] = i;
                if (ints[0] == 3) ends[i] += 1;
                if (--tmp == 0) break;
            }
            pendingList = pendingList.stream().filter(ints -> ints[0] != 3).collect(Collectors.toList());
            for (int j = 0; j < tmp; j++) {
                pendingList.add(new int[]{1, i, i});
            }
        }
        System.out.println(Arrays.toString(ends));
        for (int[] ints : pendingList) {
            System.out.println(Arrays.toString(ints));
        }
        for (int[] ints : pendingList) {
            if (ints[1] - 1 >= 0 && ends[ints[1] - 1] != 0) {
                ends[ints[1] - 1]--;
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * The greedy algorithm
     * <p>
     * Is to use the current element to append existing valid config
     * <p>
     * (or)
     * <p>
     * Initiate the array with a size of 3 and if this also fails return false
     * <p>
     * Time Complexity - O(N), Space Complexity - O(1)
     */
    public boolean isPossible(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] += 1000;
        }
        int[] count = new int[2001];
        int[] next = new int[count.length + 1];
        for (int num : nums) {
            count[num] += 1;
        }
        for (int num : nums) {
            if (count[num] == 0) continue;
            if (next[num] > 0) {
                next[num]--;
                count[num]--;
                next[num + 1]++;
            } else if (num + 2 < 2001 && count[num + 1] > 0 && count[num + 2] > 0) {
                count[num]--;
                count[num + 1]--;
                count[num + 2]--;
                next[num + 3]++;
            } else {
                return false;
            }
        }
        return true;
    }

}