// http://www.lintcode.com/en/problem/search-a-2d-matrix/
public class QID28 {
    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        
        // find row
        // find the index largest index i such that matrix[i][0] <= target
        int low = 0;
        int high = row - 1;
        int mid;
        while (low + 1 < high) {
            mid = low + (high - low) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] > target) {
                high = mid;
            } else {
                low = mid;
            }
        }
        int x; // cordinate x
        if (matrix[high][0] <= target) {
            x = high;
        } else if (matrix[low][0] <= target) {
            x = low;
        } else {
            return false;
        }
        
        // find column
        low = 0;
        high = col - 1;
        while (low + 1 < high) {
            mid = low + (high - low) / 2;
            if (matrix[x][mid] == target) {
                return true;
            } else if (matrix[x][mid] > target) {
                high = mid;
            } else {
                low = mid;
            }
        }
        if (matrix[x][low] == target || matrix[x][high] == target) {
            return true;
        } else {
            return false;
        }        
    } 

}
