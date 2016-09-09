// http://www.lintcode.com/en/problem/median-of-two-sorted-arrays/
public class QID65 {
    public double findMedianSortedArrays(int[] A, int[] B) {

        int k = A.length + B.length;

        if (k % 2 == 0) {
            return (findKth(A, 0, B, 0, k/2) + findKth(A, 0, B, 0, k/2 + 1)) / 2.0;
        } else {
            return findKth(A, 0, B, 0, k/2 + 1);
        }
    }

    private int findKth(int[] A, int startA,
                        int[] B, int startB,
                        int k) {
        // empty array
        if (startA >= A.length) {
            return B[startB + k - 1];
        }
        if (startB >= B.length) {
            return A[startA + k - 1];
        }

        if (k == 1) {
            return Math.min(A[startA], B[startB]);
        }

        // ArrayIndexOutOfBounds
        int valA = startA + k / 2 - 1 >= A.length ? Integer.MAX_VALUE : A[startA + k / 2 - 1];
        int valB = startB + k / 2 - 1 >= B.length ? Integer.MAX_VALUE : B[startB + k / 2 - 1];

        if (valA < valB) {
            startA = startA + k / 2;
        } else {
            startB = startB + k / 2;
        }

        return findKth(A, startA, B, startB, k - k/2);
    }
}
