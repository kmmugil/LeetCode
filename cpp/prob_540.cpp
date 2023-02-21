/*
540. Single Element in a Sorted Array
Medium

You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.

Constraints:
    1 <= nums.length <= 10^5
    0 <= nums[i] <= 10^5
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    /*
        Solution using binary search
        Time Complexity - O(log n), Space Complexity - O(1)
    */
    int singleNonDuplicate(vector<int>& nums) {
        int left, right, mid;
        left = 0; right = nums.size() - 1;
        while(left < right) {
            mid = (left + right) / 2;
            if(mid > 0 && nums.at(mid - 1) == nums.at(mid)) {
                if((mid - left + 1) % 2 == 0) left = mid + 1;
                else right = mid - 2;
            } else if(mid < nums.size() - 1 && nums.at(mid) == nums.at(mid + 1)) {
                if((right - mid + 1) % 2 == 0) right = mid - 1;
                else left = mid + 2;
            } else return nums.at(mid);
        }
        return nums.at(left);
    }

    /*
        Solution using xor
        Time Complexity - O(n), Space Complexity - O(1)
    */
    int singleNonDuplicate_v2(vector<int>& nums) {
        int ans = 0;
        for(size_t i = 0; i < nums.size(); i++) {
            ans ^= nums.at(i);
        }
        return ans;
    }
} solution;

int main(int argc, char* argv[]) {
    vector<int> nums{3, 3, 7, 7, 10, 11, 11};
    cout << "Value of single non-duplicate: " << solution.singleNonDuplicate_v2(nums);
    return 1;
}
