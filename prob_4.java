public class prob_4 {
    public static void main(String args[]) {
        int[] nums1 = {1, 2}, nums2 = {1, 2, 3};
        System.out.println(Solution_4.findMedianSortedArrays(nums1, nums2));
    }
}

class Solution_4 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int target = (int) Math.ceil((double)(nums1.length+nums2.length)/2);
        if(nums1.length == 0) {
            if(nums2.length == 0) {
                return 0;
            } else {
                return findMedianSortedArray(nums2);
            }
        } else {
            if(nums2.length == 0) {
                return findMedianSortedArray(nums1);
            } else {
                return findMedianSortedArraysBin(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, target);
            }
        }
    }

    public static double findMedianSortedArray(int[] nums) {
        try {
            int mid = (int) Math.ceil(nums.length/2.0) - 1;
            if(nums.length%2 == 0) {
                return (nums[mid]+nums[mid+1])/2.0;
            } else {
                return nums[mid];
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Error occurred findMedianSortedArray: "+e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public static double findMedianSortedArraysBin(int[] nums1, int i, int j, int[] nums2, int m, int n, int target) {
        try {
            int midpoint = target;
            int mid = (int) Math.ceil(target/2.0);
            while(mid > 0) {
                if(i > j) break;
                if(m > n) break;
                int k1 = i+mid-1;
                int k2 = m+mid-1;
                k1 = k1>j ? j : k1;
                k2 = k2>n ? n : k2;
                if(nums1[k1] <= nums2[k2]) {
                    target = target - (k1-i+1);
                    i = k1+1;
                } else {
                    target = target - (k2-m+1);
                    m = k2+1;
                }
                mid = (int) Math.ceil(target/2.0);
            }
            int total = i+m;
            int lastFind;
            if(i > 0) {
                if(m > 0) {
                    lastFind = nums1[i-1]>nums2[m-1] ? nums1[i-1] : nums2[m-1];
                } else {
                    lastFind = nums1[i-1];
                }
            } else {
                if(m > 0) {
                    lastFind = nums2[m-1];
                } else {
                    lastFind = -1000001; // stipulated in the problem to be less than the min value in arrays
                }
            }
            if (total != midpoint) {
                if(i > j) {
                    m = m+midpoint-total;
                    lastFind = nums1[i-1]>nums2[m-1] ? nums1[i-1] : nums2[m-1];
                     total = midpoint;
                } else if(m > n) {
                    i = i+midpoint-total;
                    lastFind = nums2[m-1]>nums1[i-1] ? nums2[m-1] : nums1[i-1];
                    total = midpoint;
                } else {
                    while (total < midpoint) {
                        if (nums1[i] > lastFind && nums1[i] <= nums2[m]) {
                            lastFind = nums1[i];
                            if (i + 1 <= j) i = i + 1;
                            total += 1;
                        } else if (nums2[m] > lastFind && nums2[m] <= nums1[i]) {
                            lastFind = nums2[m];
                            if (m + 1 <= n) m = m + 1;
                            total += 1;
                        }
                    }
                }
            }
            if((nums1.length+nums2.length)%2 != 0) {
                return lastFind;
            } else {
                if(i > j) {
                    return (lastFind+nums2[m])/2.0;
                } else if(m > n) {
                    return (lastFind+nums1[i])/2.0;
                } else if(nums1[i] <= nums2[m]) {
                    return (lastFind+nums1[i])/2.0;
                } else {
                    return (lastFind+nums2[m])/2.0;
                }
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Error occurred findMedianSortedArraysBin: "+e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
}
