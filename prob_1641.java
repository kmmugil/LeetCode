import java.util.*;

public class prob_1641 {
    public static void main(String[] args) {
        Solution_1641 solution = new Solution_1641();
        System.out.println(solution.countVowelStrings(1));
        for (int[] ints : solution.memoize) {
            System.out.println(Arrays.toString(ints));
        }
    }
}

class Solution_1641 {
    int[][] memoize;
    public int countVowelStrings(int n) {
        memoize = new int[n][5];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                memoize[i][j] = Integer.MIN_VALUE;
            }
        }
        return this.recurseVowelStack(n, 0);
    }

    private int recurseVowelStack(int n, int i) {
        if(n == 0) return 1;
        if(i > 4) return -1;
        int includedTop, excludedTop;
        if(n == 1) {
            includedTop = 1;
        } else {
            if(memoize[n-2][i] != Integer.MIN_VALUE) {
                includedTop = memoize[n-2][i];
            } else {
                includedTop = this.recurseVowelStack(n-1, i);
                memoize[n-2][i] = includedTop;
            }
        }
        if(i == 4) {
            excludedTop = -1;
        } else {
            if(memoize[n-1][i+1] != Integer.MIN_VALUE) {
                excludedTop = memoize[n-1][i+1];
            } else {
                excludedTop = this.recurseVowelStack(n, i+1);
                memoize[n-1][i+1] = excludedTop;
            }
        }
        if(includedTop == -1 || excludedTop == -1) {
            return Math.max(includedTop, excludedTop);
        } else {
            return includedTop+excludedTop;
        }
    }
}

class Solution_1641_1 {
    public int countVowelStrings(int n) {
        return this.recurseVowelStack(n, 1);
    }

    private int recurseVowelStack(int n, int i) {
        if(n == 0) return 1;
        if(i > 5) return -1;
        int includedTop, excludedTop;
        includedTop = this.recurseVowelStack(n-1, i);
        excludedTop = this.recurseVowelStack(n, i+1);
        if(includedTop == -1 || excludedTop == -1) {
            return Math.max(includedTop, excludedTop);
        } else {
            return includedTop+excludedTop;
        }
    }
}
