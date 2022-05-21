import java.util.*;

/**
 * 322. Coin Change
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 */
public class prob_322 {
    public static void main(String[] args) {
        Solution_322 solution = new Solution_322();
//        int[] coins = {1,2,5}; int amount = 11;
//        int[] coins = {1}; int amount = 0;
        /*
         *  This example refutes the greedy approach of having the largest denominator with the higher count leading to the optimal result
         */
//        int[] coins = {7,5,1}; int amount = 10;
        int[] coins = {186, 419, 83, 408}; int amount = 6249;
        System.out.println(solution.coinChange(coins, amount));
    }
}

class Solution_322 {
    int[] sortedCoins;
    int[] countCoins;
    List<HashMap<Integer, Integer>> hashMaps;
    public int coinChange(int[] coins, int amount) {
        /*
         * Sorting the coins is only necessary for the greedy approach, DP approach doesn't care about the order in which the coins are present in the array
         */
//        Arrays.sort(coins);
        sortedCoins = coins;
//        countCoins = new int[coins.length];
//        Arrays.fill(countCoins, -1);
//        int count = this.coinChangeRecurse(coins.length-1, amount);
//        System.out.println(Arrays.toString(countCoins));
//        int tmp = 0;
//        for (int i = 0; i < sortedCoins.length; i++) {
//            if(countCoins[i] != -1) {
//                tmp += sortedCoins[i]*countCoins[i];
//            }
//        }
//        System.out.println(tmp);
//        return count;
        hashMaps = new ArrayList<>();
        for (int i = 0; i < coins.length; i++) {
            hashMaps.add(new HashMap<>());
        }
        int tots =  this.coinChangeRecurse_v2(coins.length-1, amount);
        return tots == Integer.MAX_VALUE ? -1 : tots;
    }

    /**
     * Greedy solution - doesn't work
     */
    private int coinChangeRecurse(int i, int amount) {
        if(amount == 0) return 0;
        if(amount < 0 || i < 0) return -1;
        int j = amount/sortedCoins[i], result = -1;
        int tmpAmount = amount - (j*sortedCoins[i]);
        for (int k = j; k >= 0; k--) {
            result = this.coinChangeRecurse(i-1, tmpAmount);
            if(result != -1) {
                countCoins[i] = k;
                result += k;
                break;
            }
            tmpAmount += sortedCoins[i];
        }
        return result;
    }

    /**
     * Solution using Dynamic Programming and memoization (Time and Space complexity is still worse though)
     * The trick here is to cache as many (i, amount) pairs in the HashMap/Vector
     */
    private int coinChangeRecurse_v2(int i, int amount) {
        if(amount == 0) return 0;
        if(amount < 0 || i < 0) return Integer.MAX_VALUE;
        if(!Objects.isNull(hashMaps.get(i).get(amount))) return hashMaps.get(i).get(amount);
        int isPresent, isAbsent;
        isAbsent = this.coinChangeRecurse_v2(i-1, amount);
        isPresent = this.coinChangeRecurse_v2(i, amount-sortedCoins[i]);
        isPresent = isPresent == Integer.MAX_VALUE ? isPresent : isPresent+1;
        hashMaps.get(i).put(amount, Math.min(isAbsent, isPresent));
        return hashMaps.get(i).get(amount);
    }

    /**
     * Need to try solving this question mathematically t0*coins[0] + t1*coins[1] ... + tn*coins[n] = amount, minimize the sum of t0+t1+ .... +tn.
     * One of the possible solutions might include regression
     */
}
