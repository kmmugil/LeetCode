import java.util.Arrays;

/**
 * 583. Delete Operation for Two Strings
 * Medium
 * Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
 *
 * In one step, you can delete exactly one character in either string.
 */
public class prob_583 {
    public static void main(String[] args) {
        Solution_583 solution = new Solution_583();
//        String word1 = "sea", word2 = "eat";
        String word1 = "leetcode", word2 = "etco";
        System.out.println(solution.minDistance(word1, word2));
    }
}

/**
 * Solution using Dynamic Programming - find the longest common subsequence and then use that to calculate the # of deletions required
 * Time Complexity - O(m*n), Space Complexity - O(m*n)
 * Where m,n are the lengths of the strings given
 * Think of an algorithm which uses only O(n) space! and also one which uses dynamic programming without memoization (and recursion)
 */
class Solution_583 {
    String word1, word2;
    int[][] store;
    public int minDistance(String word1, String word2) {
        this.word1 = word1; this.word2 = word2;
        store = new int[word1.length()][word2.length()];
        for (int[] ints : store) {
            Arrays.fill(ints, Integer.MIN_VALUE);
        }
        this.findMaxCommonString(0, 0);
        return word1.length()+word2.length()-2*this.store[0][0];
    }

    private int findMaxCommonString(int i, int j) {
        if(i >= this.store.length || j >= this.store[0].length) return 0;
        if(this.store[i][j] != Integer.MIN_VALUE) return this.store[i][j];
        int max = Integer.MIN_VALUE;
        if(this.word1.charAt(i) == this.word2.charAt(j)) max = 1+this.findMaxCommonString(i+1, j+1);
        else {
            max = Math.max(max, this.findMaxCommonString(i, j+1));
            max = Math.max(max, this.findMaxCommonString(i+1, j));
        }
        this.store[i][j] = max;
        return max;
    }

}
