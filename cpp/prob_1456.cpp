/**
1456. Maximum Number of Vowels in a Substring of Given Length

Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.

Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

Constraints:
1 <= s.length <= 10^5
s consists of lowercase English letters.
1 <= k <= s.length
*/

#include <iostream>
#include <string>
#include <map>

using namespace std;

class Solution {
private:
    map<char, bool> vowels = { {'a', true}, {'e', true}, {'i', true}, {'o', true}, {'u', true} };
public:
    int maxVowels(string s, int k) {
        size_t start = 0, end, runningCount = 0, maxCount = 0;
        for(size_t i = 0; i < s.length() && i < k; i++) {
            if(this->vowels.find(s[i]) != this->vowels.end()) {
                runningCount++;
            }
            end = i;
        }
        maxCount = runningCount;
        for(end = end + 1; end < s.length(); start++, end++) {
            if(this->vowels.find(s[start]) != this->vowels.end()) {
                runningCount--;
            }
            if(this->vowels.find(s[end]) != this->vowels.end()) {
                runningCount++;
                maxCount = maxCount < runningCount ? runningCount : maxCount;
            }
        }
        return maxCount;
    }
    int maxVowels_1(string s, int k) {
        size_t cumulativeVowels[100001] = { 0 }, maxCount = 0;
        if(s[0] == 'a' || s[0] == 'e' || s[0] == 'i' || s[0] == 'o' || s[0] == 'u') {
            cumulativeVowels[0] = 1;
        }
        for(size_t i = 1; i < s.length(); i++) {
            cumulativeVowels[i] = cumulativeVowels[i - 1];
            if(s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o' || s[i] == 'u') {
                cumulativeVowels[i] += 1;
            }
        }
        maxCount = cumulativeVowels[k - 1];
        for(size_t i = k; i < s.length(); i++) {
            maxCount = max(maxCount, cumulativeVowels[i] - cumulativeVowels[i - k]);
        }
        return maxCount;
    }
} solution;

int main(int argc, char* argv[]) {
    string s = "leetcode";
    int k = 3;
    cout << solution.maxVowels_1(s, k) << endl;
    return 0;
}