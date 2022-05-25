import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 354. Russian Doll Envelopes
 * Hard
 * You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
 *
 * One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.
 *
 * Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
 *
 * Note: You cannot rotate an envelope.
 */
public class prob_354 {
    public static void main(String[] args) {
        Solution_354 solution = new Solution_354();
//        int[][] envelopes = new int[][] {{5,4},{6,4},{6,7},{2,3}};
//        int[][] envelopes = new int[][] {{1,15},{7,18},{7,6},{7,100},{2,200},{17,30},{17,45},{3,5},{7,8},{3,6},{3,10},{7,20},{17,3},{17,45}};
        int[][] envelopes = new int[][] {{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{6,360},{7,380}};
        System.out.println(solution.maxEnvelopes_v3(envelopes));
    }
}

class Solution_354 {
    private int[] sortedX, sortedY, dimX, dimY;
    private int[][] lcss;

    /**
     * Solution using sorting and dynamic programming
     * First sort both X and Y dimensions, then find the longest common subsequence through DP
     */
    public int maxEnvelopes(int[][] envelopes) {
        sortedX = new int[envelopes.length];
        sortedY = new int[envelopes.length];
        dimX = new int[envelopes.length];
        dimY = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            sortedX[i] = sortedY[i] = i;
            dimX[i] = envelopes[i][0];
            dimY[i] = envelopes[i][1];
        }
        boolean flag = true; int tmp;
        while(flag) {
            flag = false;
            for (int i = 0; i < envelopes.length-1; i++) {
                if(dimX[i] > dimX[i+1]) {
                    tmp = sortedX[i];
                    sortedX[i] = sortedX[i+1];
                    sortedX[i+1] = tmp;
                    tmp = dimX[i];
                    dimX[i] = dimX[i+1];
                    dimX[i+1] = tmp;
                    flag = true;
                }
                if(dimY[i] > dimY[i+1]) {
                    tmp = sortedY[i];
                    sortedY[i] = sortedY[i+1];
                    sortedY[i+1] = tmp;
                    tmp = dimY[i];
                    dimY[i] = dimY[i+1];
                    dimY[i+1] = tmp;
                    flag = true;
                }
            }
        }
        lcss = new int[envelopes.length][envelopes.length];
        for (int[] ints : lcss) {
            Arrays.fill(ints, -1);
        }
        System.out.println(Arrays.toString(sortedX));
        System.out.println(Arrays.toString(sortedY));
        System.out.println(Arrays.toString(dimX));
        System.out.println(Arrays.toString(dimY));
        int result =  this.findLCSS(0,0, Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (int[] ints : lcss) {
            System.out.println(Arrays.toString(ints));
        }
        return result;
    }

    /**
     * Here the sub-problem computed cannot be reused since the values included depend upon the minX and minY values
     * And these values could change from one sub-problem to another, thus storing it would result in error
     * Thus all the advantage of memoization is gone
     */
    private int findLCSS(int i, int j, int minX, int minY) {
        if(i >= lcss.length || j >= lcss.length) return 0;
//        if(lcss[i][j] != -1) return lcss[i][j];
        lcss[i][j] = 0;
        if(sortedX[i] == sortedY[j]) {
            if(dimX[i] == minX || dimY[j] == minY) {
                lcss[i][j] = Math.max(lcss[i][j], this.findLCSS(i+1,j+1, minX, minY));
            } else {
                lcss[i][j] = Math.max(lcss[i][j], this.findLCSS(i+1, j+1, dimX[i], dimY[j])+1);
                return lcss[i][j];
            }
        }
        lcss[i][j] = Math.max(lcss[i][j], this.findLCSS(i, j+1, minX, minY));
        lcss[i][j] = Math.max(lcss[i][j], this.findLCSS(i+1, j, minX, minY));
        return lcss[i][j];
    }

    /**
     * Solution using Dynamic Programming
     * Here the sub-problems are the max envelopes which can be enveloped starting at a particular index
     * Note that this solution relies on sorting the letters first
     * TLE
     */
    public int maxEnvelopes_v2(int[][] envelopes) {
        Comparator<int[]> envelopeComparator = this.generateEnvelopeComparator();
        Arrays.sort(envelopes, 0, envelopes.length, envelopeComparator);
        for (int[] envelope : envelopes) {
            System.out.println(Arrays.toString(envelope));
        }
        int[] memo = new int[envelopes.length];
        int minX, minY, max = 0;
        for (int i = envelopes.length - 1; i >= 0; i--) {
            minX = envelopes[i][0]; minY = envelopes[i][1];
            for (int j = i; j < envelopes.length; j++) {
                if(envelopes[j][0] > minX && envelopes[j][1] > minY) {
                    memo[i] = Math.max(memo[i], memo[j]);
                }
            }
            memo[i]++;
            max = Math.max(max, memo[i]);
        }
        return max;
    }

    /**
     * Solution using the longest increasing subsequence idea
     */
    public int maxEnvelopes_v3(int[][] envelopes) {
        Comparator<int[]> envelopeComparator = this.generateEnvelopeComparator();
        Arrays.sort(envelopes, 0, envelopes.length, envelopeComparator);
        for (int[] envelope : envelopes) {
            System.out.println(Arrays.toString(envelope));
        }
        int[] memo = new int[envelopes.length];
        int count = 1;
        memo[0] = envelopes[0][1];
        for (int i = 1; i < envelopes.length; i++) {
            if(envelopes[i][1] > memo[count-1]) {
                memo[count] = envelopes[i][1];
                count++;
            } else {
                int left = 0, right = count-1, middle;
                while(left < right) {
                    middle = left + (right-left)/2;
                    if(memo[middle] < envelopes[i][1]) left = middle+1;
                    else right = middle;
                }
//                int idx = Arrays.binarySearch(memo, 0, count, envelopes[i][1]);
//                idx = idx < 0 ? -(idx+1) : idx;
//                memo[idx] = envelopes[i][1];
                memo[left] = envelopes[i][1];
            }
            System.out.println(Arrays.toString(memo));
        }
        return count;
    }

    private Comparator<int[]> generateEnvelopeComparator() {
        return Comparator.comparing(ints -> ints, (e1, e2) -> e1[0] == e2[0] ? e2[1]-e1[1] : e1[0]-e2[0]);
    }

}
