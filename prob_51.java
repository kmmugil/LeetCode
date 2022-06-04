import java.util.ArrayList;
import java.util.List;

/**
 * 51. N-Queens
 * Hard
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 */
public class prob_51 {
    public static void main(String[] args) {
        Solution_51 solution = new Solution_51();
        List<List<String>> result = solution.solveNQueens(5);
        for (List<String> stringList : result) {
            for (String s : stringList) {
                System.out.println(s);
            }
            System.out.println("------------------------");
        }
    }
}

/**
 * Solution using backtracking
 * Time Complexity - O(N!), Space Complexity - O(n^2)
 *
 * Understanding the time complexity is important
 * Brute-force time complexity - initially we have N^2 options, N^2 - 1, N^2 - 2 .... N^2 - N => which results in (N^2N) - N^2 ~ O(N^N)
 * Simple way: Since we iterate row wise - initially we have N options, then N-1 in the second row, N-2, N-3 ..... 1 => O(N!)
 * Recursive way: T(N) = n*T(N-1) + O(n^2) ~ O(N!) < O(N^N)
 * n*T(N-1) is the row wise iteration in the simple explanation
 * O(n^2) is the safe check reference we do to identify safe squares to place the queen
 *
 * It is clear that once a queen is placed in a particular row, no other queen can be placed in that row
 * So we iterate through the rows, and check if the previously placed queens hit the current column being evaluated in the row (checking if safe to place the queen)
 * Safe Check - Done by checking if the difference between rows and columns are equal
 */
class Solution_51 {
    private int n;
    private String emptyRank;
    private List<Integer> ranks;
    private List<List<String>> result;
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        emptyRank = ".".repeat(Math.max(0, n));
        ranks = new ArrayList<>();
        result = new ArrayList<>();
        this.assignQueens(0);
        return result;
    }

    private void assignQueens(int i) {
        if(i >= n) {
            List<String> rankList = new ArrayList<>();
            StringBuilder rankString;
            for (Integer rank : ranks) {
                rankString = new StringBuilder(emptyRank);
                rankString.replace(rank, rank+1, "Q");
                rankList.add(String.valueOf(rankString));
            }
            result.add(rankList);
            return;
        }
        int k;
        for (int j = 0; j < n; j++) {
            for (k = 0; k < ranks.size(); k++) {
                if(j == ranks.get(k) || (i-k) == Math.abs(j-ranks.get(k))) break;
            }
            if(k == ranks.size()) {
                ranks.add(j);
                this.assignQueens(i+1);
                ranks.remove(ranks.size()-1);
            }
        }
    }
}
