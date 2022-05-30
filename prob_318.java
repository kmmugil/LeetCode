/**
 * 318. Maximum Product of Word Lengths

 * Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters.
 * If no such two words exist, return 0.
 */
public class prob_318 {
    public static void main(String[] args) {
        Solution_318 solution = new Solution_318();
        String[] words = {"abcw","baz","foo","bar","xtfn","abcdef"};
        System.out.println(solution.maxProduct(words));
    }
}

/**
 * Solution using matrix multiplication
 * Time Complexity = O(n^2) where n is the number of words
 * Space Complexity = O(n)
 */
class Solution_318 {
    int[][] alphabetMatrix;
    public int maxProduct(String[] words) {
        alphabetMatrix = new int[words.length][26];
        int max = 0;
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                alphabetMatrix[i][c-'a'] = 1;
            }
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = i; j < words.length; j++) {
                if(this.matMult(i, j)) max = Math.max(max, words[i].length()*words[j].length());
            }
        }
        return max;
    }

    private boolean matMult(int i, int j) {
        for (int k = 0; k < 26; k++) {
            if(alphabetMatrix[i][k] == 1 && alphabetMatrix[j][k] == 1) return false;
        }
        return true;
    }

}
