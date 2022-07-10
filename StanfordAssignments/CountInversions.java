package StanfordAssignments;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountInversions {

    public static void main(String[] args) {
        try {
            int[] nums = readArrayFromFile();
//            int[] nums = {1, 3, 5, 2, 4, 6};
            System.out.println(countInversions(nums));
        } catch (FileNotFoundException e) {
            System.out.println("File specified is not found in resources: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] readArrayFromFile() throws IOException {
        String filepath = new File("").getAbsolutePath();
        BufferedReader reader = new BufferedReader(new FileReader(filepath + "\\StanfordAssignments\\resources\\IntegerArray.txt"));
        List<Integer> arrayList = new ArrayList<>();
        reader.lines().forEach(line -> arrayList.add(Integer.parseInt(line)));
        reader.close();
        int[] nums = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            nums[i] = arrayList.get(i);
        }
        return nums;
    }

    private static long countInversions(int[] nums) {
        if (nums.length == 1) return 0;
        int[] left = Arrays.copyOfRange(nums, 0, nums.length / 2);
        int[] right = Arrays.copyOfRange(nums, nums.length / 2, nums.length);
        long invCount = 0;
        invCount += countInversions(left);
        invCount += countInversions(right);
        invCount += mergeCountSplitInv(left, right, nums);
        return invCount;
    }

    private static long mergeCountSplitInv(int[] left, int[] right, int[] nums) {
        int i = 0, j = 0, k = 0, splitInvCount = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                nums[k] = left[i];
                i++;
            } else {
                nums[k] = right[j];
                j++;
                splitInvCount += left.length - i;
            }
            k++;
        }
        if (i < left.length) {
            while (k < nums.length) {
                nums[k] = left[i];
                i++;
                k++;
            }
        } else {
            while (k < nums.length) {
                nums[k] = right[j];
                j++;
                k++;
            }
        }
        return splitInvCount;
    }

}