/*
547. Number of Provinces

There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

Constraints:
    1 <= n <= 200
    n == isConnected.length
    n == isConnected[i].length
    isConnected[i][j] is 1 or 0.
    isConnected[i][i] == 1
    isConnected[i][j] == isConnected[j][i]
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
private:
    vector<vector<int>> isConnected;
    int vertices[200] = { 0 };
public:
    int findCircleNum(vector<vector<int>>& isConnected) {
        this->isConnected = isConnected;
        int provinceCount = 0;
        for(size_t i = 0; i < isConnected.size(); i++) {
            /* code */
            if(vertices[i] != 0) continue;
            provinceCount++;
            this->recurseEdges(i);
        }
        return provinceCount;
    }

    void recurseEdges(int x) {
        if(vertices[x] == 1) return;
        vertices[x] = 1;
        for(size_t i = 0; i < isConnected.size(); i++) {
            /* code */
            if(x == i || isConnected.at(x).at(i) != 1) continue;
            this->recurseEdges(i);
        }
    }
} solution;

int main(int argc, char* argv[]) {
    // vector<vector<int>> isConnected = { {1, 1, 0}, {1, 1, 0}, {0, 0, 1} };
    vector<vector<int>> isConnected = { {1,0,0} ,{0,1,0},{0,0,1} };
    cout << solution.findCircleNum(isConnected);
    return 0;
}