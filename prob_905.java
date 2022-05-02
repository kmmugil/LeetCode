import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class prob_905 {
    public static void main(String[] args) {
        int[] nums = {3,1,2,4};
        System.out.println(Arrays.toString(Solution_905.sortArrayByParity(nums)));
    }
}

class Solution_905 {
    public static int[] sortArrayByParity(int[] nums) {
        List<Integer> even, odd;
        even = new ArrayList<>();
        odd = new ArrayList<>();
        for (int num : nums) {
            if(num%2 == 0) {
                even.add(num);
            } else {
                odd.add(num);
            }
        }
        even.addAll(odd);
        return even.stream().mapToInt(i -> i).toArray();
    }
}
