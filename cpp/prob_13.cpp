/*
13. Roman to Integer
Easy

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.

Constraints:
    1 <= s.length <= 15
    s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
    It is guaranteed that s is a valid roman numeral in the range [1, 3999].
*/

#include <iostream>
#include <string>

using namespace std;

class Solution {
public:
    /**
     * A much simpler solution would be identify that if current character is smaller than next charater then,
     * Subtract the value corresponding to this character
     * Else add the value corresponding to this character
     * This works because the representation of numbers as numerals appear in decreasing order of character
     * IM is not a valid numeral since 999 is represented as DCDXCIX
    */
    int romanToInt(string s) {
        int value = 0;
        char prev = 'A';
        for(string::iterator it = s.begin(); it != s.end(); it++) {
            switch(*it) {
                case 'I':
                    value += 1;
                    break;
                case 'V':
                    if(prev == 'I') {
                        value += 3;
                    } else {
                        value += 5;
                    }
                    break;
                case 'X':
                    if(prev == 'I') {
                        value += 8;
                    } else {
                        value += 10;
                    }
                    break;
                case 'L':
                    if(prev == 'X') {
                        value += 30;
                    } else {
                        value += 50;
                    }
                    break;
                case 'C':
                    if(prev == 'X') {
                        value += 80;
                    } else {
                        value += 100;
                    }
                    break;
                case 'D':
                    if(prev == 'C') {
                        value += 300;
                    } else {
                        value += 500;
                    }
                    break;
                case 'M':
                    if(prev == 'C') {
                        value += 800;
                    } else {
                        value += 1000;
                    }
                    break;
            }
            prev = *it;
        }
        return value;
    }
} solution;

int main(int argc, char* argv[]) {
    string roman = "MCMXCIV";
    cout << solution.romanToInt(roman) << endl;
    return 0;
}