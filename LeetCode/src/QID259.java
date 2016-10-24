import java.util.Arrays;


// ?
public class QID259 {

    public static void main(String[] args){
        int[] nums = {-2, 0, 1, 3};
        int target = 2;

        System.out.println(QID259.threeSumSmaller(nums, target));
    }
    public static int threeSumSmaller(int[] nums, int target) {
        int triplets = 0;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i ++) { // for i; no need to skip duplicate
            int targ = target - nums[i];

            int lo = i + 1;
            int hi = nums.length - 1;

            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                if (sum >= targ) {
                    hi --;
                } else {
                    triplets += (hi - lo); // main difference
                    lo ++;
                }
            }
        }

        return triplets;
    }
}
