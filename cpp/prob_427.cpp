/*
427. Construct Quad Tree

Medium

Given a n * n matrix grid of 0's and 1's only. We want to represent the grid with a Quad-Tree.

Return the root of the Quad-Tree representing the grid.

Notice that you can assign the value of a node to True or False when isLeaf is False, and both are accepted in the answer.

A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:

val: True if the node represents a grid of 1's or False if the node represents a grid of 0's.
isLeaf: True if the node is leaf node on the tree or False if the node has the four children.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;
}
We can construct a Quad-Tree from a two-dimensional area using the following steps:

If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
Recurse for each of the children with the proper sub-grid.

If you want to know more about the Quad-Tree, you can refer to the wiki.

Quad-Tree format:

The output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.

It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].

If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.

Constraints:
    n == grid.length == grid[i].length
    n == 2^x where 0 <= x <= 6
*/

#include <iostream>
#include <vector>
#include <queue>

using namespace std;

class Node {
private:
    vector<vector<int>> serializeQuadTree();
public:
    bool val;
    bool isLeaf;
    Node* topLeft;
    Node* topRight;
    Node* bottomLeft;
    Node* bottomRight;

    Node() {
        val = false;
        isLeaf = false;
        topLeft = NULL;
        topRight = NULL;
        bottomLeft = NULL;
        bottomRight = NULL;
    }

    Node(bool _val, bool _isLeaf) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = NULL;
        topRight = NULL;
        bottomLeft = NULL;
        bottomRight = NULL;
    }

    Node(bool _val, bool _isLeaf, Node* _topLeft, Node* _topRight, Node* _bottomLeft, Node* _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }

    void printQuadTree();
};

vector<vector<int>> Node::serializeQuadTree() {
    vector<vector<int>> output;
    queue<Node*> pendingNodes;
    pendingNodes.push(this);
    bool flag = false;
    while(!pendingNodes.empty()) {
        // if(pendingNodes.front() == nullptr && flag) {
        //     for(size_t i = 0; i < 4; i++) {
        //         output.push_back({});
        //         pendingNodes.push(nullptr);
        //     }
        // }
        Node currentNode = *pendingNodes.front();
        output.push_back({ currentNode.isLeaf, currentNode.val });
        pendingNodes.pop();
        if(currentNode.isLeaf) continue;
        pendingNodes.push(currentNode.topLeft);
        pendingNodes.push(currentNode.topRight);
        pendingNodes.push(currentNode.bottomLeft);
        pendingNodes.push(currentNode.bottomRight);
    }
    return output;
}

void Node::printQuadTree() {
    vector<vector<int>> output = this->serializeQuadTree();
    for(size_t i = 0; i < output.size(); i++) {
        cout << "[" << output[i][0] << ", " << output[i][1] << "] ";
    }
}

/*
    Solution using simple comparison and splitting the grid into four equal halves when the values are not same in all indexes.
    Time Complexity - O(N*N*logN), Space Complexity - O(N*N)
*/
class Solution {
private:
    vector<vector<int>> grid;
public:
    Node* construct(vector<vector<int>>& grid) {
        this->grid = grid;
        return this->recurseGrid(0, grid.size() - 1, 0, grid[0].size() - 1);
    }
    Node* recurseGrid(int top, int bottom, int left, int right) {
        cout << top << ", " << bottom << ", " << left << ", " << right << endl;
        int base = this->grid[top][left];
        bool flag = true;
        for(size_t i = top; i <= bottom; i++) {
            for(size_t j = left; j <= right; j++) {
                if(this->grid[i][j] == base) continue;
                flag = false;
                break;
            }
            if(!flag) break;
        }
        if(flag) {
            return new Node(grid[top][left], true);
        } else {
            Node* node = new Node(false, false);
            int midR = (bottom + top) / 2;
            int maxR = min(midR + 1, bottom);
            int midC = (right + left) / 2;
            int maxC = min(midC + 1, right);
            node->topLeft = this->recurseGrid(top, midR, left, midC);
            node->topRight = this->recurseGrid(top, midR, maxC, right);
            node->bottomLeft = this->recurseGrid(maxR, bottom, left, midC);
            node->bottomRight = this->recurseGrid(maxR, bottom, maxC, right);
            return node;
        }
    }
} solution;

int main(int argc, char* argv[]) {
    // vector<vector<int>> grid = { {0,1} ,{1,0} };
    vector<vector<int>> grid = { {1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0} };
    Node* quadTree = solution.construct(grid);
    quadTree->printQuadTree();
    return 1;
}