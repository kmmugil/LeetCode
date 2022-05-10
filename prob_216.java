import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class prob_216 {
    public static void main(String[] args) {
        Solution_216 solution = new Solution_216();
        int k, n;
        k = 3; n = 7;
        System.out.println(solution.combinationSum3(k, n));
    }
}

class Solution_216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> resultList = this.recurseCombinations(k, n, 9);
        if(resultList == null) return new ArrayList<>();
        return resultList;
    }

    private List<List<Integer>> recurseCombinations(int k, int n, int top) {
        System.out.println("TOP: "+top+", n: "+n+", k: "+k);
        List<List<Integer>> finalList;
        if (top == 0 || k == 0) return null;
        else {
            List<List<Integer>> includedList, excludedList;
            if(top <= n) {
                includedList = recurseCombinations(k - 1, n - top, top - 1);
                if (includedList == null) {
                    if(top == n && k == 1) {
                        List<Integer> topList = new ArrayList<>();
                        topList.add(top);
                        includedList = new ArrayList<>();
                        includedList.add(topList);
                    }
                } else {
                    includedList = includedList.stream().peek(list -> list.add(top)).toList();
                }
                excludedList = recurseCombinations(k, n, top - 1);
                if(includedList == null) {
                    return excludedList;
                } else {
                    if (excludedList == null) return includedList;
                    finalList = new ArrayList<>();
                    finalList.addAll(includedList);
                    finalList.addAll(excludedList);
                    return finalList;
                }
            } else {
                excludedList = recurseCombinations(k, n, top - 1);
                return excludedList;
            }
        }
    }
}