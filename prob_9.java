import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class prob_9 {
    public static void main(String[] args) {
        Solution_9 solution = new Solution_9();
        System.out.println(solution.isPalindrome(67876));
    }
}

class Solution_9 {
    public boolean isPalindrome(int x) {
        String intString = String.valueOf(x);
        char[] charArray = intString.toCharArray();
        for (int i = 0, j = charArray.length-1; i < charArray.length/2; i++, j--) {
            if(charArray[i] != charArray[j]) {
                return false;
            }
        }
        return true;
    }

    // doesn't capture negative palindromes
    public boolean isPalindrome_1(int x) {
        List<Integer> intArray = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while(x/10 != 0) {
            intArray.add(x%10);
            x /= 10;
        }
        if(x%10 != 0) {
            intArray.add(x%10);
        }
        for (int i = 0; i < intArray.size()/2; i++) {
            stack.push(intArray.get(i));
        }
        for (int i = intArray.size()/2+1; i < intArray.size(); i++) {
            int tmp = stack.pop();
            if(tmp != intArray.get(i)) return false;
        }
        return true;
    }
}
