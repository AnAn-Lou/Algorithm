public class QID42 {

    public static void main(String[] args) {
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        QID42 q = new QID42();
        System.out.println(q.trap(arr));
    }

    // Version 2: two pointers
    public int trap(int[] height) {

        if (height == null || height.length <= 1) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int leftHighest = height[left]; // biggest height from left
        int rightHighest = height[right]; // biggest height from right

        int water = 0;
        while (left < right) {
            if (leftHighest < rightHighest) {
                left++;
                if (leftHighest < height[left]) {
                    leftHighest = height[left];
                } else {
                    water += leftHighest - height[left];
                }
            } else {
                right--;
                if (rightHighest < height[right]) {
                    rightHighest = height[right];
                } else {
                    water += rightHighest - height[right];
                }
            }
        }

        return water;
    }

    /* Version 2: three pass
     * Iterate from left to right, record the biggest height left[i] to the left of bar i,
     * then iterate from right to left, record the biggest height right[i] to the right of bar i,
     * finally iterate again to add water.
    public int trap(int[] height) {

        if (height == null || height.length == 0) {
            return 0;
        }

        int len = height.length;

        // for any index i
        // water = Math.min(the biggest height to the left of height[i], the biggest height to the right of height[i]) - height[i]
        // water = water > 0 ? water : 0;

        // Part 1
        // state: left[i] == the height of highest bar to the left of height[i]
        int[] left = new int[len];

        // initialization: there is nothing to the left of height[0]
        left[0] = 0;

        // transition
        for (int i = 1; i < len; i++) {
            left[i] = Math.max(left[i - 1], height[i - 1]);
        }

        // Part 2
        // state: right[i] == the height of highest bar to the right of height[i]
        int[] right = new int[len];

        // initialization: there is nothing to the right of height[len - 1]
        left[len - 1] = 0;

        // transition
        for (int i = len - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i + 1]);
        }

        // Part 3
        int sum = 0;
        for (int i = 0; i < len; i++) {
            int min = Math.min(left[i], right[i]);
            int curr = min > height[i] ? min - height[i] : 0;
            sum += curr;
        }

        return sum;
    }
    */
}
