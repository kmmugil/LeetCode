import java.util.Hashtable;

/**
 * 242. Valid Anagram
 * <p>
 * Easy
 * <p>
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 */
public class prob_242 {

    public static void main(String[] args) {
        Solution_242 solution = new Solution_242();
        String s = "anagram", t = "nagaram";
        System.out.println(solution.isAnagram_v2(s, t));
    }

}

class Solution_242 {

    /**
     * Solution using hash table data-structure
     * <p>
     * Time Complexity - O(N), Space Complexity - O(1)
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        Hashtable<Character, Integer> characterCountHashTable = new Hashtable<>();
        for (char c : s.toCharArray()) {
            if (characterCountHashTable.containsKey(c)) {
                int val = characterCountHashTable.get(c) + 1;
                characterCountHashTable.put(c, val);
            } else {
                characterCountHashTable.put(c, 1);
            }
        }
        for (char c : t.toCharArray()) {
            if (characterCountHashTable.containsKey(c) && characterCountHashTable.get(c) > 0) {
                int val = characterCountHashTable.get(c) - 1;
                characterCountHashTable.put(c, val);
            } else return false;
        }
        return true;
    }

    /**
     * Solution using english alphabet optimization - contains only 26 characters
     * <p>
     * This problem does not require us to keep track of the order of the characters in the string, but only the # of each character
     * <p>
     * Time Complexity - O(N), Space Complexity - O(1)
     */
    public boolean isAnagram_v2(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] charCount = new int[26];
        for (char c : s.toCharArray()) {
            charCount[c - 'a'] += 1;
        }
        for (char c : t.toCharArray()) {
            charCount[c - 'a'] -= 1;
        }
        for (int i : charCount) {
            if (i != 0) return false;
        }
        return true;
    }

}