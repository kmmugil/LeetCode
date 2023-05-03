/**
1491. Average Salary Excluding the Minimum and Maximum Salary

You are given an array of unique integers salary where salary[i] is the salary of the ith employee.

Return the average salary of employees excluding the minimum and maximum salary. Answers within 10-5 of the actual answer will be accepted.

Constraints:
3 <= salary.length <= 100
1000 <= salary[i] <= 10^6
All the integers of salary are unique.
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    double average(vector<int>& salary) {
        int min = 1000001, max = 999, sum = 0;
        for(size_t i = 0; i < salary.size(); i++) {
            if(salary[i] > max) {
                max = salary[i];
            }
            if(salary[i] < min) {
                min = salary[i];
            }
            sum += salary[i];
        }
        sum -= min;
        sum -= max;
        return ((double)sum) / (salary.size() - 2);
    }
} solution;

int main(int argc, char* argv[]) {
    vector<int> salary = { 25000,48000,57000,86000,33000,10000,42000,3000,54000,29000,79000,40000 };
    cout << solution.average(salary) << endl;
    return 0;
}