import java.util.Arrays;

public class prob_8 {
    public static void main(String[] args) {
        String s = "-2147483648";
        Solution_8 solution = new Solution_8();
        System.out.println(solution.myAtoi(s));
    }
}

class Solution_8 {
    public int myAtoi(String s) {
        char[] charArray = s.toCharArray();
        int start, i;
        start = -1;
        i = 0;
        int value = 0;
        while(i < charArray.length) {
            if(charArray[i] == ' ') {
                i++;
                continue;
            } else if(charArray[i] == '+' || charArray[i] == '-') {
                start = i;
                i++;
            } else if(charArray[i] <= '9' && charArray[i] >= '0') {
                start = i;
                break;
            }
            break;
        }
        if(start == -1) return 0;
        try {
            while(i < charArray.length) {
                if(charArray[i] <= '9' && charArray[i] >= '0') {
                    if(value > Integer.MAX_VALUE/10) {
                        if(charArray[start] == '-') {
                            return Integer.MIN_VALUE;
                        } else {
                            return Integer.MAX_VALUE;
                        }
                    } else if(value == Integer.MAX_VALUE/10) {
                        if(Character.getNumericValue(charArray[i]) > 8 && charArray[start] == '-') {
                            return Integer.MIN_VALUE;
                        } else if(Character.getNumericValue(charArray[i]) > 7 && charArray[start] != '-') {
                            return Integer.MAX_VALUE;
                        }
                    }
                    value = value*10 + Character.getNumericValue(charArray[i]);
                    i++;
                } else {
                    break;
                }
            }
            if(charArray[start] == '-') {
                return -1 * value;
            } else {
                return value;
            }
        } catch(NumberFormatException e) {
            if(charArray[start] == '-') {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
    }
}
