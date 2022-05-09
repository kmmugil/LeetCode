import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class prob_19 {
    public static void main(String[] args) {
        Solution_19 solution = new Solution_19();
        String digits = "23";
        System.out.println(solution.letterCombinations(digits));
    }
}

class Solution_19 {
    private final static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private final static int[] offset = {0,3,6,9,12,15,19,22,26};
    private List<String> stringList = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        char[] digitList = digits.toCharArray();
        this.recurseDigitList(digitList, 0);
        return stringList;
    }

    private void recurseDigitList(char[] digitList, int index) {
        if(index == digitList.length) return;
        int digit = Character.getNumericValue(digitList[index]);
        if(stringList.isEmpty()) {
            for(int i = offset[digit-2]; i < offset[digit-1]; i++) {
                stringList.add(String.valueOf(alphabet[i]));
            }
        } else {
            List<List<String>> appendList = new ArrayList<>();
            for(int i = offset[digit-2], j = 0; i < offset[digit-1]; i++, j++) {
                int finalI = i;
                appendList.add(new ArrayList<>());
                appendList.set(j, stringList.stream().map(string -> string.concat(String.valueOf(alphabet[finalI]))).toList());
            }
            List<String> newStringList = new ArrayList<>();
            appendList.forEach(newStringList::addAll);
            stringList = newStringList;
        }
        this.recurseDigitList(digitList, ++index);
    }
}