import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 474. Ones and Zeroes
 * You are given an array of binary strings strs and two integers m and n.
 *
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 *
 * A set x is a subset of a set y if all elements of x are also elements of y.
 */
public class prob_474 {
    public static void main(String[] args) {
        Solution_474 solution = new Solution_474();
        String[] strs = {"10","0001","111001","1","0"};
        System.out.println(solution.findMaxForm_v2(strs,5,3));
//        String[] strs = {"10","0","1"};
//        System.out.println(solution.findMaxForm(strs,1,1));
    }
}

class Solution_474 {
    private int i = 0;
    private List<int[][]> store;
    private int[][] strings;

    /**
     * Solution using Dynamic Programming - memoization (top-down) approach
     */
    public int findMaxForm(String[] strs, int m, int n) {
        strings = new int[strs.length][2];
        for (int[] string : strings) {
            string[0] = 0;
            string[1] = 0;
        }
        char[] stringChars;
        for (int j = 0; j < strs.length; j++) {
            stringChars = strs[j].toCharArray();
            for (char stringChar : stringChars) {
                if(stringChar == '0') strings[j][0]++;
                else strings[j][1]++;
            }
        }
        return this.recurseFindMaxForm(m, n);
    }

    private int recurseFindMaxForm(int m, int n) {
        if(store == null) {
            store = new ArrayList<>();
            int[][] tmp;
            for (int j = 0; j < strings.length; j++) {
                tmp = new int[m+1][n+1];
                for (int[] ints : tmp) {
                    Arrays.fill(ints, -1);
                }
                store.add(tmp);
            }
        }
        if(m < 0 || n < 0) return -1;
        if(i >= strings.length) return 0;
        if(store.get(i)[m][n] != -1) return store.get(i)[m][n];
        int tmpM = m - strings[i][0], tmpN = n - strings[i][1];
        int included_count, excluded_count;
        i++;
        included_count = this.recurseFindMaxForm(tmpM, tmpN);
        included_count = included_count == -1 ? -1 : included_count+1;
        excluded_count = this.recurseFindMaxForm(m, n);
        i--;
        store.get(i)[m][n] = Math.max(included_count, excluded_count);
        return store.get(i)[m][n];
    }

    /**
     * Solution using Dynamic Programming - tabulation (bottom-up) approach
     * Not to be confused with the for loop iterating down (this is imperative since on addition of any string, the available occupancy decreases
     * Here bottom-up means considering that the string array contains one string after another
     * Instead of reducing for a full string array to just one string in the array
     */
    public int findMaxForm_v2(String[] strs, int m, int n) {
        int[][] tabulation = new int[m + 1][n + 1];
        for (String str : strs) {
            int[] count = new int[2];
            for (char c : str.toCharArray()) {
                if (c == '0') count[0]++;
                else count[1]++;
            }
            for (int j = m; j >= count[0]; j--) {
                for (int k = n; k >= count[1]; k--) {
                    tabulation[j][k] = Math.max(tabulation[j][k], 1+ tabulation[j-count[0]][k-count[1]]);
                }
            }
        }
        return tabulation[m][n];
    }

}
