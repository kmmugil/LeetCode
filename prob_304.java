import java.util.Arrays;

/**
 * 304. Range Sum Query 2D - Immutable
 * Medium
 * Given a 2D matrix matrix, handle multiple queries of the following type:
 *
 * Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Implement the NumMatrix class:
 *
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1)
 * and lower right corner (row2, col2).
 */
public class prob_304 {
    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(new int[][]{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}});
        int[][] regions = new int[][] {{2, 1, 4, 3}, {1, 1, 2, 2}, {1, 2, 2, 4}};
        for (int[] region : regions) {
            System.out.println(numMatrix.sumRegion(region[0], region[1], region[2], region[3]));
        }
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 *
 * Solution using row-bound rectangles cumulative sum
 * Time Complexity - O(1), Space Complexity - O(m*n)
 * Where m,n are dimensions of the matrix
 */
class NumMatrix {
    int[][] rcMatrix;

    public NumMatrix(int[][] matrix) {
        rcMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            rcMatrix[i][0] = matrix[i][0];
            for (int j = 1; j < matrix[0].length; j++) {
                rcMatrix[i][j] = rcMatrix[i][j-1] + matrix[i][j];
            }
        }

        for (int i = 1; i < rcMatrix.length; i++) {
            for (int j = 0; j < rcMatrix[0].length; j++) {
                rcMatrix[i][j] += rcMatrix[i-1][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(row1 > rcMatrix.length || row2 > rcMatrix.length || col1 > rcMatrix[0].length || col2 > rcMatrix[0].length) return -1;
        int sum = rcMatrix[row2][col2];
        if(col1-1 >= 0) sum -= rcMatrix[row2][col1-1];
        if(row1-1 >= 0) sum -= rcMatrix[row1-1][col2];
        if(row1-1 >= 0 && col1-1 >= 0) sum += rcMatrix[row1-1][col1-1];
        return sum;
    }

}

/**
 * Solution using row cumulative sum
 * Time Complexity - O(m), Space Complexity - O(m*n)
 * Where m, n are the dimensions of the matrix
 */
class NumMatrix_v1 {
    int[][] rcMatrix;

    public NumMatrix_v1(int[][] matrix) {
        rcMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            rcMatrix[i][0] = matrix[i][0];
            for (int j = 1; j < matrix[0].length; j++) {
                rcMatrix[i][j] = rcMatrix[i][j-1] + matrix[i][j];
            }
        }

        for (int i = 1; i < rcMatrix.length; i++) {
            for (int j = 0; j < rcMatrix[0].length; j++) {
                rcMatrix[i][j] += rcMatrix[i-1][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(row1 > rcMatrix.length || row2 > rcMatrix.length || col1 > rcMatrix[0].length || col2 > rcMatrix[0].length) return -1;
        int sum = rcMatrix[row2][col2];
        if(col1-1 >= 0) sum -= rcMatrix[row2][col1-1];
        if(row1-1 >= 0) sum -= rcMatrix[row1-1][col2];
        if(row1-1 >= 0 && col1-1 >= 0) sum += rcMatrix[row1-1][col1-1];
        return sum;
    }

}
