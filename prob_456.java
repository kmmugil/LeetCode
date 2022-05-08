import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class prob_456 {
    public static void main(String[] args) {
        Solution_456 solution = new Solution_456();
        int[] nums = {3,1,4,2};
        int[] nums1 = {-2,1,2,-2,1,2};
        System.out.println(solution.find132pattern_2(nums1));
    }
}

class Solution_456 {
    public boolean find132pattern(int[] nums) {
        List<IntPair> ascendingPairs = new ArrayList<>();
        int tmpMax = Integer.MAX_VALUE, tmpMin = Integer.MIN_VALUE;
        for (int num : nums) {
            if(num < tmpMax) {
                ascendingPairs.add(new IntPair(tmpMin, tmpMax));
                tmpMin = num;
            }
            tmpMax = num;
            for (int i = 1; i < ascendingPairs.size(); i++) {
                IntPair intPair = ascendingPairs.get(i);
                if(num < intPair.getMax() && num > intPair.getMin()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean find132pattern_1(int[] nums) {
        List<IntPair> intervals = new ArrayList<>();
        int tmpMin = nums[0], tmpMax = nums[0];
        int i;
        for (i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i-1]) {
                intervals.add(new IntPair(nums[i-1], nums[i]));
                tmpMin = nums[i-1];
                tmpMax = nums[i];
                i++;
                break;
            }
        }
        for (; i < nums.length; i++) {
            if(nums[i] < nums[i-1]) {
                for (int j = 0; j < intervals.size(); j++) {
                    if(tmpMin > intervals.get(j).getMax()) {
                        continue;
                    } else if(tmpMax < intervals.get(j).getMin()) {
                        intervals.add(j, new IntPair(tmpMin, tmpMax));
                        break;
                    } else {
                        int start = j, end = j;
                        boolean endInterval = true;
                        if(tmpMin < intervals.get(j).getMin()) {
                            while(j >= 0) {
                                if(!(tmpMin > intervals.get(j).getMax() || tmpMax < intervals.get(j).getMin())) {
                                    tmpMin = Math.min(tmpMin, intervals.get(j).getMin());
                                    tmpMax = Math.max(tmpMax, intervals.get(j).getMax());
                                    start = j;
                                    j--;
                                } else {
                                    intervals.add(j+1, new IntPair(tmpMin, tmpMax));
                                    for (int k = start+1; k <= end+1 ; k++) {
                                        intervals.remove(k);
                                    }
                                    endInterval = false;
                                    break;
                                }
                            }
                            if(endInterval) {
                                intervals.add(0, new IntPair(tmpMin, tmpMax));
                                for (int k = start+1; k <= end+1 ; k++) {
                                    intervals.remove(k);
                                }
                            }
                        } else {
                            while(j < intervals.size()) {
                                if(!(tmpMin > intervals.get(j).getMax() || tmpMax < intervals.get(j).getMin())) {
                                    tmpMin = Math.min(tmpMin, intervals.get(j).getMin());
                                    tmpMax = Math.max(tmpMax, intervals.get(j).getMax());
                                    end = j;
                                    j++;
                                } else {
                                    intervals.add(j, new IntPair(tmpMin, tmpMax));
                                    for (int k = start; k <= end ; k++) {
                                        intervals.remove(k);
                                    }
                                    endInterval = false;
                                    break;
                                }
                            }
                            if(endInterval) {
                                intervals.add(new IntPair(tmpMin, tmpMax));
                                for (int k = start; k <= end ; k++) {
                                    intervals.remove(k);
                                }
                            }
                        }
                        break;
                    }
                }
                tmpMin = nums[i];
            }
            tmpMax = nums[i];
            for (IntPair interval : intervals) {
                if(nums[i] < interval.getMax() && nums[i] > interval.getMin()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * The idea is to compare the j and k numbers first since they are adjacent to each other and then finding iterating down for possible i's
     * Finding the max value of the all possible k's smaller than j (done through stack)
     * @param nums
     * @return
     */
    public boolean find132pattern_2(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int tmpMax = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] < tmpMax) {
                return true;
            } else {
                while(!stack.empty() && nums[i] > stack.peek()) {
                    tmpMax = stack.peek();
                    stack.pop();
                }
            }
            stack.push(nums[i]);
        }
        return false;
    }
}

class IntPair {
    private int min;
    private int max;

    public IntPair(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return (this.getMin()+"-"+this.getMax());
    }
}
