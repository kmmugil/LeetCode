import java.util.ArrayList;
import java.util.List;

public class prob_986 {
    public static void main(String args[]) {
        Solution_986 solution = new Solution_986();
        int[][] firstList = new int[][] {{0,2},{5,10},{13,23},{24,25}}, secondList = new int[][] {{1,5},{8,12},{15,24},{25,26}};
        int[][] intersection = solution.intervalIntersection(firstList, secondList);
        for(int i=0; i<intersection.length; i++){
            System.out.println(intersection[i][0] + "," + intersection[i][1]);
        }
    }
}

class Solution_986 {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> intersection = new ArrayList<int[]>();
        for(int i=0, j=0; i<firstList.length && j<secondList.length; ) {
            if(firstList[i][0] <= secondList[j][0]) {
                if(firstList[i][1] >= secondList[j][0]) {
                    if(firstList[i][1] <= secondList[j][1]) {
                        intersection.add(new int[] {secondList[j][0], firstList[i][1]});
                        i++;
                    }
                    else {
                        intersection.add(new int[] {secondList[j][0], secondList[j][1]});
                        j++;
                    }
                }
                else
                    i++;
            }
            else {
                if(secondList[j][1] >= firstList[i][0]) {
                    if(secondList[j][1] <= firstList[i][1]) {
                        intersection.add(new int[] {firstList[i][0], secondList[j][1]});
                        j++;
                    }
                    else {
                        intersection.add(new int[] {firstList[i][0], firstList[i][1]});
                        i++;
                    }
                }
                else
                    j++;
            }
        }
        return intersection.toArray(new int[][] {});
    }
}
