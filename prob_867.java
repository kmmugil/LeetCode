import java.util.Arrays;

/**
 * 867. Transpose Matrix
 * Easy
 * Given a 2D integer array matrix, return the transpose of matrix.
 *
 * The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column indices.
 */
public class prob_867 {
    public static void main(String[] args) {
        Solution_867 solution = new Solution_867();
        int[][] matrix = {{1},{4}};
        matrix = solution.transpose(matrix);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
}

/**
 * Solution using simple matrix assignment
 * Time Complexity - O(n^2), Space Complexity - O(1) (not considering the space required to return the result)
 */
class Solution_867 {
    public int[][] transpose(int[][] matrix) {
        int[][] transpose = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }
        return transpose;
    }
}
