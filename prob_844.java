import java.util.Objects;

public class prob_844 {
    public static void main(String[] args) {
        String s = "sywrrmp";
        String t = "sywrrmu#p";
        System.out.println(Solution_844.backspaceCompare(s, t));
    }
}

class Solution_844 {
    public static boolean backspaceCompare(String s, String t) {
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        StringBuilder sFinal = new StringBuilder();
        StringBuilder tFinal = new StringBuilder();
        for (char c : a) {
            if(c != '#') {
                sFinal.append(c);
            } else {
                if(!sFinal.isEmpty()) {
                    sFinal.delete(sFinal.length()-1, sFinal.length());
                }
            }
        }
        for (char c : b) {
            if(c != '#') {
                tFinal.append(c);
            } else {
                if(!tFinal.isEmpty()) {
                    tFinal.delete(tFinal.length()-1, tFinal.length());
                }
            }
        }
        return Objects.equals(sFinal.toString(), tFinal.toString());
    }
}
