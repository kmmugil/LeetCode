import java.util.Arrays;

/**
 * 2351. First Letter to Appear Twice
 * <p>
 * Easy
 * <p>
 * Given a string s consisting of lowercase English letters, return the first letter to appear twice.
 * <p>
 * Note:
 * <p>
 * A letter a appears twice before another letter b if the second occurrence of a is before the second occurrence of b.
 * s will contain at least one letter that appears twice.
 * <p>
 * Constraints:
 * <p>
 * 2 <= s.length <= 100
 * <p>
 * s consists of lowercase English letters.
 * <p>
 * s has at least one repeated letter.
 */
public class prob_2351 {

    public static void main(String[] args) {
        Solution_2351 solution = new Solution_2351();
        String[] s = {"abccbaacz"};
        System.out.println(solution.repeatedCharacter(s[0]));
    }

}

/**
 * Solution using simple array traversal
 * <p>
 * Time Complexity - O(N), Space Complexity - O(N)
 */
class Solution_2351 {

    public char repeatedCharacter(String s) {
        int[] alphabet = new int[26];
        boolean[] hasRepeated = new boolean[26];
        char[] string = s.toCharArray();
        for (int i = 0; i < string.length; i++) {
            if (hasRepeated[string[i] - 'a']) continue;
            if (alphabet[string[i] - 'a'] == 0) {
                alphabet[string[i] - 'a'] = i + 1;
                continue;
            }
            hasRepeated[string[i] - 'a'] = true;
            alphabet[string[i] - 'a'] = i;
        }
        System.out.println(Arrays.toString(alphabet));
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < alphabet.length; i++) {
            if (hasRepeated[i]) {
                if (alphabet[i] < min) {
                    min = alphabet[i];
                    minIndex = i;
                }
            }
        }
        return (char) (minIndex + 'a');
    }

}