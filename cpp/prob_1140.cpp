/*
1140. Stone Game II
Medium

Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones.

Alice and Bob take turns, with Alice starting first.  Initially, M = 1.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).

The game continues until all the stones have been taken.

Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.

Constraints:
    1 <= piles.length <= 100
    1 <= piles[i] <= 10^4
*/

#include <iostream>
#include <vector>
#include <numeric>

using namespace std;

class Solution {
private:
    int store[100][100];
public:
    int stoneGameII(vector<int>& piles) {
        fill_n(&store[0][0], sizeof(store) / sizeof(store[0][0]), -1);
        int pileTotal = accumulate(begin(piles), end(piles), 0);
        return this->optimalPickSum(0, piles, pileTotal, 1);
    }

    int optimalPickSum(int i, vector<int>& piles, int pileSum, int M) {
        if(i >= piles.size()) return 0;
        if(store[i][M] == -1) {
            int runningSum = 0;
            for(int X = 1; X <= 2 * M && i + X - 1 < piles.size(); X++) {
                runningSum += piles[i + X - 1];
                store[i][M] = max(store[i][M], pileSum - this->optimalPickSum(i + X, piles, pileSum - runningSum, max(X, M)));
            }
        }
        return store[i][M];
    }
} solution;

int main(int argc, char* argv[]) {
    vector<int> piles = { 1,2,3,4,5,100 };
    cout << solution.stoneGameII(piles) << endl;
    return 0;
}