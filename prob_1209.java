public class prob_1209 {
    public static void main(String[] args) {
        Solution_1209 solution = new Solution_1209();
        String s = "deeedbbcccbdaa";
        int k = 3;
        System.out.println(solution.removeDuplicates(s, k));
    }
}

class Solution_1209 {
    public String removeDuplicates(String s, int k) {
        StringBuilder stringBuilder = new StringBuilder(s);
        int chainLength = 1;
        for(int i = 0; i < stringBuilder.length()-1; ) {
            if(stringBuilder.charAt(i) == stringBuilder.charAt(i+1)) {
                chainLength++;
                if(chainLength == k) {
                    stringBuilder.delete(i-k+2, i+2);
                    chainLength = 1;
                    i = Math.max(i - 2 * k + 3, 0);
                    continue;
                }
            } else {
                chainLength = 1;
            }
            i++;
        }
        return stringBuilder.toString();
    }
}
