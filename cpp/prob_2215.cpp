/**
2215. Find the Difference of Two Arrays
Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
    answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
    answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
Note that the integers in the lists may be returned in any order.
Constraints:
    1 <= nums1.length, nums2.length <= 1000
    -1000 <= nums1[i], nums2[i] <= 1000
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    vector<vector<int>> findDifference(vector<int>& nums1, vector<int>& nums2) {
        vector<vector<int>> answer;
        vector<int> dnums1, dnums2;
        int store[2001] = { 0 };
        size_t i;
        for(i = 0; i < nums1.size() && i < nums2.size(); i++) {
            if(store[nums1.at(i) + 1000] == 0 || store[nums1.at(i) + 1000] == 1) {
                store[nums1.at(i) + 1000] = 1;
            } else {
                store[nums1.at(i) + 1000] = -1;
            }
            if(store[nums2.at(i) + 1000] == 0 || store[nums2.at(i) + 1000] == 2) {
                store[nums2.at(i) + 1000] = 2;
            } else {
                store[nums2.at(i) + 1000] = -1;
            }
        }
        if(nums1.size() < nums2.size()) {
            for(; i < nums2.size(); i++) {
                if(store[nums2.at(i) + 1000] == 0 || store[nums2.at(i) + 1000] == 2) {
                    store[nums2.at(i) + 1000] = 2;
                } else {
                    store[nums2.at(i) + 1000] = -1;
                }
            }
        } else {
            for(; i < nums1.size(); i++) {
                if(store[nums1.at(i) + 1000] == 0 || store[nums1.at(i) + 1000] == 1) {
                    store[nums1.at(i) + 1000] = 1;
                } else {
                    store[nums1.at(i) + 1000] = -1;
                }
            }
        }
        for(size_t j = 0; j < 2001; j++) {
            if(store[j] == 1) {
                dnums1.push_back(j - 1000);
            } else if(store[j] == 2) {
                dnums2.push_back(j - 1000);
            }
        }
        answer.push_back(dnums1);
        answer.push_back(dnums2);
        return answer;
    }
} solution;

int main(int argc, char* argv[]) {
    vector<int> nums1 = { 80,5,-20,33,26,29 };
    vector<int> nums2 = { -69,0,-36,52,-84,-34,-67,-100,80 };
    vector<vector<int>> answer = solution.findDifference(nums1, nums2);
    for(auto list : answer) {
        for(size_t i = 0; i < list.size(); i++) {
            cout << list.at(i) << ", ";
        }
        cout << endl;
    }
    system("pause");
    return 0;
}