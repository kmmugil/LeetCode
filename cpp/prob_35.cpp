#include <iostream>
#include <vector>
#include <string>

using namespace std;

class Solution {
    public:
    Solution() {
        cout << "Constructor of solution for prob_35 called" << endl;
    }
    ~Solution() {
        cout << "Destructor of solution for prob_35 called" << endl;
    }
    int searchInsert(vector<int>& nums, int target) {
        int left, right, mid;
        left = 0;
        right = nums.size() - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums.at(mid) == target) return mid;
            if (nums.at(mid) < target) left = mid + 1;
            else right = mid - 1;
        }
        if (nums.at(left) < target) return left + 1;
        else return left;
    }
} solution;

int main(int argc, char* argv[]) {
    vector<int> nums{1, 3, 4, 7};
    int target = 6;
    cout << solution.searchInsert(nums, target) << endl;
    return 0;
}