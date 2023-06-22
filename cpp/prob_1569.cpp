/*
1569. Number of Ways to Reorder Array to Get Same BST
Hard

Given an array nums that represents a permutation of integers from 1 to n. We are going to construct a binary search tree (BST) by inserting the elements of nums in order into an initially empty BST. Find the number of different ways to reorder nums so that the constructed BST is identical to that formed from the original array nums.

For example, given nums = [2,1,3], we will have 2 as the root, 1 as a left child, and 3 as a right child. The array [2,3,1] also yields the same BST but [3,2,1] yields a different BST.
Return the number of ways to reorder nums such that the BST formed is identical to the original BST formed from nums.

Since the answer may be very large, return it modulo 10^9 + 7.

Constraints:
    1 <= nums.length <= 1000
    1 <= nums[i] <= nums.length
    All integers in nums are distinct.
*/

#include <iostream>
#include <vector>
#include <queue>

using namespace std;

class Solution {
private:
    struct TreeNode {
        int val;
        int lstCount = 0;
        int rstCount = 0;
        TreeNode* left;
        TreeNode* right;
        TreeNode() : val(0), left(nullptr), right(nullptr) {}
        TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
        TreeNode(int x, TreeNode* left, TreeNode* right) : val(x), left(left), right(right) {}
    };
    vector<int> multiplicativeInverses;
    static const long long MOD = 1e9 + 7;
public:
    /**
     * Analyze the positions of nums in left and right subtree:
     * 1. The order in a particular subtree should remain the constant to maintain same BST structure
     * 2. But the elements in left subtree can be mingled with right subtree maintaining rule 1 without modifying the BST structure
     * Given l as # of elements in LST and r as # of elements in RST
     * Then # of permutations possible: (l+r)! / (l! * r!)
    */
    int numOfWays(vector<int>& nums) {
        TreeNode* root = this->constructBinarySearchTree(nums);
        // this->multiplicativeInverses.push_back(0);
        // this->multiplicativeInverses.push_back(1);
        // for(size_t i = 1; i < nums.size(); i++) {
        //     int x, y;
        //     this->gcdExtended(i + 1, 1000000007, &x, &y);
        //     this->multiplicativeInverses.push_back(x);
        // }
        vector<vector<long long>> binomialCoefficients = vector<vector<long long>>(nums.size() + 1, vector<long long>(nums.size() + 1, 0));
        for(size_t i = 1; i < nums.size(); i++) {
            binomialCoefficients[i][0] = 1;
            binomialCoefficients[i][i - 1] = i;
            binomialCoefficients[i][1] = i;
            binomialCoefficients[i][i] = 1;
        }
        for(size_t i = 2; i < nums.size() + 1; i++) {
            for(size_t j = 2; j < nums.size() + 1; j++) {
                if(j > i) break;
                binomialCoefficients[i][j] = (binomialCoefficients[i - 1][j - 1] % MOD + binomialCoefficients[i - 1][j] % MOD) % MOD;
            }
        }
        unsigned long long ways = 1;
        queue<TreeNode*> pendingNodes;
        pendingNodes.push(root);
        while(pendingNodes.size() != 0) {
            TreeNode* parent = pendingNodes.front();
            pendingNodes.pop();
            if(parent->left) pendingNodes.push(parent->left);
            if(parent->right) pendingNodes.push(parent->right);
            if(parent->lstCount == 0 || parent->rstCount == 0) continue;
            // ways = (ways * this->findPermutations(parent->lstCount, parent->rstCount)) % MOD;
            ways = (ways * binomialCoefficients[parent->lstCount + parent->rstCount][parent->rstCount]) % MOD;
        }
        if(ways < 0) ways += MOD;
        return ways - 1;
    }



    int findPermutations(int l, int r) {
        unsigned long long num = 1, denom = 1;
        for(size_t i = 2; i <= l + r; i++) {
            num = this->multiplyWithModulus(num, i);
        }
        for(size_t i = 2; i <= l; i++) {
            int x, y;
            denom = this->multiplyWithModulus(denom, this->multiplicativeInverses.at(i));
        }
        for(size_t i = 2; i <= r; i++) {
            int x, y;
            denom = this->multiplyWithModulus(denom, this->multiplicativeInverses.at(i));
        }
        cout << num << ", " << denom << endl;
        return this->multiplyWithModulus(num, denom);
    }

    int multiplyWithModulus(long a, long b) {
        for(size_t i = 0; i < b; i++) {
            a = (a + a) % MOD;
        }
        return a;
    }

    int gcdExtended(int a, int b, int* x, int* y) {
        if(a == 0) {
            *x = 0;
            *y = 1;
            return b;
        }
        int x1, y1;
        int gcd = this->gcdExtended(b % a, a, &x1, &y1);
        *x = (y1 - (b / a) * x1) % MOD;
        *y = x1 % MOD;
        return gcd;
    }

    TreeNode* constructBinarySearchTree(vector<int>& nums) {
        TreeNode* root = new TreeNode(nums.at(0));
        for(vector<int>::iterator it = nums.begin() + 1; it != nums.end(); it++) {
            int val = *it;
            TreeNode* parent = root;
            while(true) {
                // ignore equal case due to constraint
                if(val > parent->val) {
                    parent->rstCount++;
                    if(parent->right == nullptr) { parent->right = new TreeNode(val);  break; } else { parent = parent->right;  continue; }
                } else {
                    parent->lstCount++;
                    if(parent->left == nullptr) { parent->left = new TreeNode(val);  break; } else { parent = parent->left;  continue; }
                }
            }
        }
        return root;
    }
} solution;

class Solution_v2 {
    vector<vector<long long>> dp;
    long long MOD = 1e9 + 7;

    unsigned long long solve(vector<int>& nums) {
        if(nums.size() <= 1) return 1;
        vector<int> l, r;
        for(int i = 1; i < nums.size(); ++i) {
            if(nums[i] > nums[0]) r.push_back(nums[i]);
            else l.push_back(nums[i]);
        }
        int n = l.size(), m = r.size();
        return solve(l) * solve(r) % MOD * dp[n + m][n] % MOD;
    }

public:
    int numOfWays(vector<int>& nums) {
        dp = vector<vector<long long>>(nums.size() + 1, vector<long long>(nums.size() + 1, 0));
        for(int i = 1; i < nums.size() + 1; ++i) {
            dp[i][0] = 1;
            dp[i][1] = i;
            dp[i][i - 1] = i;
            dp[i][i] = 1;
        }
        for(int i = 2; i < nums.size() + 1; ++i) {
            for(int j = 2; j < nums.size() + 1; ++j) {
                if(i >= j) dp[i][j] = (dp[i - 1][j - 1] % MOD + dp[i - 1][j] % MOD) % MOD;
                else break;
            }
        }
        return solve(nums) - 1;
    }
} solution_v2;

int main(int argc, char** argv) {
    vector<int> nums = { 31,23,14,24,15,12,25,28,5,35,17,6,9,11,1,27,18,20,2,3,33,10,13,4,7,36,32,29,8,30,26,19,34,22,21,16 };
    cout << solution.numOfWays(nums);
    return 0;
}