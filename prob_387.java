/**
 * 387. First Unique Character in a String
 * <p>
 * Easy
 * <p>
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * <p>
 * s consists of only lowercase English letters.
 */
public class prob_387 {

    public static void main(String[] args) {
        Solution_387 solution = new Solution_387();
        String[] s = {"leetcode", "loveleetcode", "aabb"};
        System.out.println(solution.firstUniqChar(s[2]));
    }

}

/**
 * Solution using simple array traversal
 * <p>
 * Time Complexity - O(N), Space Complexity - O(N)
 */
class Solution_387 {

    public int firstUniqChar(String s) {
        int[] alphabet = new int[26];
        char[] string = s.toCharArray();
        for (int i = 0; i < string.length; i++) {
            if (alphabet[string[i] - 'a'] == 0) alphabet[string[i] - 'a'] = i + 1;
            else alphabet[string[i] - 'a'] = Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        for (int i : alphabet) {
            if (i > 0 && i < Integer.MAX_VALUE) min = Math.min(min, i - 1);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

}