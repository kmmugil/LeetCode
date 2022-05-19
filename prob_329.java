import java.util.Arrays;

/**
 * 329. Longest Increasing Path in a Matrix
 *
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 */
public class prob_329 {
    public static void main(String[] args) {
        Solution_329 solution = new Solution_329();
        int[][] matrix = new int[][] {{9,9,4},{6,6,8},{2,1,1}};
        System.out.println(solution.longestIncreasingPath(matrix));
    }
}

/**
 * Solution using Dynamic Programming
 */
class Solution_329 {
    int[][] lipMatrix;
    int[][] directions = new int[][] {{-1,0}, {0,1}, {0,-1}, {1,0}};
    public int longestIncreasingPath(int[][] matrix) {
        lipMatrix = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int[] ints : lipMatrix) {
            Arrays.fill(ints, -1);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                max = Math.max(max, this.recurseLongestIncreasingPath(matrix, i, j));
            }
        }
        return max;
    }

    private int recurseLongestIncreasingPath(int[][] matrix, int i, int j) {
        if(i >= matrix.length || j >= matrix[0].length) return 0;
        if(lipMatrix[i][j] != -1) return lipMatrix[i][j];
        lipMatrix[i][j] = 1;
        for (int[] direction : directions) {
            int iTmp = i+direction[0];
            int jTmp = j+direction[1];
            if(iTmp >= 0 && jTmp >= 0 && iTmp < matrix.length && jTmp < matrix[0].length && matrix[i][j] < matrix[iTmp][jTmp]) {
                lipMatrix[i][j] = Math.max(lipMatrix[i][j], this.recurseLongestIncreasingPath(matrix, iTmp, jTmp)+1);
            }
        }
        return lipMatrix[i][j];
    }

}
