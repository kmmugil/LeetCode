import java.util.HashSet;
import java.util.Hashtable;

/**
 * 128. Longest Consecutive Sequence
 * <p>
 * Medium
 * <p>
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * <p>
 * You must write an algorithm that runs in O(n) time.
 */
public class prob_128 {

    public static void main(String[] args) {
        Solution_128 solution = new Solution_128();
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(solution.longestConsecutive(nums));
    }

}

class Solution_128 {

    public int longestConsecutive(int[] nums) {
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        for (int num : nums) {
            hashtable.put(num, 1);
        }
        int maxLongestStreak = 0, longestStreak, nextVal;
        for (int i = 0; i < nums.length; i++) {
            if (hashtable.get(nums[i] - 1) != null) continue;
            longestStreak = 1;
            nextVal = nums[i] + 1;
            while (hashtable.get(nextVal) != null) {
                longestStreak++;
                nextVal++;
            }
            maxLongestStreak = Math.max(maxLongestStreak, longestStreak);
        }
        return maxLongestStreak;
    }

    /**
     * One of the best examples to showcase that brute-force solution idea can give rise to a better optimized solution
     * <p>
     * Since sorting is one of the options with time complexity of O(NLogN), it is somewhat silly to first think of the brute force approach
     * <p>
     * But this optimized O(N) solution is a direct derivation from the brute force approach with time complexity O(N^3) - each "contains" search (array traversal) would result
     * in O(N) time and worse case if all N #'s form the continuous sequence it would result in:
     * <p>
     * O(N^3) without the middle # continue optimization, O(N^2) with middle # continue optimization
     * <p>
     * Time Complexity - O(N), Space Complexity - O(1)
     */
    public int longestConsecutive_v2(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        int maxLongestStreak = 0, longestStreak, nextVal;
        for (int i = 0; i < nums.length; i++) {
            // this is key optimization which avoids counting while in the middle of a streak
            if (hashSet.contains(nums[i] - 1)) continue;
            longestStreak = 1;
            nextVal = nums[i] + 1;
            while (hashSet.contains(nextVal)) {
                longestStreak++;
                nextVal++;
            }
            maxLongestStreak = Math.max(maxLongestStreak, longestStreak);
        }
        return maxLongestStreak;
    }

}