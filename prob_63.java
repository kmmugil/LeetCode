import java.util.Arrays;

/**
 * 63. Unique Paths II
 * You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m-1][n-1]). The robot can only move either down or right at any point in time.
 *
 * An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.
 *
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The testcases are generated so that the answer will be less than or equal to 2 * 10^9.
 */
public class prob_63 {
    public static void main(String[] args) {
        Solution_63 solution = new Solution_63();
//        int[][] obstacleGrid = new int[][] {{0,0,0},{0,1,0},{0,0,0}};
        int[][] obstacleGrid  = new int[][] {{0,1},{0,0}};
        System.out.println(solution.uniquePathsWithObstacles(obstacleGrid));
    }
}

/**
 * Solution using Dynamic Programming (memoization)
 */
class Solution_63 {
    int[][] pathCountMatrix;
    int[][] obstacleGrid;
    int m, n;
    int[][] directions = new int[][] {{1,0}, {0,1}};
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1) return 0;
        this.obstacleGrid = obstacleGrid;
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;
        pathCountMatrix = new int[m][n];
        for (int[] countMatrix : pathCountMatrix) {
            Arrays.fill(countMatrix, -1);
        }
        pathCountMatrix[m-1][n-1] = (obstacleGrid[m-1][n-1]+1)%2;
        int ret = this.recurseUniquePathsWithObstacles(0, 0);
        for (int[] countMatrix : pathCountMatrix) {
            System.out.println(Arrays.toString(countMatrix));
        }
        return ret;
    }

    private int recurseUniquePathsWithObstacles(int i, int j) {
        if(i >= m || j >= n) return 0;
        if(pathCountMatrix[i][j] != -1) return pathCountMatrix[i][j];
        pathCountMatrix[i][j] = 0;
        int iTmp, jTmp;
        for (int[] direction : directions) {
            iTmp = i + direction[0];
            jTmp = j + direction[1];
            if(iTmp < obstacleGrid.length && jTmp < obstacleGrid[0].length && obstacleGrid[iTmp][jTmp] == 0) {
                pathCountMatrix[i][j] += this.recurseUniquePathsWithObstacles(iTmp, jTmp);
            }
        }
        return pathCountMatrix[i][j];
    }

}
