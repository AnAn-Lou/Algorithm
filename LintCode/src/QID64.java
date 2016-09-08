// http://www.lintcode.com/en/problem/merge-sorted-array/
public class QID64 {
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {

        if (A == null || B == null || A.length < m + n) {
            return;
        }

        int pA = m - 1;
        int pB = n - 1;
        int pM = m + n - 1;

        while (pA >= 0 && pB >= 0) {

            if (A[pA] > B[pB]) {
                A[pM] = A[pA];
                pA--;
            } else {
                A[pM] = B[pB];
                pB--;
            }
            pM--;
        }

        while (pB >= 0) {
            A[pM] = B[pB];
            pB--;
            pM--;
        }
    }
}
