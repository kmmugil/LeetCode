/*
837. New 21 Game
Medium

Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points and draws numbers while she has less than k points. During each draw, she gains an integer number of points randomly from the range [1, maxPts], where maxPts is an integer. Each draw is independent and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets k or more points.

Return the probability that Alice has n or fewer points.

Answers within 10-5 of the actual answer are considered accepted.

Constraints:
    0 <= k <= n <= 10^4
    1 <= maxPts <= 10^4
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
private:
    int n, k, maxPts;
    double store[20001];
public:
    double new21Game(int n, int k, int maxPts) {
        this->n = n;
        this->k = k;
        this->maxPts = maxPts;
        fill(begin(store), end(store), -1);
        return this->constructGame();
    }

    double recurseGame(int val) {
        if(val >= k) return val <= n ? 1 : 0;
        double prob = 0.0;
        for(size_t i = 0; i < maxPts; i++) {
            if(store[val + i + 1] == -1) {
                store[val + i + 1] = this->recurseGame(val + i + 1) / maxPts;
            }
            prob += store[val + i + 1];
        }
        return prob;
    }

    double constructGame() {
        if(this->k == 0) return 1;
        store[this->k - 1] = 0.0;
        for(int i = this->k; i < this->k + this->maxPts; i++) {
            store[i] = i <= this->n ? 1 : 0;
            store[this->k - 1] += store[i] / maxPts;
        }
        for(int i = this->k - 2; i >= 0; i--) {
            // double prob = 0.0;
            // for(int j = 1; j <= this->maxPts; j++) {
            //     prob += store[i + j];
            // }
            // store[i] = prob / this->maxPts;
            store[i] = store[i + 1] / maxPts + (store[i + 1] - (store[i + this->maxPts + 1] / maxPts));
        }
        return store[0];
    }
} solution;

int main(int argc, char* argv[]) {
    cout << solution.new21Game(185, 183, 2);
    return 0;
}