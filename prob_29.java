/**
 * 29. Divide Two Integers
 * Medium
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 *
 * The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1].
 * For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.
 */
public class prob_29 {
    public static void main(String[] args) {
        Solution_29 solution = new Solution_29();
        int dividend = Integer.MIN_VALUE, divisor = 2;
        System.out.println(solution.divide(dividend, divisor));
    }
}

/**
 * Solution using bit manipulation - multiplying by 2 == left shift by 1
 * Time Complexity - O(logN * logN), Space Complexity - O(1)
 */
class Solution_29 {
    public int divide(int dividend, int divisor) {
        int count = 0;
        long tmpCount, tmpDivs, div, divs, prevDivs = 0;
        if(dividend == 0) return 0;
        if(dividend == Integer.MIN_VALUE) {
            if (divisor == 1) return Integer.MIN_VALUE;
            if (divisor == -1) return Integer.MAX_VALUE;
        }
        div = Math.abs((long) dividend);
        divs = Math.abs((long) divisor);
        while(prevDivs != div && prevDivs+divs <= div) {
            tmpCount = 1;
            tmpDivs = divs;
            while(prevDivs+tmpDivs <= div) {
                tmpCount = tmpCount << 1;
                tmpDivs = tmpDivs << 1;
                System.out.println("tmpDivs: "+tmpDivs);
            }
            prevDivs += tmpDivs >> 1;
            count += tmpCount >> 1;
            System.out.println(prevDivs);
        }
        return (dividend > 0) == (divisor > 0) ? count : -count;
    }
}
