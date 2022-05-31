/**
 * 1461. Check If a String Contains All Binary Codes of Size K
 * Medium
 * Given a binary string s and an integer k, return true if every binary code of length k is a substring of s. Otherwise, return false.
 */
public class prob_1461 {
    public static void main(String[] args) {
        Solution_1461 solution = new Solution_1461();
        String s = "00110110";
        int k = 2;
        System.out.println(solution.hasAllCodes(s,k));
    }
}

/**
 * Solution using binary code matrix: All combinations of the bits 2*2*2*...*2 - 'k' times
 * Time Complexity - O(n) where n is the length of the string
 * Space Complexity - O(2^k) where k is the binary code length
 */
class Solution_1461 {
    public boolean hasAllCodes(String s, int k) {
        if(s.length() < k) return false;
        int[] binaryCodes = new int[(int)Math.pow(2,k)];
        StringBuilder stringBuilder = new StringBuilder();
        int pow = 1, tmp = 0;
        for (int i = s.length()-1; i > s.length()-k-1; i--) {
            stringBuilder.insert(0, s.charAt(i));
            if(s.charAt(i) == '1') tmp += pow;
            pow = pow << 1;
        }
        pow = pow >> 1;
        binaryCodes[tmp] = 1;
        for (int i = s.length()-k-1; i >= 0; i--) {
            stringBuilder.deleteCharAt(k-1);
            stringBuilder.insert(0, s.charAt(i));
            System.out.println(stringBuilder);
            tmp = tmp >> 1;
            if(s.charAt(i) == '1') tmp += pow;
            binaryCodes[tmp] = 1;
        }
        for (int binaryCode : binaryCodes) {
            if (binaryCode == 0) return false;
        }
        return true;
    }
}
