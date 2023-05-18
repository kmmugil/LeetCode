/**
2130. Maximum Twin Sum of a Linked List

In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.

For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
The twin sum is defined as the sum of a node and its twin.

Given the head of a linked list with even length, return the maximum twin sum of the linked list.

Constraints:
The number of nodes in the list is an even integer in the range [2, 10^5].
1 <= Node.val <= 10^5
*/

#include <iostream>
#include <stack>

using namespace std;

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */

struct ListNode {
    int val;
    ListNode* next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode* next) : val(x), next(next) {}
};

class Solution {
private:
    stack<int> listStack;
public:
    int pairSum(ListNode* head) {
        ListNode* currentNode = head;
        int count = 0, maxPairSum = 0;
        do {
            listStack.push(currentNode->val);
            currentNode = currentNode->next;
            count++;
        } while(currentNode != nullptr);
        currentNode = head;
        for(int i = 0, twinVal; i < count / 2; i++) {
            twinVal = listStack.top();
            maxPairSum = max(currentNode->val + twinVal, maxPairSum);
            listStack.pop();
            currentNode = currentNode->next;
        }
        return maxPairSum;
    }
} solution;

int main(int argc, char* argv[]) {
    ListNode a1 = ListNode(4);
    ListNode a2 = ListNode(4, &a1);
    ListNode a3 = ListNode(2, &a2);
    ListNode a4 = ListNode(1, &a3);
    cout << solution.pairSum(&a4);
    return 0;
}