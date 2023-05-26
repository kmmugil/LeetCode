/*
12. Integer to Roman
Medium

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral.

Constraints:
    1 <= num <= 3999
*/

#include <iostream>
#include <string>

using namespace std;

class Solution {
public:
    string intToRoman(int num) {
        string roman = "";
        int place = 0;
        while(num != 0) {
            string base;
            string mid;
            string top;
            switch(place) {
                case 0:
                    base = 'I';
                    mid = 'V';
                    top = 'X';
                    break;
                case 1:
                    base = 'X';
                    mid = 'L';
                    top = 'C';
                    break;
                case 2:
                    base = 'C';
                    mid = 'D';
                    top = 'M';
                    break;
                case 3:
                    base = 'M';
                    mid = 'A';
                    break;
            }
            string current = "";
            if(num % 10 == 9) {
                current.append(base).append(top);
            } else if(num % 10 == 4) {
                current.append(base).append(mid);
            } else {
                current.append((num % 10) / 5 == 1 ? mid : "");
                for(size_t i = 0; i < (num % 10) % 5; i++) {
                    current.append(base);
                }
            }
            roman = current.append(roman);
            num /= 10;
            place++;
        }
        return roman;
    }
} solution;

int main(int argc, char* argv[]) {
    cout << solution.intToRoman(1994) << endl;
    return 0;
}