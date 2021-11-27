public class prob_238 {
    public static void main(String args[]) {
        int[] nums = {-1,1,0,-3,3};
        Solution_238.productExceptSelf(nums);
        for(int i=0; i<nums.length; i++) {
            System.out.print(nums[i]+",");
        }
    }
}

class Solution_238 {
    public static int[] productExceptSelf(int[] nums) {
        int prod = 1; int zeroCount = 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] == 0) {
                zeroCount += 1;
                continue;
            }
            prod *= nums[i];
        }
        if(zeroCount > 1) {
            for(int i=0; i<nums.length; i++) {
                nums[i] = 0;
            }
            return nums;
        }
        else{
            for(int i=0; i<nums.length; i++) {
                if(nums[i] == 0) {
                    nums[i] = prod;
                    continue;
                }
                nums[i] = zeroCount>0 ? 0 : prod/nums[i];
            }
        }
        return nums;
    }
}
