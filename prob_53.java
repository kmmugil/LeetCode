public class prob_53 {
    public static void main(String args[]) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(Solution_53.maxSubArray(nums));
    }
}

class Solution_53 {
    public static int maxSubArray(int[] nums) {
        int max;
        if(nums[0]<0){
            max = nums[0];
            int i = 1;
            for(i=1; i<nums.length; i++) {
                if(nums[i]<0) 
                    max = Math.max(nums[i], max);
                else 
                    return computeMaxSubArray(nums, i); 
            }
            return max;
        }
        else 
            return computeMaxSubArray(nums, 0);
    }

    private static int computeMaxSubArray(int[] nums, int index) {
        int tmp_max = 0, pos = 0, max = 0;
        Boolean flag = true;
        for(int i=index; i<nums.length; i++) {
            if(nums[i]<0) {
                if(flag) {
                    if(pos > tmp_max) {
                        tmp_max = pos;
                        max = Math.max(pos, max);
                    }
                    max = Math.max(max, tmp_max);
                    pos = 0;
                    flag = false;
                }
                tmp_max += nums[i];
            }
            else {
                if(!flag) 
                    flag = true;                
                pos+=nums[i];
                tmp_max+=nums[i];
            }
        }
        if(flag) {
            if(pos > tmp_max) {
                tmp_max = pos;
                max = Math.max(pos, max);
            }
            max = Math.max(max, tmp_max);
        }
        return max;
    } 
}