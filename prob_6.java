import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class prob_6 {
    public static void main(String[] args) {
        String s = "AB";
        System.out.println(Solution_6.convert(s, 1));
    }
}

class Solution_6 {
    public static String convert(String s, int numRows) {
        try{
            if(numRows == 1) return s;
            if(numRows == 0) return null;
            char[] originalString = s.toCharArray();
            ArrayList<ArrayList<Character>> stringList = new ArrayList<>();
            for(int i = 0; i < numRows; i++) {
                stringList.add(new ArrayList<>());
            }
            int j = 0;
            boolean zigzag = true;
            for (char value : originalString) {
                stringList.get(j).add(value);
                if(j == numRows-1 || j == 0) zigzag = !zigzag;
                j = zigzag ? j + 1 : j - 1;
            }
            return stringList.stream().map(list -> list.stream().map(Objects::toString).reduce((acc, c) -> acc+c).orElse("")).reduce((acc, c) -> acc+c).orElse("");
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Doofus, check array index out of bounds!");
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
