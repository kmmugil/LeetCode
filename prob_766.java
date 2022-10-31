/**
 * 766. Toeplitz Matrix
 * <p>
 * Easy
 * <p>
 * Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.
 * <p>
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * <p>
 * n == matrix[i].length
 * <p>
 * 1 <= m, n <= 20
 * <p>
 * 0 <= matrix[i][j] <= 99
 * <p>
 * Follow up:
 * <p>
 * What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
 * <p>
 * What if the matrix is so large that you can only load up a partial row into the memory at once?
 * <p>
 * Here the main operation is column wise comparison of the array contents, thus loading the array partially up until a few columns basically forming grids in the array
 * would solve this issue
 */
public class prob_766 {

    public static void main(String[] args) {
        Solution_766 solution = new Solution_766();
        int[][] matrix = {{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}};
//        int[][] matrix = {{1, 2}, {2, 2}};
        System.out.println(solution.isToeplitzMatrix(matrix));
    }

}

/**
 * Solution using array traversal
 * <p>
 * Time Complexity - O(M*N), Space Complexity - O(M*N)
 * <p>
 * Where M and N are the dimensions of the array
 */
class Solution_766 {

    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = matrix.length - 1; i >= 1; i--) {
            for (int j = matrix[i].length - 1; j >= 1; j--) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

}