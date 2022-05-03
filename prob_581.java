public class prob_581 {
    public static void main(String[] args) {
        Solution_581 solution = new Solution_581();
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        int[] nums1 = {1, 2, 3, 4};
        int[] nums2 = {1, 3, 2, 4, 5};
        int[] nums3 = {1, 3, 2, 2, 2};
        int[] nums4 = {2, 3, 3, 2, 4};
        int[] nums5 = {2, 1, 5, 3, 4};
        System.out.println(solution.findUnsortedSubarray(nums5));
    }
}

class Solution_581 {
    public int findUnsortedSubarray(int[] nums) {
        if(nums.length < 1) return 0;
        int start, end, i, prev, min, tmpMin, max, tmpMax;
        start = -1;
        prev = nums[0];
        for (i = 1; i < nums.length; i++) {
            if(nums[i] < prev) {
                start = i-1;
                prev = nums[i];
                break;
            } else {
                prev = nums[i];
            }
        }
        if(start >= 0) {
            for (end = i+1, min = nums[start], tmpMin = nums[start], max = nums[start], tmpMax = nums[start]; i < nums.length; i++) {
                if(nums[i] < prev || nums[i] < nums[start]) {
                    min = Math.min(min, tmpMin);
                    max = Math.max(max, tmpMax);
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);
                    end = i+1;
                } else {
                    tmpMin = Math.min(min, nums[i]);
                    tmpMax = Math.max(max, nums[i]);
                }
                prev = nums[i];
            }
        } else {
            return 0;
        }
        for (int j = start-1; j >= 0; j--) {
            if(min < nums[j]) {
                start--;
            } else {
                break;
            }
        }
        for (int j = end; j < nums.length; j++) {
            if(nums[j] < max) {
                end++;
            } else {
                break;
            }
        }
        System.out.println(start+","+end);
        System.out.println(min+","+max);
        if(end == -1) {
            return nums.length - start;
        } else {
            return end - start;
        }
    }
}
