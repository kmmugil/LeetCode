/*
541. Reverse String II
Easy

Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.

If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and leave the other as original.

Constraints:
    1 <= s.length <= 10^4
    s consists of only lowercase English letters.
    1 <= k <= 10^4
*/

#include <iostream>
#include <string>
#include <bits/stdc++.h>
#include <stack>

using namespace std;

class Solution {
public:
    /*
        Solution using simple string traversal and reversal
        Time Complexity - O(n), Space Complexity - O(n)
    */
    string reverseStr(string s, int k) {
        int left, right;
        left = 0; right = 2 * k - 1;
        string fString, tmp;
        while(right < s.length()) {
            tmp = s.substr(left, k);
            reverse(tmp.begin(), tmp.end());
            fString.append(tmp);
            fString.append(s.substr(left + k, k));
            left = right + 1;
            right = left + 2 * k - 1;
        }
        if(s.length() - left > k) {
            tmp = s.substr(left, k);
            reverse(tmp.begin(), tmp.end());
            fString.append(tmp);
            fString.append(s.substr(left + k));
        } else {
            tmp = s.substr(left, s.length() - left);
            reverse(tmp.begin(), tmp.end());
            fString.append(tmp);
        }
        return fString;
    }

    /*
        Solution using stack, but can be done using queue as well
        This data structure functions extremely well for the problem statement given
        Time Complexity - O(n), Space Complexity - O(n)
    */
    string reverseStr_v2(string s, int k) {
        stack<char> s_stack;
        for(string::iterator it = s.end() - 1; it >= s.begin(); it--) {
            s_stack.push(*it);
        }
        int count = 0;
        string fString, tmp;
        while(!s_stack.empty()) {
            if(count < k) {
                tmp.insert(tmp.begin(), s_stack.top());
                s_stack.pop();
            } else if(count < 2 * k) {
                tmp.insert(tmp.end(), s_stack.top());
                s_stack.pop();
            } else {
                fString.append(tmp);
                count = 0;
                tmp = "";
                continue;
            }
            count++;
        }
        fString.append(tmp);
        return fString;
    }
} solution;

int main(int argc, char* argv[]) {
    string s = "abcdefg";
    cout << "Final string: " << solution.reverseStr_v2(s, 2);
    return 1;
}