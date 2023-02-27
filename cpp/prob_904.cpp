/*
904. Fruit Into Baskets

Medium

You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.

Constraints:
    1 <= fruits.length <= 10^5
    0 <= fruits[i] < fruits.length
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
private:
    vector<int> fruits;
    int recurseFruitPicking_v2(int _pos, int a, int countA, int b, int countB);
    int recurseFruitPicking_v3(int _pos);
    int countFruits(int _pos);
public:
    int totalFruit(vector<int>& fruits);
    int totalFruit_v2(vector<int>& fruits) {
        this->fruits = fruits;
        return this->recurseFruitPicking_v2(0, -1, 0, -1, 0);
    }
    int totalFruit_v3(vector<int>& fruits) {
        this->fruits = fruits;
        return this->recurseFruitPicking_v3(0);
    }
    int totalFruit_v4(vector<int>& fruits);
} solution;

/*
    Simple array traversal.
    Keep record of previous fruit type's starting position when inflection.
    If belonging to same two types include and switch latest fruit type.
    Do until end of fruit array.
    Time Complexity - O(N), Space Complexity - O(1)
*/
int Solution::totalFruit(vector<int>& fruits) {
    int a, b, start;
    a = b = fruits[0];
    start = 0;
    int runningCount = 0, count = 1;
    for(int i = 1; i < fruits.size(); i++) {
        if(fruits[i] == fruits[i - 1]) {
            count++;
            continue;
        }
        if(a == fruits[i] || a == b) {
            a = b;
            b = fruits[i];
            count++;
        } else {
            runningCount = max(runningCount, count);
            count = i - start + 1;
            a = b;
            b = fruits[i];
        }
        start = i;
    }
    runningCount = max(runningCount, count);
    return runningCount;
}

int Solution::countFruits(int _pos) {
    int types[10001] = { 0 }, typeCount = 0, count = 0;
    for(size_t i = _pos; i < this->fruits.size(); i++) {
        int type = this->fruits[i];
        if(types[type] == 0) {
            if(typeCount < 2) {
                count++; typeCount++;
                types[type]++;
            } else {
                return count;
            }
        } else {
            count++;
            types[type]++;
        }
    }
    return count;
}

int Solution::totalFruit_v4(vector<int>& fruits) {
    this->fruits = fruits;
    int runningCount = countFruits(0);
    for(int i = 1; i < fruits.size(); i++) {
        if(fruits[i] == fruits[i - 1]) continue;
        else runningCount = max(runningCount, this->countFruits(i));
    }
    return runningCount;
}

int Solution::recurseFruitPicking_v3(int _pos) {
    if(_pos >= this->fruits.size()) return 0;
    vector<int> types;
    int count = 0;
    for(size_t i = _pos; i < this->fruits.size(); i++) {
        bool flag = false;
        for(size_t j = 0; j < types.size(); j++) {
            if(this->fruits[i] == types[j]) {
                flag = true;
                break;
            }
        }
        if(flag) count++;
        else if(types.size() < 2) {
            types.push_back(fruits[i]);
            count++;
        } else {
            break;
        }
    }
    int new_pos = this->fruits.size();
    for(int i = _pos + 1; i < this->fruits.size(); i++) {
        if(fruits[i] == fruits[i - 1]) continue;
        else {
            new_pos = i;
            break;
        }
    }
    return max(count, this->recurseFruitPicking_v3(new_pos));
}

int Solution::recurseFruitPicking_v2(int _pos, int a, int countA, int b, int countB) {
    if(_pos == this->fruits.size()) return countA + countB;
    if(a != -1 && fruits[_pos] != a) {
        if(b != -1 && fruits[_pos] != b) {
            return this->recurseFruitPicking_v2(_pos + 1, a, countA, b, countB);
        } else {
            return max(this->recurseFruitPicking_v2(_pos + 1, a, countA, fruits[_pos], countB + 1), this->recurseFruitPicking_v2(_pos + 1, a, countA, b, countB));
        }
    } else {
        if(a == -1) {
            if(b != -1 && fruits[_pos] == b) {
                return this->recurseFruitPicking_v2(_pos + 1, a, countA, b, countB + 1);
            } else {
                return max(this->recurseFruitPicking_v2(_pos + 1, fruits[_pos], countA + 1, b, countB), this->recurseFruitPicking_v2(_pos + 1, a, countA, b, countB));
            }
        } else {
            return this->recurseFruitPicking_v2(_pos + 1, a, countA + 1, b, countB);
        }
    }
}

int main(int argc, char* argv[]) {
    vector<int> fruits = { 1,2,1 };
    // vector<int> fruits = { 0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
    cout << solution.totalFruit(fruits);
    return 1;
}