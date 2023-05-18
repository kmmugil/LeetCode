/**
1557. Minimum Number of Vertices to Reach All Nodes

Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi] represents a directed edge from node fromi to node toi.

Find the smallest set of vertices from which all nodes in the graph are reachable. It's guaranteed that a unique solution exists.

Notice that you can return the vertices in any order.

Constraints:
    2 <= n <= 10^5
    1 <= edges.length <= min(10^5, n * (n - 1) / 2)
    edges[i].length == 2
    0 <= fromi, toi < n
    All pairs (fromi, toi) are distinct.
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    vector<int> findSmallestSetOfVertices(int n, vector<vector<int>>& edges) {
        int parentNodes[100000] = { 0 };
        for(size_t i = 0; i < edges.size(); i++) {
            /* code */
            int child = edges.at(i).at(1);
            parentNodes[child] = 1;
        }
        vector<int> indexVertices;
        for(size_t i = 0; i < n; i++) {
            /* code */
            if(parentNodes[i] == 1) continue;
            indexVertices.push_back(i);
        }
        return indexVertices;
    }
} solution;

int main(int argc, char* argv[]) {
    int n = 6;
    vector<vector<int>> edges = { {0,1}, {0,2}, {2,5}, {3,4}, {4,2} };
    vector<int> indexVertices = solution.findSmallestSetOfVertices(n, edges);
    for(size_t i = 0; i < indexVertices.size(); i++) {
        cout << indexVertices.at(i) << ", ";
    }
    return 0;
}