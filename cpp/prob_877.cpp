/*
877. Stone Game
Medium

Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].

The objective of the game is to end with the most stones. The total number of stones across all the piles is odd, so there are no ties.

Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row. This continues until there are no more piles left, at which point the person with the most stones wins.

Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.

Constraints:
    2 <= piles.length <= 500
    piles.length is even.
    1 <= piles[i] <= 500
    sum(piles[i]) is odd.
*/

#include <iostream>
#include <vector>
#include <numeric>

using namespace std;

class Solution {
private:
    vector<int> piles;
    int store[500][500];
public:
    bool stoneGame(vector<int>& piles) {
        // Assuming Alice starts the game
        this->piles = piles;
        int pileTotal = 0;
        for(size_t i = 0; i < piles.size(); i++) {
            pileTotal += piles.at(i);
        }
        return pileTotal / 2 < this->optimalPickSum(0, piles.size() - 1, true);
    }

    int optimalPickSum(int i, int j, bool player) {
        if(i > j) return 0;
        if(player) {
            return max(piles.at(i) + this->optimalPickSum(i + 1, j, false), piles.at(j) + this->optimalPickSum(i, j - 1, false));
        } else {
            return min(this->optimalPickSum(i + 1, j, true), this->optimalPickSum(i, j - 1, true));
        }
    }

    bool stoneGame_v2(vector<int>& piles) {
        this->piles = piles;
        // fill(&store[0][0], &store[0][0] + sizeof(store) / sizeof(store[0][0]), -1);
        fill_n(&store[0][0], sizeof(store) / sizeof(store[0][0]), -1);
        int pileTotal = accumulate(begin(piles), end(piles), 0);
        return pileTotal / 2 < this->optimalPickSum_v2(0, piles.size() - 1, pileTotal);
    }

    int optimalPickSum_v2(int i, int j, int pileSum) {
        if(i > j) return 0;
        if(i == j) return piles[i];
        if(store[i][j] == -1) {
            store[i][j] = max(pileSum - this->optimalPickSum_v2(i + 1, j, pileSum - piles[i]), pileSum - this->optimalPickSum_v2(i, j - 1, pileSum - piles[j]));
        }
        return store[i][j];
    }
} solution;

int main(int argc, char* argv[]) {
    vector<int> piles = { 3,7,2,3 };
    cout << solution.stoneGame_v2(piles) << endl;
    return 0;
}