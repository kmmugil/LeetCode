/*
22. Generate Parentheses

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Constraints:
    1 <= n <= 8
*/

#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Solution {
private:
    vector<string> paranthesisList;
public:
    vector<string> generateParenthesis(int n) {
        this->constructValidParanthesis("", 0, n * 2);
        return this->paranthesisList;
    }
    void constructValidParanthesis(string s, int toClose, int pending) {
        if(pending == 0 && toClose == 0) {
            this->paranthesisList.push_back(s);
            return;
        }
        if(toClose > 0) {
            s.append(")");
            this->constructValidParanthesis(s, toClose - 1, pending - 1);
            s.erase(s.end() - 1);
        }
        if(toClose < pending) {
            s.append("(");
            this->constructValidParanthesis(s, toClose + 1, pending - 1);
            s.erase(s.end() - 1);
        }
    }
} solution;

int main(int argc, char* argv[]) {
    vector<string> paranthesisList = solution.generateParenthesis(3);
    for(size_t i = 0; i < paranthesisList.size(); i++) {
        cout << paranthesisList.at(i) << endl;
    }
    return 0;
}