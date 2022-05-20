import java.util.Arrays;

/**
 * 62. Unique Paths
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 */
public class prob_62 {
    public static void main(String[] args) {
        Solution_62 solution = new Solution_62();
        System.out.println(solution.uniquePaths_v2(51,9));
    }
}

class Solution_62 {
    int m, n;
    int[][] pathCountMatrix, directions = new int[][] {{0,1}, {1,0}};

    /**
     * Solution using Dynamic Programming
     */
    public int uniquePaths(int m, int n) {
        this.m = m; this.n = n;
        pathCountMatrix = new int[m][n];
        for (int[] countMatrix : pathCountMatrix) {
            Arrays.fill(countMatrix, -1);
        }
        pathCountMatrix[m-1][n-1] = 1;
        int tmp = this.recurseUniquePaths(0,0);
        for (int[] countMatrix : pathCountMatrix) {
            System.out.println(Arrays.toString(countMatrix));
        }
        return tmp;
    }

    /**
     * Solution using Probability
     * Since there are no obstacles, we need to take m-1 steps down the grid, n-1 steps across the grid in any order
     * (m-1 black balls, n-1 red balls) - permutation (Place the walls in the m+n-2 places, no arrangement is necessary
     */
    public int uniquePaths_v2(int m, int n) {
        int places = m+n-2;
        if(m != Math.min(m,n)) {
            m += n;
            n = m-n;
            m = m-n;
        }
        long ans = 1;
        for (int i = 1; i < m; i++) {
            ans = (ans*places) /i;
            places--;
        }
        return (int) ans;
    }

    private int recurseUniquePaths(int i, int j) {
        if(i >= m || j >= n) return 0;
        if(pathCountMatrix[i][j] != -1) return pathCountMatrix[i][j];
        pathCountMatrix[i][j] = 0;
        for (int[] direction : directions) {
            pathCountMatrix[i][j] += this.recurseUniquePaths(i+direction[0], j+direction[1]);
        }
        return pathCountMatrix[i][j];
    }
}
