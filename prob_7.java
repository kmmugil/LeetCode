import java.util.Arrays;

public class prob_7 {
    public static void main(String[] args) {
        int x = -(int) (Math.pow(2, 31)-1);
        System.out.println(Solution_7.reverse(x));
    }
}

class Solution_7 {
    public static int reverse(int x) {
        try{
            String s = String.valueOf(Math.abs(x));
            StringBuilder stringBuilder = new StringBuilder(s);
            stringBuilder.reverse();
            return x >= 0 ? Integer.parseInt(stringBuilder.toString()) : Integer.parseInt(stringBuilder.insert(0, '-').toString());
        } catch(NumberFormatException e) {
            System.out.println("Exception::::: "+e.getMessage());
            return 0;
        }
    }
}
