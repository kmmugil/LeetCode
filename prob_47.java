import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class prob_47 {
    public static void main(String[] args) {
        Solution_47 solution = new Solution_47();
        int[] nums = {1,1,2};
        System.out.println(solution.permuteUnique(nums));
    }
}

class Solution_47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        int[] distinctNums = new int[21];
        int totalCount = 0;
        for (int i = 0; i < 21; i++) {
            distinctNums[i] = 0;
        }
        for (int num : nums) {
            if(distinctNums[num+10] == 0) {
                totalCount += 1;
            }
            distinctNums[num+10] += 1;
        }
        return recursePermutations(distinctNums, totalCount);
    }

    private List<List<Integer>> recursePermutations(int[] distinctNums, int totalCount) {
        if(totalCount == 0) return null;
        List<List<Integer>> permutationList = new ArrayList<>();
        for (int i = 0; i < distinctNums.length; i++) {
            if(distinctNums[i] == 0) continue;
            List<List<Integer>> permutationListTmp;
            distinctNums[i] -= 1;
            if(distinctNums[i] == 0) {
                permutationListTmp = this.recursePermutations(distinctNums, totalCount-1);
            } else {
                permutationListTmp = this.recursePermutations(distinctNums, totalCount);
            }
            distinctNums[i] += 1;
            if(permutationListTmp == null) {
                permutationListTmp = new ArrayList<>();
                List<Integer> soloList = new ArrayList<>();
                soloList.add(i-10);
                permutationListTmp.add(soloList);
            } else {
                for (List<Integer> integers : permutationListTmp) {
                    integers.add(0, i - 10);
                }
            }
            permutationList.addAll(permutationListTmp);
        }
        return permutationList;
    }
}
