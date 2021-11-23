import java.util.HashMap;
import java.util.Map;

public class prob_1 {
    public static void main(String args[]) {
        Solution solution = new Solution();
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] indices = solution.twoSum(nums, target);
        System.out.println(indices[0] + "," + indices[1]);
        return;
    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> reverseMap = new HashMap<>();
        int[] indices = new int[2];
        for(int i=0; i<nums.length; i++){
            reverseMap.put(target-nums[i], i);
        }
        for(int i=0; i<nums.length; i++){
            Integer index = reverseMap.get(nums[i]);
            if(index!=null && index!=i){
                indices[0] = i;
                indices[1] = index;
                break;
            }
        }
        return indices;
        
    }
}
