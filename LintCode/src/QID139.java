// http://www.lintcode.com/en/problem/subarray-sum-closest/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QID139 {
    public int[] subarraySumClosest(int[] nums) {
        // write your code here
        int[] prefixSum = new int[nums.length + 1];
        List<Prefix> list = new ArrayList<>();

        prefixSum[0] = 0;
        list.add(new Prefix(0,0));

        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
            list.add(new Prefix(prefixSum[i], i));
        }

        Collections.sort(list);
        int minDiff = Integer.MAX_VALUE;
        int indexOfMinDiffInList = 0;
        int temp;
        for (int i = 0; i < list.size() - 1; i++){
            temp = Math.abs(list.get(i).prefixSum - list.get(i+1).prefixSum);
            if (temp < minDiff){
                indexOfMinDiffInList = i;
                minDiff = temp;
            }
        }

        int a = list.get(indexOfMinDiffInList).index;
        int b = list.get(indexOfMinDiffInList + 1).index;

        int[] result = new int[2];

        if (a < b){
            result[0] = a;
            result[1] = b - 1;
        } else {
            result[0] = b;
            result[1] = a - 1;
        }

        return result;
    }

    private static class Prefix implements Comparable<Prefix>{
        private int prefixSum;
        private int index;

        Prefix (int prefixSum, int index){
            this.prefixSum = prefixSum;
            this.index = index;
        }

        public int compareTo(Prefix p){
            return this.prefixSum - p.prefixSum;
        }
    }
}
