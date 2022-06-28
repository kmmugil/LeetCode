import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1647. Minimum Deletions to Make Character Frequencies Unique
 * Medium
 * A string s is called good if there are no two different characters in s that have the same frequency.
 * <p>
 * Given a string s, return the minimum number of characters you need to delete to make s good.
 * <p>
 * The frequency of a character in a string is the number of times it appears in the string.
 * For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
 */
public class prob_1647 {
    public static void main(String[] args) {
        Solution_1647 solution = new Solution_1647();
//        String s = "abcabc";
//        String s = "bbcebab";
//        String s = "adec";
//        String s = "accdcdadddbaadbc";
//        String s = "aaabbbcc";
        String s = "baeccdae";
        System.out.println(solution.minDeletions(s));
    }
}

/**
 * Solution using sorting and hashMap
 * Time Complexity - O(NlogN), Space Complexity - O(n)
 */
class Solution_1647 {
    public int minDeletions(String s) {
        Map<Character, Integer> charCount = new HashMap<>();
        Integer runningCount;
        for (char c : s.toCharArray()) {
            runningCount = charCount.get(c);
            if (runningCount == null) {
                charCount.put(c, 1);
            } else {
                charCount.put(c, runningCount + 1);
            }
        }
        int[] counts = new int[charCount.size()];
        int i = 0;
        for (Map.Entry<Character, Integer> characterIntegerEntry : charCount.entrySet()) {
            counts[i] = characterIntegerEntry.getValue();
            i++;
        }
        Arrays.sort(counts);
        System.out.println(Arrays.toString(counts));
        int minCount = 0, equalCount = 0;
        for (i = counts.length - 2; i >= 0; i--) {
            if (counts[i + 1] > counts[i]) {
                if (equalCount != 0) {
                    int j, tmp = counts[i + 1];
                    for (j = 1; j <= equalCount && tmp != 0; j++) {
                        minCount += j;
                        tmp--;
                    }
                    for (; j <= equalCount; j++) {
                        minCount += counts[i + 1];
                    }
                    counts[i + 1] = tmp;
                    equalCount = 0;
                    i++;
                }
            } else if (counts[i + 1] == counts[i]) {
                equalCount += 1;
            } else {
                minCount += counts[i] - counts[i + 1];
                counts[i] = counts[i + 1];
                equalCount += 1;
            }
        }
        if (equalCount != 0 && counts[0] != 0) {
            int j, tmp = counts[0];
            for (j = 1; j <= equalCount && tmp != 0; j++) {
                minCount += j;
                tmp--;
            }
            for (; j <= equalCount; j++) {
                minCount += counts[0];
            }
        }
        return minCount;
    }

    /**
     * Improvement in accumulating the frequencies in the array
     */
    public int minDeletions_v2(String s) {
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }

        Arrays.sort(counts);
        System.out.println(Arrays.toString(counts));
        int minCount = 0, equalCount = 0;
        for (int i = counts.length - 2; i >= 0; i--) {
            if (counts[i + 1] > counts[i]) {
                if (equalCount != 0) {
                    int j, tmp = counts[i + 1];
                    for (j = 1; j <= equalCount && tmp != 0; j++) {
                        minCount += j;
                        tmp--;
                    }
                    for (; j <= equalCount; j++) {
                        minCount += counts[i + 1];
                    }
                    counts[i + 1] = tmp;
                    equalCount = 0;
                    i++;
                }
            } else if (counts[i + 1] == counts[i]) {
                equalCount += 1;
            } else {
                minCount += counts[i] - counts[i + 1];
                counts[i] = counts[i + 1];
                equalCount += 1;
            }
        }
        if (equalCount != 0 && counts[0] != 0) {
            int j, tmp = counts[0];
            for (j = 1; j <= equalCount && tmp != 0; j++) {
                minCount += j;
                tmp--;
            }
            for (; j <= equalCount; j++) {
                minCount += counts[0];
            }
        }
        return minCount;
    }
}