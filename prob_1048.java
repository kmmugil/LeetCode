import java.util.HashMap;

/**
 * 1048. Longest String Chain
 * Medium
 * You are given an array of words where each word consists of lowercase English letters.
 *
 * wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.
 *
 * For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
 * A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1,
 * where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.
 *
 * Return the length of the longest possible word chain with words chosen from the given list of words.
 */
public class prob_1048 {
    public static void main(String[] args) {
        Solution_1048 solution = new Solution_1048();
//        String[] words = new String[] {"a","b","ba","bca","bda","bdca"};
        String[] words = new String[] {"xbc","pcxbcf","xb","cxbc","pcxbc"};
        System.out.println(solution.longestStrChain(words));
    }
}

/**
 * Solution using Dynamic Programming and Recursion
 * Although the solution without recursion might be faster but for which sorting has to be done on the length of the words
 * Time Complexity - O(N*MM), Space Complexity - O(N)
 * Where N is the # of words and M is the max length of the word (M for string concatenation, M for length of the word)
 */
class Solution_1048 {

    private HashMap<String, Integer> hashMap;
    public int longestStrChain(String[] words) {
        this.hashMap = new HashMap<>();
        for (String word : words) {
            hashMap.put(word, -1);
        }
        int max = Integer.MIN_VALUE;
        for (String word: words) {
            max = Math.max(max, this.longestStrChain(word));
        }
        return max;
    }

    public int longestStrChain(String word) {
        Integer longestChain = this.hashMap.get(word);
        if(longestChain == null) return -1;
        if(longestChain != -1) return longestChain;
        longestChain = 1;
        for (int i = 0; i < word.length()-1; i++) {
            longestChain = Math.max(longestChain, 1+this.longestStrChain(word.substring(0,i)+word.substring(i+1)));
        }
        longestChain = Math.max(longestChain, 1+this.longestStrChain(word.substring(0,word.length()-1)));
        this.hashMap.put(word, longestChain);
        return longestChain;
    }

}
