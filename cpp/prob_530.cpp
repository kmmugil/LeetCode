/*
530. Minimum Absolute Difference in BST
Easy

Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.

Constraints:

The number of nodes in the tree is in the range [2, 10^4].
0 <= Node.val <= 10^5
*/

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */

#include <iostream>
#include <math.h>
#include <stack>

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
private:
    TreeNode* prev = nullptr;
    void inorderTraverseMinDiff(TreeNode* node, int& minDiff) {
        if(node == nullptr) return;
        if(node->left != nullptr) this->inorderTraverseMinDiff(node->left, minDiff);
        if(prev != nullptr) { minDiff = min(minDiff, (node->val) - (prev->val)); prev = node; } else prev = node;
        if(node->right != nullptr) this->inorderTraverseMinDiff(node->right, minDiff);
    }
public:
    /*
        Solution using plain recursion
    */
    int getMinimumDifference(TreeNode* root) {
        int minDiff = INT_MAX;
        this->findMinimumDifference(root, minDiff);
        return minDiff;
    }

    pair<int, int> findMinimumDifference(TreeNode* root, int& minDiff) {
        int maxTree = root->val, minTree = root->val;
        if(root->right != nullptr) {
            pair<int, int> diffRight = findMinimumDifference(root->right, minDiff);
            minDiff = min(minDiff, diffRight.first - (root->val));
            maxTree = diffRight.second;
        }
        if(root->left != nullptr) {
            pair<int, int> diffLeft = findMinimumDifference(root->left, minDiff);
            minDiff = min(minDiff, (root->val) - diffLeft.second);
            minTree = diffLeft.first;
        }
        return pair<int, int>(minTree, maxTree);
    }

    /*
        Solution using inorder traversal with recursion
    */
    int getMinimumDifference_v2(TreeNode* root) {
        int minDiff = INT_MAX;
        this->inorderTraverseMinDiff(root, minDiff);
        return minDiff;
    }

    /*
        Solution using inorder traversal without recursion
    */
    int getMinimumDifference_v3(TreeNode* root) {
        stack<TreeNode*> inorderStack;
        TreeNode* node = root, * prev = nullptr;
        int minDiff = INT_MAX;
        while(node != nullptr || inorderStack.size() > 0) {
            if(node != nullptr) {
                inorderStack.push(node);
                node = node->left;
            } else {
                node = inorderStack.top();
                inorderStack.pop();
                if(prev != nullptr) {
                    minDiff = min(minDiff, node->val - prev->val);
                }
                prev = node;
                node = node->right;
            }
        }
        return minDiff;
    }

} solution;

int main(int argc, char* argv[]) {
    TreeNode* leftRoot = new TreeNode(104, nullptr, new TreeNode(227));
    TreeNode* rightRoot = new TreeNode(701, nullptr, new TreeNode(911));
    TreeNode* root = new TreeNode(236, leftRoot, rightRoot);
    cout << solution.getMinimumDifference_v3(root);
}