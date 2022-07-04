import java.util.Arrays;

/**
 * 135. Candy
 * <p>
 * Hard
 * <p>
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 * <p>
 * You are giving candies to these children subjected to the following requirements:
 * <p>
 * Each child must have at least one candy.
 * <p>
 * Children with a higher rating get more candies than their neighbors.
 * <p>
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 */
public class prob_135 {

    public static void main(String[] args) {
        Solution_135 solution = new Solution_135();
//        int[] ratings = {1, 2, 2};
//        int[] ratings = {3, 2, 1, 2, 3, 3, 2, 1, 3, 3, 4, 5, 3, 2, 1};
        int[] ratings = {1, 3, 2, 2, 1};
        System.out.println(solution.candy(ratings));
    }

}

class Solution_135 {

    /**
     * Two pass of the array, first to assign candies to increasing sequences, second to assign candies to decreasing sequences
     * <p>
     * Time Complexity - O(n), Space Complexity - O(n)
     */
    public int candy(int[] ratings) {
        if (ratings.length == 1) return 1;
        int[] candies = new int[ratings.length];
        boolean flag = true;
        int incCount = 1;
        if (ratings[0] == ratings[1]) {
            candies[0] = 1;
        }
        if (ratings[ratings.length - 1] == ratings[ratings.length - 2]) {
            candies[ratings.length - 1] = 1;
        }
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] == ratings[i - 1]) {
                flag = true;
                candies[i - 1] = Math.max(1, candies[i - 1]);
            } else if (ratings[i] > ratings[i - 1]) {
                incCount++;
                if (flag) {
                    flag = false;
                    candies[i - 1] = 1;
                    incCount = 2;
                }
                candies[i] = incCount;
            } else {
                flag = true;
            }
        }
        System.out.println(Arrays.toString(ratings));
        System.out.println(Arrays.toString(candies));
        flag = false;
        if (candies[ratings.length - 1] == 0) candies[ratings.length - 1] = 1;
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (candies[i] != 0) {
                if (!flag) continue;
                if (!(ratings[i] == ratings[i + 1])) candies[i] = Math.max(candies[i], candies[i + 1] + 1);
                flag = false;
            } else {
                candies[i] = candies[i + 1] + 1;
                flag = true;
            }
        }
        System.out.println(Arrays.toString(ratings));
        System.out.println(Arrays.toString(candies));
        return Arrays.stream(candies).sum();
    }

}