import java.util.*;

/**
 * 970. Powerful Integers
 * Medium
 * <p>
 * Given three integers x, y, and bound, return a list of all the powerful integers that have a value less than or equal to bound.
 * <p>
 * An integer is powerful if it can be represented as xi + yj for some integers i >= 0 and j >= 0.
 * <p>
 * You may return the answer in any order. In your answer, each value should occur at most once.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= x, y <= 100
 * <p>
 * 0 <= bound <= 106
 */
public class prob_970 {

    public static void main(String[] args) {
        Solution_970 solution = new Solution_970();
        int x, y, bound;
        x = 73;
        y = 3;
        bound = 700000;
        System.out.println(solution.powerfulIntegers(x, y, bound));
    }

}

/**
 * Solution using logarithmic bounds
 * <p>
 * Time Complexity - O(N*M), Space Complexity - O(N*M)
 * <p>
 * where N is log(bound)/log(x) and M is log(bound)/log(y)
 */
class Solution_970 {

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        List<Integer> powerX = new ArrayList<>();
        List<Integer> powerY = new ArrayList<>();
        HashSet<Integer> powerfulIntegers = new HashSet<>();
        int runningPower = 1;
        while (runningPower <= bound) {
            powerX.add(runningPower);
            if (runningPower + 1 <= bound) powerfulIntegers.add(runningPower + 1);
            if (x == 1) break;
            runningPower *= x;
        }
        runningPower = 1;
        while (runningPower <= bound) {
            powerY.add(runningPower);
            if (runningPower + 1 <= bound) powerfulIntegers.add(runningPower + 1);
            if (y == 1) break;
            runningPower *= y;
        }
        for (int i = 1; i < powerX.size(); i++) {
            for (int j = 1; j < powerY.size(); j++) {
                if (powerX.get(i) + powerY.get(j) <= bound) powerfulIntegers.add(powerX.get(i) + powerY.get(j));
                else break;
            }
        }
        return powerfulIntegers.stream().toList();
    }

}