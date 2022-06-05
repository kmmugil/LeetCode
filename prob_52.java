/**
 * 52. N-Queens II
 * Hard
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 */
public class prob_52 {
    public static void main(String[] args) {
        Solution_52 solution = new Solution_52();
        System.out.println(solution.totalNQueens(5));
    }
}

/**
 * Solution using backtracking
 * Time Complexity - O(N!), Space Complexity - O(1)
 * This is alternate version of prob_51 which requires printing out all unique solution of the N-Queens problem
 * The solution presented here is using bit manipulation but follows the same logic as the solution of prob_51
 */
class Solution_52 {
    int count, n;
    public int totalNQueens(int n) {
        int cols = 0, diag = 0, antiDiag = 0;
        this.count = 0;
        this.n = n;
        this.backTrackBoard(0, cols, diag, antiDiag);
        return count;
    }

    private void backTrackBoard(int i, int cols, int diag, int antiDiag) {
        if(i == n) {
            count++;
            return;
        }
        for (int j = 0; j < n; j++) {
            int curr = 1 << j;
            if((curr & cols) != 0) continue;
            if((curr & (diag >> 1)) != 0) continue;
            if((curr & (antiDiag << 1)) != 0) continue;
            this.backTrackBoard(i+1, cols+curr, (diag >> 1) + curr, (antiDiag << 1) + curr);
        }
    }

}
