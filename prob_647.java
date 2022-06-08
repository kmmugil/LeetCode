/**
 * 647. Palindromic Substrings
 * Given a string s, return the number of palindromic substrings in it.
 *
 * A string is a palindrome when it reads the same backward as forward.
 *
 * A substring is a contiguous sequence of characters within the string.
 */
public class prob_647 {
    public static void main(String[] args) {
        Solution_647 solution = new Solution_647();
//        String s = "abc";
        String s = "aaa";
        String s1 = "aabb";
        System.out.println(solution.countSubstrings(s1));
    }
}

/**
 * Solution using Dynamic Programming
 * Capturing all palindromic substrings with the same mid-point iteratively. This is somewhat dynamic, because we are reusing the smaller palindromic substring to find the
 * larger one
 * There are 2n-1 midpoints and for each midpoint we likely do O(n/2) max computation, for each start-end combination of indexes, the palindromes are identified in O(1) time
 * complexity
 * Although the total time complexity in the worst case (all repeating letters) is O(n^2)
 */
class Solution_647 {
    public int countSubstrings(String s) {
        char[] string = s.toCharArray();
        int count = 0;
        for (int k = 0; k <= 2 * string.length - 2; k++) {
            count += this.recurseMidPoints(string, k);
        }
        return count;
    }

    private int recurseMidPoints(char[] string, int k) {
        int count = 0;
        int i, j;
        i = k/2; j = (k/2)+1;
        StringBuilder stringBuilder = new StringBuilder();
        if(k%2 == 0) {
            count++;
            stringBuilder.append(string[i]);
            i = i-1;
        }
        while(i >= 0 && j < string.length) {
            if(string[i] != string[j]) break;
            count++;
            stringBuilder.insert(0, string[i]);
            stringBuilder.append(string[j]);
            i--; j++;
        }
        System.out.println(stringBuilder+": "+count);
        return count;
    }
}
