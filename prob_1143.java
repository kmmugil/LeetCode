import java.util.Arrays;
import java.util.Stack;

/**
 * 1143. Longest Common Subsequence
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 */
public class prob_1143 {
    public static void main(String[] args) {
        Solution_1143 solution = new Solution_1143();
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(solution.longestCommonSubsequence(text1, text2));
    }
}

/**
 * Solution using Dynamic Programming
 */
class Solution_1143 {
    char[] charArray_1, charArray_2;
    int[][] lcssMatrix;

    public int longestCommonSubsequence(String text1, String text2) {
        charArray_1 = text1.toCharArray();
        charArray_2 = text2.toCharArray();
        lcssMatrix = new int[charArray_1.length][charArray_2.length];
        for (int[] matrix : lcssMatrix) {
            Arrays.fill(matrix, -1);
        }
        return this.recurseLongestCommonSubsequence_v2(0, 0);
    }

    public int recurseLongestCommonSubsequence(int i, int j) {
        if(i >= charArray_1.length || j >= charArray_2.length) return 0;
        if(lcssMatrix[i][j] != -1) return lcssMatrix[i][j];
        // case-1 the character at index i is included in the subsequence
        char c = charArray_1[i];
        int max = 0;
        for (int k = j; k < charArray_2.length; k++) {
            if(c == charArray_2[k]) {
                max = Math.max(max, this.recurseLongestCommonSubsequence(i+1, k+1)+1);
                break;
            }
        }
        // case-2 the character at index i is not included in the subsequence
        max = Math.max(max, this.recurseLongestCommonSubsequence(i+1, j));
        lcssMatrix[i][j] = max;
        return max;
    }

    /**
     * This version is faster since running the for loop while matching characters reduces the chance of populating the memoization matrix, thus increasing the runtime.
     * It is always better in dynamic programming to split the problem into sub-parts instead of running the core logic directly
     */
    public int recurseLongestCommonSubsequence_v2(int i, int j) {
        if(i >= charArray_1.length || j >= charArray_2.length) return 0;
        if(lcssMatrix[i][j] != -1) return lcssMatrix[i][j];
        lcssMatrix[i][j] = 0;
        if(charArray_1[i] == charArray_2[j]) {
            lcssMatrix[i][j] = Math.max(lcssMatrix[i][j], this.recurseLongestCommonSubsequence_v2(i+1, j+1)+1);
        } else {
            lcssMatrix[i][j] = Math.max(lcssMatrix[i][j], this.recurseLongestCommonSubsequence_v2(i+1, j));
            lcssMatrix[i][j] = Math.max(lcssMatrix[i][j], this.recurseLongestCommonSubsequence_v2(i, j+1));
        }
        return lcssMatrix[i][j];
    }
}
