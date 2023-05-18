/*
657. Robot Return to Origin

There is a robot starting at the position (0, 0), the origin, on a 2D plane. Given a sequence of its moves, judge if this robot ends up at (0, 0) after it completes its moves.

You are given a string moves that represents the move sequence of the robot where moves[i] represents its ith move. Valid moves are 'R' (right), 'L' (left), 'U' (up), and 'D' (down).

Return true if the robot returns to the origin after it finishes all of its moves, or false otherwise.

Note: The way that the robot is "facing" is irrelevant. 'R' will always make the robot move to the right once, 'L' will always make it move left, etc. Also, assume that the magnitude of the robot's movement is the same for each move.

Constraints:
    1 <= moves.length <= 2 * 10^4
    moves only contains the characters 'U', 'D', 'L' and 'R'.
*/

#include <iostream>
#include <string>

using namespace std;

class Solution {
public:
    bool judgeCircle(string moves) {
        int positionX = 0, positionY = 0;
        for(string::iterator i = moves.begin(); i != moves.end(); i++) {
            switch(*i) {
                case 'U':
                    positionX++;
                    break;
                case 'D':
                    positionX--;
                    break;
                case 'L':
                    positionY--;
                    break;
                case 'R':
                    positionY++;
                    break;
            }
        }
        return positionX == 0 && positionY == 0;
    }
} solution;

int main(int argc, char* argv[]) {
    string moves = "UL";
    cout << solution.judgeCircle(moves);
    return 0;
}