import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

/**
 * 32. Longest Valid Parentheses
 * Hard
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 */
public class prob_32 {
    public static void main(String[] args) {
        Solution_32 solution = new Solution_32();
//        String s = "(()";
//        String s = ")()())";
        String s = ")()())()()(";
//        String s = "()(())";
//        String s = "))))((()((";
        System.out.println(solution.longestValidParentheses_v4(s));
    }
}

class Solution_32 {
    private final Stack<Integer> cStack = new Stack<>();

    /**
     * Solution using Stack
     * But here instead of incrementing the left count, if we just store the index and calculate the length of the valid substring using the difference
     * between the current index and top of stack, that should also work
     */
    public int longestValidParentheses(String s) {
        int tmp, cumulative, max = Integer.MAX_VALUE;
        for (char c : s.toCharArray()) {
            if(c == '(') {
                if(cStack.isEmpty()) cStack.push(1);
                else {
                    tmp = cStack.pop();
                    if(tmp <= 0) {
                        cStack.push(tmp);
                        cStack.push(1);
                    } else {
                        cStack.push(tmp+1);
                    }
                }
            } else {
                cumulative = 0;
                while(true) {
                    if(cStack.isEmpty()) {
                        if(cumulative < 0) cStack.push(cumulative);
                        cStack.push(0);
                        break;
                    }
                    tmp = Objects.requireNonNullElse(cStack.pop(), 0);
                    if(tmp == 0) {
                      cStack.push(0);
                      if(cumulative < 0) cStack.push(cumulative);
                      cStack.push(0);
                      break;
                    } else if(tmp < 0) {
                        cumulative += tmp;
                    } else {
                        tmp--;
                        cumulative += -1;
                        if(tmp > 0) cStack.push(tmp);
                        if(cumulative < 0) cStack.push(cumulative);
                        break;
                    }
                }
            }
            if (cStack.isEmpty()) {
                System.out.println("None");
            } else {
                System.out.println(Arrays.toString(cStack.toArray()));
            }
        }
        cumulative = 0;
        for (Integer integer : cStack) {
            if(integer >= 0) {
                max = Math.min(max, cumulative);
                cumulative = 0;
            } else {
                cumulative += integer;
            }
        }
        max = Math.min(max, cumulative);
        return -2*max;
    }

    /**
     * Solution without using stack (mem - O(1), time - O(n))
     * Left to right scan and right to left scan
     */
    public int longestValidParentheses_v2(String s) {
        int left = 0, right = 0, max = 0;
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if(c == '(') left++;
            else right++;
            if(left < right) left = right = 0;
            if(left == right) max = Math.max(max, left);
        }
        left = right = 0;
        for (int i = charArray.length - 1; i >= 0; i--) {
            if(charArray[i] == ')') right++;
            else left++;
            if(right < left) left = right = 0;
            if(right == left) max = Math.max(max, right);
        }
        return max*2;
    }

    private int[] store;
    private char[] charArray;

    /**
     * Solution using Dynamic Programming
     * Here the sub-problems are longest substrings ending at a particular index
     * Goal: find that val where the index = s.length()
     * Using recursion we could traverse the charArray from left to right/ right to left
     * But when we iterate from just left to right on each index, recursion is not necessary
     */
    public int longestValidParentheses_v3(String s) {
        charArray = s.toCharArray();
        store = new int[s.length()];
        store[0] = 0;
        for (int i = 1; i < charArray.length; i++) {
            if(charArray[i] == '(') store[i] = 0;
            else store[i] = -1;
        }
        int max = 0;
        for (int i = 0; i < store.length; i++) {
            System.out.println(this.recurseStringForValidParentheses(i));
        }
        for (int i : store) {
            max = Math.max(max, i);
        }
        return max;
    }

    private int recurseStringForValidParentheses(int i) {
        if(i >= store.length) return -1;
        if(store[i] != -1) return store[i];
        if(charArray[i-1] == '(') store[i] = (i > 2 ? store[i-2] : 0) + 2;
        else {
            int k = i-1;
            while(k > 0 && charArray[k] == ')') {
                store[k] = this.recurseStringForValidParentheses(k);
                if(store[k] == 0) {
                    k = -1;
                    break;
                }
                k = k-store[k];
            }
            if(k >= 0 && charArray[k] == '(') store[i] = (k-1 > 0 ? store[k-1] : 0) + (i-k+1);
            else store[i] = 0;
        }
        return store[i];
    }

    public int longestValidParentheses_v4(String s) {
        if(s.length() == 0) return 0;
        charArray = s.toCharArray();
        store = new int[s.length()];
        store[0] = 0;
        int max = 0;
        for (int i = 1; i < charArray.length; i++) {
            if(charArray[i] == '(') store[i] = 0;
            else {
                if(charArray[i-1] == '(') store[i] = (i > 2 ? store[i-2] : 0) + 2;
                else {
                    int k = i-store[i-1]-1;
                    if(k >= 0 && charArray[k] == '(') store[i] = i-k+1;
                    else store[i] = 0;
                }
            }
            max = Math.max(max, store[i]);
        }
        return max;
    }

}
