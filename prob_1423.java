/**
 * 1423. Maximum Points You Can Obtain from Cards
 * Medium
 * There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array cardPoints.
 *
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 *
 * Your score is the sum of the points of the cards you have taken.
 *
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 */
public class prob_1423 {
    public static void main(String[] args) {
        Solution_1423 solution = new Solution_1423();
        int[] cardPoints = {1,2,3,4,5,6,1};
        int k = 3;
        System.out.println(solution.maxScore(cardPoints, k));
    }
}

/**
 * Solution using two pointers, array traversal
 * Time Complexity - O(n), Space Complexity - O(1)
 */
class Solution_1423 {
    public int maxScore(int[] cardPoints, int k) {
        int runningScore = 0, maxScore;
        for (int i = 0; i < k; i++) {
            runningScore += cardPoints[i];
        }
        maxScore = runningScore;
        for (int i = k-1; i >= 0; i--) {
            runningScore -= cardPoints[i];
            runningScore += cardPoints[cardPoints.length+i-k];
            maxScore = Math.max(maxScore, runningScore);
        }
        return maxScore;
    }
}
