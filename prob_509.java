/**
 * 509. Fibonacci Number
 * <p>
 * Easy
 * <p>
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 * <p>
 * F(0) = 0, F(1) = 1
 * <p>
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * <p>
 * Given n, calculate F(n).
 */
public class prob_509 {

    public static void main(String[] args) {
        Solution_509 solution = new Solution_509();
        System.out.println(solution.fib(3));
    }

}

class Solution_509 {

    /**
     * Dynamic Programming
     * Time Complexity - O(N), Space Complexity - O(1)
     */
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int[] fib = new int[2];
        fib[1] = 1;
        int tmp;
        for (int i = 2; i <= n; i++) {
            tmp = fib[1];
            fib[1] += fib[0];
            fib[0] = tmp;
        }
        return fib[1];
    }

}