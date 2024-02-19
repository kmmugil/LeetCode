/**
231. Power of Two
Easy

Given an integer n, return true if it is a power of two. Otherwise, return
false. An integer n is a power of two, if there exists an integer x such that n
== 2x.

Constraints:
  -231 <= n <= 231 - 1

Follow up: Could you solve it without loops/recursion?
*/

#include <iostream>

using namespace std;

class Solution {
 public:
  bool isPowerOfTwo(int n) {
    if (n <= 0) return false;
    while (n > 0) {
      if (n == 1) break;
      if (n % 2 != 0) return false;
      n = n / 2;
    }
    return true;
  }

  bool isPowerOfTwo_v2(int n) { return n > 0 && (n & (n - 1)) == 0; }

} solution;

int main(int argc, char *argv[]) {
  int n = 8;
  cout << (solution.isPowerOfTwo_v2(n) ? "True" : "False") << endl;
  return 0;
}
