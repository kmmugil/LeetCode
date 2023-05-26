/*
14. Longest Common Prefix
Easy

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Constraints:
    1 <= strs.length <= 200
    0 <= strs[i].length <= 200
    strs[i] consists of only lowercase English letters.
*/

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        int minLength = 200;
        string prefix = "";
        for(vector<string>::iterator it = strs.begin(); it != strs.end(); it++) {
            minLength = min(minLength, (int)(*it).size());
        }
        for(size_t i = 0; i < minLength; i++) {
            char c = strs[0][i];
            for(vector<string>::iterator it = strs.begin() + 1; it != strs.end(); it++) {
                if(c != (*it)[i]) {
                    return prefix;
                }
            }
            prefix += c;
        }
        return prefix;
    }
} solution;

int main(int argc, char* argv[]) {
    vector<string> strs = { "flower","flow","flight" };
    cout << solution.longestCommonPrefix(strs) << endl;
    return 0;
}