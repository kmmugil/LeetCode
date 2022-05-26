/**
 * 191. Number of 1 Bits
 * Easy
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
 *
 * Note:
 * Note that in some languages, such as Java, there is no unsigned integer type. In this case, the input will be given as a signed integer type. It should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 3, the input represents the signed integer. -3.
 */
public class prob_191 {
    public static void main(String[] args) {
        Solution_191 solution = new Solution_191();
//        int n = 0b00000000000000000000000000001011;
        int n = 0b11111111111111111111111111111101;
        System.out.println(solution.hammingWeight(n));
    }
}

/**
 * 2's complement Math
 * Negative number representation = (2^32-1)-n+1 (here n is positive i.e., if N = -3, n = 3)
 */

class Solution_191 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0; long tmp;
        System.out.println(n);
//        if(n < 0) {
//            tmp = (long)(Math.pow(2,32)+n);
//            System.out.println(tmp);
//        }
        if(n < 0) {
            tmp = Integer.MAX_VALUE* 2L + 2;
            tmp += n;
        }
        else tmp = n;
        while(tmp != 0) {
            if(tmp%2 == 1) count++;
            tmp /= 2;
        }
        return count;
    }
}
