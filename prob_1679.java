import java.util.*;

public class prob_1679 {
    public static void main(String[] args) {
        Solution_1679_1 solution = new Solution_1679_1();
        int[] nums = {1,2,3,4};
        int[] nums1 = {1,3,1,2,5,5,4};
        int[] nums2 = {3,1,5,1,1,1,1,1,2,2,3,2,2};
        int[] nums3 = {63,10,28,31,90,53,75,77,72,47,45,6,49,13,77,61,68,43,33,1,14,62,55,55,38,54,8,79,89,14,50,68,85,12,42,57,4,67,75,6,71,8,61,26,11,20,22,3,70,52,82,70,67
                ,18,66,79,84,51,78,23,19,84,46,61,63,73,80,61,15,12,58,3,21,66,42,55,7,58,85,60,23,69,41,61,35,64,58,84,61,77,45,14,1,38,4,8,42,16,79,60,80,39,67,10,24,15,6,
                37,68,76,30,53,63,87,11,71,86,82,77,76,37,21,85,7,75,83,80,8,19,25,11,10,41,66,70,14,23,74,33,76,35,89,68,85,83,57,6,72,34,21,57,72,79,29,65,3,67,8,24,24,18,26,27,68,78,64,57,55,68,28,9,11,38,45,61,37,81,89,38,43,45,26,84,62,22,37,51,15,30,67,75,24,66,40,81,74,48,43,78,14,33,19,73,5,1,2,53,29,49,73,23,5};
        System.out.println(solution.maxOperations(nums3, 59));
        System.out.println(Arrays.toString(solution.bubbleSort(nums3)));
    }
}

class Solution_1679 {
    public int maxOperations(int[] nums, int k) {
        int count = 0, mid = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            if(num < (double) k/2) {
                if(Objects.isNull(hashMap.get(num))) {
                    hashMap.put(num, 1);
                } else {
                    int tmp = hashMap.get(num) + 1;
                    hashMap.put(num, tmp);
                }
            } else if((double) num == (double) k/2) {
                mid++;
            }
        }
        for (int num : nums) {
            if(num > k/2) {
                num = k - num;
                if(!Objects.isNull(hashMap.get(num))) {
                    int tmp = hashMap.get(num) - 1;
                    if(tmp >= 0) {
                        count++;
                        hashMap.put(num, tmp);
                    }
                }
            }
        }
        count += mid/2;
        return count;
    }
}

class Solution_1679_1 {
    // time-limit exceeded, probably because of the latency of bubble sort. Try with other sorting algorithms
    public int maxOperations(int[] nums, int k) {
        int mid = 0, count = 0;
        Stack<Integer> stack = new Stack<>();
//        nums = this.bubbleSort(nums);
        Arrays.sort(nums);
        for (int num : nums) {
            if(num < (double) k/2) {
                stack.push(num);
            } else if ((double) num == (double) k/2) {
                mid++;
            } else {
                try {
                    while(stack.peek() + num > k) {
                        stack.pop();
                    }
                    if(stack.peek() + num == k) {
                        count++;
                        stack.pop();
                    }
                } catch(Exception e) {
                    break;
                }
            }
        }
        return count+mid/2;
    }
    public int[] bubbleSort(int[] nums) {
        boolean changed = true;
        while(changed) {
            changed = false;
            for (int i = 0; i < nums.length-1 ; i++) {
                if(nums[i] > nums[i+1]) {
                    nums[i] += nums[i+1];
                    nums[i+1] = nums[i] - nums[i+1];
                    nums[i] -= nums[i+1];
                    changed = true;
                }
            }
        }
        return nums;
    }
}
