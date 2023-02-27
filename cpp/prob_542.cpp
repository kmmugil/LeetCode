/*
542. 01 Matrix
Medium

Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

Constraints:
    m == mat.length
    n == mat[i].length
    1 <= m, n <= 10^4
    1 <= m * n <= 10&=^4
    mat[i][j] is either 0 or 1.
    There is at least one 0 in mat.
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
private:
    vector<vector<int>> cost;
    vector<vector<bool>> visited;
    vector<pair<int, int>> focii;
    int calcMinCost(int i, int j);
    int pushBackFocus(int i, int j, int parentCost);
public:
    vector<vector<int>> updateMatrix(vector<vector<int>>& mat);
} solution;

/*
    The solution here can be thought of as applying BFS on a graph
    Add the visited nodes to the queue and continue till the whole matrix is covered initialized with the nodes containing zero as their value
    Time Complexity - O(m*n), Space Complexity - O(m*n)
*/
vector<vector<int>> Solution::updateMatrix(vector<vector<int>>& mat) {
    for(size_t i = 0; i < mat.size(); i++) {
        vector<int> c_tmp;
        vector<bool> v_tmp;
        for(size_t j = 0; j < mat[i].size(); j++) {
            if(mat[i][j] == 0) {
                c_tmp.push_back(0);
                v_tmp.push_back(true);
                focii.push_back(pair(i, j));
            } else {
                c_tmp.push_back(100000);
                v_tmp.push_back(false);
            }
        }
        cost.push_back(c_tmp);
        visited.push_back(v_tmp);
    }

    // bool flag = true;
    // while(flag) {
    //     flag = false;
    //     for(size_t i = 0; i < cost.size(); i++) {
    //         for(size_t j = 0; j < cost[i].size(); j++) {
    //             if(cost[i][j] == 0) continue;
    //             int initial = cost[i][j];
    //             if(i < cost.size() - 1) cost[i][j] = min(cost[i][j], cost[i + 1][j] + 1);
    //             if(j < cost[i].size() - 1) cost[i][j] = min(cost[i][j], cost[i][j + 1] + 1);
    //             if(i > 0) cost[i][j] = min(cost[i][j], cost[i - 1][j] + 1);
    //             if(j > 0) cost[i][j] = min(cost[i][j], cost[i][j - 1] + 1);
    //             if(initial != cost[i][j]) flag = true;
    //         }
    //     }
    // }

    vector<pair<int, int>>::iterator it;
    while(focii.size() > 0) {
        it = focii.begin();
        int i = (*it).first;
        int j = (*it).second;
        if(i > 0 && !visited[i - 1][j]) {
            this->pushBackFocus(i - 1, j, cost[i][j]);
        }
        if(i < visited.size() - 1 && !visited[i + 1][j]) {
            this->pushBackFocus(i + 1, j, cost[i][j]);
        }
        if(j > 0 && !visited[i][j - 1]) {
            this->pushBackFocus(i, j - 1, cost[i][j]);
        }
        if(j < visited[0].size() - 1 && !visited[i][j + 1]) {
            this->pushBackFocus(i, j + 1, cost[i][j]);
        }
        focii.erase(focii.begin());
    }
    return cost;
}

int Solution::calcMinCost(int i, int j) {
    int r_count = 10000;
    for(size_t k = 0; k < focii.size(); k++) {
        r_count = min(r_count, abs(i - focii[k].first) + abs(j - focii[k].second) + cost[focii[k].first][focii[k].second]);
    }
    cost[i][j] = r_count;
    visited[i][j] = true;
    focii.push_back(pair(i, j));
    return r_count;
}

int Solution::pushBackFocus(int i, int j, int parentCost) {
    cost[i][j] = parentCost + 1;
    visited[i][j] = true;
    focii.push_back(pair(i, j));
    return parentCost;
}



int main(int argc, char* argv[]) {
    // vector<vector<int>> mat = { {0},{1} };
    vector<vector<int>> mat {{0, 0, 0}, { 0, 1, 0 }, { 1, 1, 1 }};
    mat = solution.updateMatrix(mat);
    for(size_t i = 0; i < mat.size(); i++) {
        for(size_t j = 0; j < mat[i].size(); j++) {
            cout << mat[i][j] << " ";
        }
        cout << endl;
    }
    return 1;
}