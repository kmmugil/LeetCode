/**
1822. Sign of the Product of an Array

There is a function signFunc(x) that returns:
1 if x is positive.
-1 if x is negative.
0 if x is equal to 0.
You are given an integer array nums. Let product be the product of all values in the array nums.

Return signFunc(product).

Constraints:
1 <= nums.length <= 1000
-100 <= nums[i] <= 100
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int arraySign(vector<int>& nums) {
        int arraySign = 1;
        for(size_t i = 0; i < nums.size(); i++) {
            if(nums[i] == 0) return 0;
            arraySign = nums[i] > 0 ? arraySign : -1 * arraySign;
        }
        return arraySign;
    }
} solution;

int main(int argc, char* argv[]) {
    vector<int> nums = { -1,-2,-3,-4,3,2,1 };
    cout << solution.arraySign(nums) << endl;
    return 0;
}