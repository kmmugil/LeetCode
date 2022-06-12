import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1695. Maximum Erasure Value
 * Medium
 * You are given an array of positive integers nums and want to erase a subarray containing unique elements.
 * The score you get by erasing the subarray is equal to the sum of its elements.
 *
 * Return the maximum score you can get by erasing exactly one subarray.
 *
 * An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).
 */
public class prob_1695 {
    public static void main(String[] args) {
        Solution_1695 solution = new Solution_1695();
//        int[] nums = {4,2,4,5,6};
//        int[] nums = {187,470,25,436,538,809,441,167,477,110,275,133,666,345,411,459,490,266,987,965,429,166,809,340,467,318,125,165,809,610,31,585,970,306,42,189,169,743,78
//                ,810,70,382,367,490,787,670,476,278,775,673,299,19,893,817,971,458,409,886,434};
        int[] nums = {215,436,338,139,220,815,150,720,64,721,811,32,411,901,120,657,311,429,73,825,217,173,256,805,864,459,306,426,855,425,893,557,571,255,185,300,976,388,275
                ,301,263,834,228,116,959,109,369,162,38,384,27,387,183,773,419,409,437,799,378,977,735,618,783,941,132,944,469,633,292,660,890,22,817,356,782,406,427,179,316,574,21,492,966,962,162,27,422,451,568,187,809,626,29,758,713,294,555,104,556,689,950,983,224,811,587,926,77,478,122,333,849,504,702,94,28,837,483,266,48,147,28,568,785,573,332,207,38,442,38,852,190,648,715,32,184,361,986,466,740,980,816,875,199,687,415,619,472,52,634,348,689,325,39,870,22,638,705,282,433,272,108,755,307,279,897,317,1,935,550,335,690,614,502,94,872,269,318,735,651,71,506,886,359,2,38,320,219,274,308,715,464,252,329,932,726,196,834,869,251,17,248,60,99,911,271,665,783,140,603,621,827,975,807,459,853,605,537,550,384,444,732,614,606,866,290,630,665,746,787,410,532,27,271,457,979,774,97,238,760,205,306,679,811,857,849,689,512,927,151,772,219,251,387,747,961,21,115,511,393,791,5,204,173,280,251,468,789,197,909,710,822,731,539,121,310,806,836,6,455,305,50,38,289,33,666,78,670,292,241,311,320,173,141,962,207,494,314,779,120,185,303,454,794,962,886,115,647,519,241,808,787,846,878,413,180,465,501,194,86,176,201,537,257,602,604,857,68,545,143,396,733,459,997,559,783,64,931,486,452,611,41,921,506,967,449,23,754,564,130,830,905,678,388,307,464,818,998,970,891,204,754,887,17,495,339,40,494,292,823,746,842,39,524,507,746,764,488,955,670,35,99,387,147,370,261,848,605,695,406,408,84,657,320,982,564,424,742,447,284,658,589,930,765,467,260,552,171,903,869};
        System.out.println(solution.maximumUniqueSubarray_v5(nums));
    }
}

/**
 * Can't use a binary search here since the elements in the array are sorted
 * But one advantage here is that the elements in the running sum has to be contiguous.
 * Thus, we can simultaneously calculate the sum of elements to be deleted and the starting point where the new sub-array starts with a single traversal
 * Time Complexity - O(n*n), Space Complexity - O(1)/O(m) where m is the # of unique elements in the array (Hash Set approach)
 * Here the time complexity is O(n) since the window in which the element is checked to be unique is sliding. Thus, the total window is of length 'n'
 */
class Solution_1695 {
    int[] nums;

    /**
     * Dictionary approach - the fastest
     * Time Complexity - O(n), Space Complexity - O(1e4 + 1)
     * Sliding window approach
     */
    public int maximumUniqueSubarray_v5(int[] nums) {
        int[] map = new int[10001];
        int max = 0, runningSum = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            while(map[nums[j]] != 0) {
                runningSum -= nums[i];
                map[nums[i++]]--;
            }
            map[nums[j]]++;
            runningSum += nums[j];
            max = Math.max(max, runningSum);
        }
        return max;
    }

    /**
     * Hash set approach is slower than the runningSum method
     */
    public int maximumUniqueSubarray(int[] nums) {
        int max = 0, runningSum = 0;
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0, j = 0; j < nums.length; j++) {
            while(hashSet.contains(nums[j])) {
                hashSet.remove(nums[i]);
                runningSum -= nums[i++];
                System.out.println(i);
            }
            hashSet.add(nums[j]);
            runningSum += nums[j];
            max = Math.max(max, runningSum);
            System.out.println(hashSet);
        }
        return max;
    }

    /**
     * Running sum approach with array traversal to calculate deletion sum if element is duplicate
     */
    public int maximumUniqueSubarray_v2(int[] nums) {
        int max = 0, runningSum = 0, dSum;
        for (int i = 0, j = 0, k; j < nums.length; j++) {
            dSum = 0;
            for (k = i; k < j; k++) {
                if(nums[k] == nums[j]) break;
                dSum += nums[k];
            }
            if(k < j) {
                i = k+1;
                System.out.println(nums[i-1]+", "+nums[j]);
                runningSum -= dSum;
            } else {
                runningSum += nums[j];
            }
            max = Math.max(max, runningSum);
        }
        return max;
    }

    public int maximumUniqueSubarray_v3(int[] nums) {
        int[] storeMax = new int[nums.length];
        storeMax[0] = nums[0];
        int max = nums[0];
        for (int i = 0, j = 1, k; j < nums.length; j++) {
            storeMax[j] = storeMax[j-1]+nums[j];
            for (k = i; k < j; k++) {
                if(nums[k] == nums[j]) break;
            }
            if(k < j) {
                i = k+1;
                System.out.println(nums[i-1]+", "+nums[j]);
                for (k = i; k <= j; k++) {
                    storeMax[k] -= storeMax[i-1];
                }
            }
            max = Math.max(max, storeMax[j]);
            System.out.println(Arrays.toString(storeMax));
        }
        return max;
    }

    public int maximumUniqueSubarray_v4(int[] nums) {
        int[][] storeMax = new int[nums.length][nums.length];
        storeMax[0][0] = nums[0];
        int max = nums[0];
        for (int i = 0, j = 1, k; j < nums.length; j++) {
            for (k = i; k <= j; k++) {
                storeMax[k][j] += storeMax[k][j-1]+nums[j];
                if(storeMax[k][j-1] == 0) storeMax[k][j] = nums[j];
            }
//            storeMax[i][j] = storeMax[j-1][i]+nums[j];
            for (k = i; k < j; k++) {
                if(nums[k] == nums[j]) break;
            }
            if(k < j) {
                i = k+1;
            }
            max = Math.max(max, storeMax[i][j]);
        }
        for (int[] ints : storeMax) {
            System.out.println(Arrays.toString(ints));
        }
        return max;
    }

    private int binarySearch(int l, int r, int target) {
        int mid;
        while(l <= r) {
            mid = (l+r)/2;
            if(target == this.nums[mid]) return mid;
            if(target < this.nums[mid]) r = mid-1;
            else l = mid+1;
        }
        return -1;
    }
}
