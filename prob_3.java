public class prob_3 {
    public static void main(String args[]) {
        String s = "dvdf";
        System.out.println(Solution_3.lengthOfLongestSubstring(s));
    }
}

class Solution_3 {
    public static int lengthOfLongestSubstring(String string) {
        char[] s = string.toCharArray();
        int max = 0, index = 0;
        if(s.length > 0){
            String substring = ""+s[0];
            for(int i=1; i<s.length; i++){
                index = substring.indexOf(s[i]);
                if(index == -1) {
                    substring = substring+s[i];
                }
                else {
                    max = Math.max(max, substring.length());
                    if(substring.length() > index+1) 
                        substring = substring.substring(index+1) + s[i];
                    else
                        substring = ""+s[i];      
                }
            }
            max = Math.max(max, substring.length());
        }
        return max;
    }
}
