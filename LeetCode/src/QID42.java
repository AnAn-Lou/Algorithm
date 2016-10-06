public class QID42 {

    public static void main(String[] args) {
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        QID42 q = new QID42();
        System.out.println(q.trappingRainWater(arr));
    }

    public int trappingRainWater(int[] arr){

        int len = arr.length;

        // for any index i
        // water = Math.min(the biggest height to the left of arr[i], the biggest height to the right of arr[i]) - arr[i]
        // water = water > 0 ? water : 0;

        // Part 1
        // state: left[i] == the height of highest bar to the left of arr[i]
        int[] left = new int[len];

        // initialization: there is nothing to the left of arr[0]
        left[0] = 0;

        // transition
        for (int i = 1; i < len; i++) {
            left[i] = Math.max(left[i - 1], arr[i - 1]);
        }

        // Part 2
        // state: right[i] == the height of highest bar to the right of arr[i]
        int[] right = new int[len];

        // initialization: there is nothing to the right of arr[len - 1]
        left[len - 1] = 0;

        // transition
        for (int i = len - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], arr[i + 1]);
        }

        // Part 3
        int sum = 0;
        for (int i = 0; i < len; i++) {
            int min = Math.min(left[i], right[i]);
            int curr = min > arr[i] ? min - arr[i] : 0;
            sum += curr;
        }

        return sum;
    }
}
