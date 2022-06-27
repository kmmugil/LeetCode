/**
 * 1689. Partitioning Into Minimum Number Of Deci-Binary Numbers
 * Medium
 * A decimal number is called deci-binary if each of its digits is either 0 or 1 without any leading zeros.
 * For example, 101 and 1100 are deci-binary, while 112 and 3001 are not.
 *
 * Given a string n that represents a positive decimal integer, return the minimum number of positive deci-binary numbers needed so that they sum up to n.
 */
public class prob_1689 {
    public static void main(String[] args) {
        Solution_1689 solution = new Solution_1689();
        String n = "32";
        System.out.println(solution.minPartitions(n));
    }
}

/**
 * Solution using string integer parsing
 * Time Complexity - O(n), Space Complexity - O(1)
 * Observation - Any deci-binary number can be constructed using the largest digit in the string
 * Because that is the largest # of ones required
 */
class Solution_1689 {
    public int minPartitions(String n) {
        int max = 1;
        for (char c : n.toCharArray()) {
            max = Math.max(max, c-'0');
            if(max == 9) break;
        }
        return max;
    }
}
