import java.util.ArrayList;
import java.util.List;

/**
 * 1332. Remove Palindromic Subsequences
 * Easy
 * You are given a string s consisting only of letters 'a' and 'b'. In a single step you can remove one palindromic subsequence from s.
 *
 * Return the minimum number of steps to make the given string empty.
 *
 * A string is a subsequence of a given string if it is generated by deleting some characters of a given string without changing its order.
 * Note that a subsequence does not necessarily need to be contiguous.
 *
 * A string is called palindrome if is one that reads the same backward as well as forward.
 */
public class prob_1332 {
    public static void main(String[] args) {
        Solution_1332 solution = new Solution_1332();
        String s = "abcaba";
        String s1 = "abbaaaab";
//        System.out.println(solution.removePalindromeSub(s));
        System.out.println(solution.removePalindromeSub_v2(s));
//        System.out.println(solution.removeLongestPalindromeSub(s1));
    }
}

/**
 * Solution using array traversal - front and back
 * Don't overthink this question - key point is that there are only 2 letters possible 'a', 'b'
 * Time Complexity - O(n), Space Complexity - O(n)
 */
class Solution_1332 {
    public int removePalindromeSub(String s) {
        int i, j;
        for (i = 0, j = s.length()-1; i < j; i++, j--) {
            if(s.charAt(i) == s.charAt(j)) continue;
            return 2;
        }
        return 1;
    }

    public int removePalindromeSub_v2(String s) {
        int[] count = new int[s.length()*2-1];
        int min = Integer.MAX_VALUE;
        String[] results = this.removeLongestPalindromeSub(s);
        for (int i = 0; i < results.length; i++) {
            if(results[i].length() == 0) return count[i]+1;
            count[i]++;
            min = Math.min(min, count[i]+this.removePalindromeSub_v2(results[i]));
        }
        return min;
    }

    /**
     * This method is good for finding the longest palindrome (of more than just 2 letters)
     * But removing the longest palindrome doesn't guarantee that the remaining string can be reduced to "" in the
     * shortest # of steps, flawed assumption
     * Ex: abbaaaab
     * longest palindrome subsequence - baaaab, remaining string - ab, it takes two more reductions to reduce to "" - Total=3
     * But reducing like aaaaaa and bbb gives the least Total possible = 2
     */
    public String[] removeLongestPalindromeSub(String s) {
        String[] strings = new String[s.length()*2-1];
        int min = Integer.MAX_VALUE, k = 0;
        String[] lrList;
        for (int i = 0; i < s.length()*2-1; i++) {
            lrList =  this.removeLongestPalindromeSubMid(s, (i/2)-1+(i%2), (i/2)+1);
            strings[i] = lrList[0]+lrList[1];
            System.out.println("FINAL: "+i+", "+strings[i]);
            min = Math.min(min, strings[i].length());
            if(min == strings[i].length()) k = i;
        }
        System.out.println(strings[k]);
        return strings;
    }

    private String[] removeLongestPalindromeSubMid(String s, int i, int j) {
        if(i < 0) {
            if(j == s.length()) return new String[]{"", ""};
            else return new String[]{"", s.substring(j)};
        } else {
            if( j == s.length()) return new String[]{s.substring(0, i + 1), ""};
        }
        int k, eCount, iCount;
        String[] elrList, ilrList;
        StringBuilder stringBuilder;
        char[] sArray = s.toCharArray();
        elrList = this.removeLongestPalindromeSubMid(s, i-1, j);
        eCount = elrList[0].length()+elrList[1].length()+1;
        for (k = j; k < s.length(); k++) {
            if(sArray[k] == sArray[i]) break;
        }
        if(k != s.length()) {
            ilrList = this.removeLongestPalindromeSubMid(s, i-1, k+1);
            iCount = ilrList[0].length()+ilrList[1].length()+k-j;
        } else {
            ilrList = new String[2];
            ilrList[0] = s.substring(0, i+1);
            ilrList[1] = "";
            iCount = ilrList[0].length()+s.length()-j;
        }
        if(iCount < eCount) {
            stringBuilder = new StringBuilder(ilrList[1]);
            stringBuilder.insert(0, s.substring(j,k));
            ilrList[1] = stringBuilder.toString();
            System.out.println("ILR: "+ilrList[0]+ilrList[1]+", ij: "+i+","+j);
            return ilrList;
        } else {
            stringBuilder = new StringBuilder(elrList[0]);
            stringBuilder.append(sArray[i]);
            elrList[0] = stringBuilder.toString();
            System.out.println("ELR: "+elrList[0]+elrList[1]);
            return elrList;
        }
    }

}
