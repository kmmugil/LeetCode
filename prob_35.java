public class prob_35 {
    public static void main(String args[]) {
        int nums[] = {1,3,5,6}, target = 7; 
        System.out.println(Solution_35.searchInsert(nums, target));
    }
}

class Solution_35 {
    public static int searchInsert(int[] nums, int target) {
        return searchInsertBin(nums, 0, nums.length-1, target);
    }

    public static int searchInsertBin(int[] nums, int i, int j, int target) {
        if(i == j) {
            if(nums[i] >= target) return i;
            else if(nums[i] < target) return i+1;
        }
        int mid = (i+j)/2;
        if(nums[mid] == target) return mid;
        else if(nums[mid] < target) return searchInsertBin(nums, mid+1, j, target);
        else {
            if(mid == i) return mid;
            else return searchInsertBin(nums, i, mid-1, target);
        }
    }
}