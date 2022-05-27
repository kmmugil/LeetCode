/**
 * 1342. Number of Steps to Reduce a Number to Zero
 * Easy
 * Given an integer num, return the number of steps to reduce it to zero.
 *
 * In one step, if the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.
 */
public class prob_1342 {
    public static void main(String[] args) {
        Solution_1342 solution = new Solution_1342();
        int num = 123;
        System.out.println(solution.numberOfSteps_v2(num));
    }
}

class Solution_1342 {
    /**
     * Simple iteration through the number will give optimal answer
     * Time Complexity - O(# of bits), Space Complexity - O(1)
     */
    public int numberOfSteps(int num) {
        int count = 0;
        while(num != 0) {
            if(num % 2 == 0) num/=2;
            else num -= 1;
            count++;
        }
        return count;
    }

    /**
     * Solution using bitwise right shift and bitwise and operations to get answer
     * Time Complexity - O(# of bits), Space Complexity - O(1)
     */
    public int numberOfSteps_v2(int num) {
        int count = 0;
        while(num != 0) {
            num = (num & 1) == 1 ? num - 1 : num >> 1;
            count++;
        }
        return count;
    }
}
