/*
1161. Maximum Level Sum of a Binary Tree
Medium

Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level x such that the sum of all the values of nodes at level x is maximal.

Constraints:
    The number of nodes in the tree is in the range [1, 10^4].
    -105 <= Node.val <= 10^5
*/

#include <iostream>
#include <queue>

using namespace std;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode* left, TreeNode* right) : val(x), left(left), right(right) {}
};

class Solution {
public:
    int maxLevelSum(TreeNode* root) {
        queue<TreeNode*> level;
        int count = 1, levelSumMax = INT_MIN, maxLevel = 0, currLevel = 0;
        level.push(root);
        while(level.size() > 0) {
            currLevel++;
            int levelSum = 0, nxtCount = 0;
            for(size_t i = 0; i < count; i++) {
                TreeNode* node = level.front();
                level.pop();
                levelSum += node->val;
                if(node->left != nullptr) { level.push(node->left); nxtCount++; }
                if(node->right != nullptr) { level.push(node->right); nxtCount++; }
            }
            if(levelSumMax < levelSum) {
                levelSumMax = levelSum;
                maxLevel = currLevel;
            }
            levelSumMax = max(levelSumMax, levelSum);
            count = nxtCount;
        }
        return maxLevel;
    }
} solution;

int main(int argc, char* argv[]) {
    TreeNode* root = new TreeNode(1, new TreeNode(7, new TreeNode(7), new TreeNode(-8)), new TreeNode(0));
    cout << solution.maxLevelSum(root) << endl;
    return 0;
}