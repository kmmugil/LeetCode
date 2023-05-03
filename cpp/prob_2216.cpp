/**
2216. Minimum Deletions to Make Array Beautiful

You are given a 0-indexed integer array nums. The array nums is beautiful if:

nums.length is even.
nums[i] != nums[i + 1] for all i % 2 == 0.
Note that an empty array is considered beautiful.

You can delete any number of elements from nums. When you delete an element, all the elements to the right of the deleted element will be shifted one unit to the left to fill the gap created and all the elements to the left of the deleted element will remain unchanged.

Return the minimum number of elements to delete from nums to make it beautiful.

Constraints:

1 <= nums.length <= 10^5
0 <= nums[i] <= 10^5
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int minDeletion(vector<int>& nums) {
        int counter = 0;
        for(size_t i = 0; i < nums.size() - 1; i++) {
            if((i - counter) % 2 == 0) {
                if(nums.at(i) == nums.at(i + 1)) {
                    // cout << nums[i] << " " << nums[i + 1] << endl;
                    counter += 1;
                }
            }
        }
        if((nums.size() - counter) % 2 == 1) {
            return counter + 1;
        } else {
            return counter;
        }
    }
} solution;

int main(int argc, char* argv[]) {
    vector<int> nums = { 1,1,2,2,3,3 };
    cout << solution.minDeletion(nums) << endl;
    return 0;
}