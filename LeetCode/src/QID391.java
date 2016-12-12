import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/perfect-rectangle/
public class QID391 {
    // https://discuss.leetcode.com/topic/56052/really-easy-understanding-solution-o-n-java/4
    // https://discuss.leetcode.com/topic/55944/o-n-log-n-sweep-line-solution/10
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles == null || rectangles.length == 0) {
            return false;
        }

        int leftBottomX = Integer.MAX_VALUE;
        int leftBottomY = Integer.MAX_VALUE;
        int rightTopX = Integer.MIN_VALUE;
        int rightTopY = Integer.MIN_VALUE;

        Set<String> set = new HashSet<>();

        int area = 0;

        for (int[] rectangle : rectangles) {
            leftBottomX = Math.min(leftBottomX, rectangle[0]);
            leftBottomY = Math.min(leftBottomY, rectangle[1]);
            rightTopX = Math.max(rightTopX, rectangle[2]);
            rightTopY = Math.max(rightTopY, rectangle[3]);

            if (!set.add(rectangle[0] + " " + rectangle[1])) set.remove(rectangle[0] + " " + rectangle[1]);
            if (!set.add(rectangle[0] + " " + rectangle[3])) set.remove(rectangle[0] + " " + rectangle[3]);
            if (!set.add(rectangle[2] + " " + rectangle[3])) set.remove(rectangle[2] + " " + rectangle[3]);
            if (!set.add(rectangle[2] + " " + rectangle[1])) set.remove(rectangle[2] + " " + rectangle[1]);

            area += (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);
        }

        if (set.size() != 4 || !set.contains(leftBottomX + " " + leftBottomY) ||
                !set.contains(leftBottomX + " " + rightTopY) ||
                !set.contains(rightTopX + " " + rightTopY) ||
                !set.contains(rightTopX + " " + leftBottomY)) {
            return false;
        }
        return area == (rightTopX - leftBottomX) * (rightTopY - leftBottomY);
    }
}
