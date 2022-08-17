import java.util.HashSet;

/**
 * 804. Unique Morse Code Words
 * Easy
 * <p>
 * <p>
 * International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes, as follows:
 * <p>
 * 'a' maps to ".-",
 * <p>
 * 'b' maps to "-...",
 * <p>
 * 'c' maps to "-.-.", and so on.
 * <p>
 * For convenience, the full table for the 26 letters of the English alphabet is given below:
 * <p>
 * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
 * <p>
 * Given an array of strings words where each word can be written as a concatenation of the Morse code of each letter.
 * <p>
 * For example, "cab" can be written as "-.-..--...", which is the concatenation of "-.-.", ".-", and "-...". We will call such a concatenation the transformation of a word.
 * <p>
 * Return the number of different transformations among all words we have.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 100
 * <p>
 * 1 <= words[i].length <= 12
 * <p>
 * words[i] consists of lowercase English letters.
 */
public class prob_804 {

    public static void main(String[] args) {
        Solution_804 solution = new Solution_804();
        String[] words = {"gin", "zen", "gig", "msg"};
        System.out.println(solution.uniqueMorseRepresentations(words));
    }

}

/**
 * Solution using hashing
 * <p>
 * Time Complexity - O(N), Space Complexity - O(N)
 */
class Solution_804 {

    private final String[] morseCode = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...",
            "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    public int uniqueMorseRepresentations(String[] words) {
        HashSet<String> uniqueTransformations = new HashSet<>();
        for (String word : words) {
            StringBuilder transformation = new StringBuilder();
            for (char c : word.toCharArray()) {
                transformation.append(this.morseCode[c - 'a']);
            }
            uniqueTransformations.add(transformation.toString());
        }
        return uniqueTransformations.size();
    }

}